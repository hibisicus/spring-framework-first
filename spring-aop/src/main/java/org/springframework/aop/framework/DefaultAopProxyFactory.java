/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.aop.framework;

import java.io.Serializable;
import java.lang.reflect.Proxy;

import org.springframework.aop.SpringProxy;
import org.springframework.core.NativeDetector;

/**
 * Default {@link AopProxyFactory} implementation, creating either a CGLIB proxy
 * or a JDK dynamic proxy.
 *
 * <p>Creates a CGLIB proxy if one the following is true for a given
 * {@link AdvisedSupport} instance:
 * <ul>
 * <li>the {@code optimize} flag is set
 * <li>the {@code proxyTargetClass} flag is set
 * <li>no proxy interfaces have been specified
 * </ul>
 *
 * <p>In general, specify {@code proxyTargetClass} to enforce a CGLIB proxy,
 * or specify one or more interfaces to use a JDK dynamic proxy.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Sebastien Deleuze
 * @since 12.03.2004
 * @see AdvisedSupport#setOptimize
 * @see AdvisedSupport#setProxyTargetClass
 * @see AdvisedSupport#setInterfaces
 */
@SuppressWarnings("serial")
public class DefaultAopProxyFactory implements AopProxyFactory, Serializable {

	/**
	 *
	 * @param config the AOP configuration in the form of an
	 * AdvisedSupport object
	 * @return
	 * @throws AopConfigException
	 * @Description: JDK和Cglib使用总结
	 * · 如果目标对象实现了接口,默认情况下回采用JDK的动态代理实现AOP
	 * · 如果目标实现了接口,可以强制使用CGLIB实现AOP
	 * · 如果目标对象没有实现接口,必须采用CGLIB库,Spring会自动在JDK动态代理和CGLIB之间转换
	 *
	 * 如何强制使用CGLIB实现AOP?
	 * 1、添加CGLIB库,Spring_HOME/cglib/*.jar
	 * 2、在Spring配置文件中加入<aop:aspectj-autoproxy proxy-target-class="true"/>
	 *
	 * JDK动态代理和CGLIB字节码生成的区别?
	 * · JDK动态代理只能对实现了接口的类生成代理,而布恩那个针对类
	 * · CGLIB是针对类实现代理,主要是对指定的类生成一个子类,覆盖其中的方法,因为是集成,所以该类或方法最好不好声明成final
	 */
	@Override
	public AopProxy createAopProxy(AdvisedSupport config) throws AopConfigException {
//		optimize:用来控制通过CGLIB创建的代理是否使用激进的优化策略。除非完全了解AOP代理如何处理优化,否则不推荐用户使用这个设置
//		目前这个设置仅用于CGLIB代理,对于JDK动态代理（默认代理）无效
//		proxyTargetClass:这个属性为true时,目标类本身呗代理而不是目标类的接口.如果这个属性
//		被设置为true,CGLIB代理将被创建,设置方式为<aop:aspectj-autoproxy-proxy-target-class="true"/>
//		hasNoUserSuppliedProxyInterfaces:是否存在代理接口
		if (!NativeDetector.inNativeImage() &&
				(config.isOptimize() || config.isProxyTargetClass() || hasNoUserSuppliedProxyInterfaces(config))) {
			Class<?> targetClass = config.getTargetClass();
			if (targetClass == null) {
				throw new AopConfigException("TargetSource cannot determine target class: " +
						"Either an interface or a target is required for proxy creation.");
			}
			if (targetClass.isInterface() || Proxy.isProxyClass(targetClass)) {
				return new JdkDynamicAopProxy(config);
			}
			return new ObjenesisCglibAopProxy(config);
		}
		else {
			return new JdkDynamicAopProxy(config);
		}
	}

	/**
	 * Determine whether the supplied {@link AdvisedSupport} has only the
	 * {@link org.springframework.aop.SpringProxy} interface specified
	 * (or no proxy interfaces specified at all).
	 */
	private boolean hasNoUserSuppliedProxyInterfaces(AdvisedSupport config) {
		Class<?>[] ifcs = config.getProxiedInterfaces();
		return (ifcs.length == 0 || (ifcs.length == 1 && SpringProxy.class.isAssignableFrom(ifcs[0])));
	}

}
