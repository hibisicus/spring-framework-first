/*
 * Copyright 2002-2018 the original author or authors.
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

package org.springframework.beans.factory.xml;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

/**
 * Default implementation of the {@link BeanDefinitionDocumentReader} interface that
 * reads bean definitions according to the "spring-beans" DTD and XSD format
 * (Spring's default XML bean definition format).
 *
 * <p>The structure, elements, and attribute names of the required XML document
 * are hard-coded in this class. (Of course a transform could be run if necessary
 * to produce this format). {@code <beans>} does not need to be the root
 * element of the XML document: this class will parse all bean definition elements
 * in the XML file, regardless of the actual root element.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @author Erik Wiersma
 * @since 18.12.2003
 */
public class DefaultBeanDefinitionDocumentReader implements BeanDefinitionDocumentReader {

    public static final String BEAN_ELEMENT = BeanDefinitionParserDelegate.BEAN_ELEMENT;

    public static final String NESTED_BEANS_ELEMENT = "beans";

    public static final String ALIAS_ELEMENT = "alias";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String ALIAS_ATTRIBUTE = "alias";

    public static final String IMPORT_ELEMENT = "import";

    public static final String RESOURCE_ATTRIBUTE = "resource";

    public static final String PROFILE_ATTRIBUTE = "profile";

    protected final Log logger = LogFactory.getLog(getClass());

    @Nullable private XmlReaderContext readerContext;

    @Nullable private BeanDefinitionParserDelegate delegate;

    /**
     * This implementation parses bean definitions according to the "spring-beans" XSD
     * (or DTD, historically).
     * <p>Opens a DOM Document; then initializes the default settings
     * specified at the {@code <beans/>} level; then parses the contained bean definitions.
     */
    @Override public void registerBeanDefinitions(Document doc, XmlReaderContext readerContext) {
        this.readerContext = readerContext;
        doRegisterBeanDefinitions(doc.getDocumentElement());
    }

    /**
     * Return the descriptor for the XML resource that this parser works on.
     */
    protected final XmlReaderContext getReaderContext() {
        Assert.state(this.readerContext != null, "No XmlReaderContext available");
        return this.readerContext;
    }

    /**
     * Invoke the {@link org.springframework.beans.factory.parsing.SourceExtractor}
     * to pull the source metadata from the supplied {@link Element}.
     */
    @Nullable protected Object extractSource(Element ele) {
        return getReaderContext().extractSource(ele);
    }

    /**
     * Register each bean definition within the given root {@code <beans/>} element.
     */
    @SuppressWarnings("deprecation")  // for Environment.acceptsProfiles(String...)
    protected void doRegisterBeanDefinitions(Element root) {
        // Any nested <beans> elements will cause recursion in this method. In
        // order to propagate and preserve <beans> default-* attributes correctly,
        // keep track of the current (parent) delegate, which may be null. Create
        // the new (child) delegate with a reference to the parent for fallback purposes,
        // then ultimately reset this.delegate back to its original (parent) reference.
        // this behavior emulates a stack of delegates without actually necessitating one.
        //		专门处理解析
        BeanDefinitionParserDelegate parent = this.delegate;
        this.delegate = createDelegate(getReaderContext(), root, parent);

        if (this.delegate.isDefaultNamespace(root)) {
            //			处理profile属性
            String profileSpec = root.getAttribute(PROFILE_ATTRIBUTE);
            if (StringUtils.hasText(profileSpec)) {
                String[] specifiedProfiles = StringUtils.tokenizeToStringArray(profileSpec,
                        BeanDefinitionParserDelegate.MULTI_VALUE_ATTRIBUTE_DELIMITERS);
                // We cannot use Profiles.of(...) since profile expressions are not supported
                // in XML config. See SPR-12458 for details.
                if (!getReaderContext().getEnvironment().acceptsProfiles(specifiedProfiles)) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Skipped XML bean definition file due to specified profiles [" + profileSpec
                                + "] not matching: " + getReaderContext().getResource());
                    }
                    return;
                }
            }
        }

        //		解析前处理,留给子类实现
        preProcessXml(root);
        parseBeanDefinitions(root, this.delegate);
        //		解析后处理,留给子类实现
        postProcessXml(root);

        this.delegate = parent;
    }

    protected BeanDefinitionParserDelegate createDelegate(XmlReaderContext readerContext, Element root,
            @Nullable BeanDefinitionParserDelegate parentDelegate) {

        BeanDefinitionParserDelegate delegate = new BeanDefinitionParserDelegate(readerContext);
        delegate.initDefaults(root, parentDelegate);
        return delegate;
    }

    /**
     * Parse the elements at the root level in the document:
     * "import", "alias", "bean".
     *
     * @param root the DOM root element of the document
     */
    protected void parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate) {
        //		对Beans的处理
        if (delegate.isDefaultNamespace(root)) {
            NodeList nl = root.getChildNodes();
            for (int i = 0; i < nl.getLength(); i++) {
                //				通过node.getNamespaceURL()获取命名空间，并与Spring中固定的命名空间http://www.springframework.org/scherna/beans 进行对比
                //				一致性则认为是默认
                Node node = nl.item(i);
                if (node instanceof Element) {
                    Element ele = (Element) node;
                    if (delegate.isDefaultNamespace(ele)) {
                        //						对Bean的处理;对于根节点或者是子节点，如果是使用默认的命名空间
                        parseDefaultElement(ele, delegate);
                    } else {
                        //						对Bean的处理;自定义命名
                        delegate.parseCustomElement(ele);
                    }
                }
            }
        } else {
//            自定义标签的解析
            delegate.parseCustomElement(root);
        }
    }

    //	XML中默认标签的解析  匹配:import,alias,bean,beans
    private void parseDefaultElement(Element ele, BeanDefinitionParserDelegate delegate) {
        if (delegate.nodeNameEquals(ele, IMPORT_ELEMENT)) {
            importBeanDefinitionResource(ele);
        } else if (delegate.nodeNameEquals(ele, ALIAS_ELEMENT)) {
            processAliasRegistration(ele);
        } else if (delegate.nodeNameEquals(ele, BEAN_ELEMENT)) {
            processBeanDefinition(ele, delegate);
        } else if (delegate.nodeNameEquals(ele, NESTED_BEANS_ELEMENT)) {
            // recurse
            doRegisterBeanDefinitions(ele);
        }
    }

    /**
     * Parse an "import" element and load the bean definitions
     * from the given resource into the bean factory.
     *
     * Spring解析import标签
     * 1、获取resource属性锁标识的路径
     * 2、解析路径中的系统属性，格式如“{use.dir}”
     * 3、判断location是绝对路径还是相对路径
     * 4、如果是绝对路径则递归使用调用bean的解析过程，进行另一次的解析
     * 5、如果是相对路径则计算出绝对路径并进行解析
     * 6、通知监听器,解析完成.
     */
    protected void importBeanDefinitionResource(Element ele) {
//        获取resource属性
        String location = ele.getAttribute(RESOURCE_ATTRIBUTE);
//        如果不存在resource属性，则不做任何处理
        if (!StringUtils.hasText(location)) {
            getReaderContext().error("Resource location must not be empty", ele);
            return;
        }

        // Resolve system properties: e.g. "${user.dir}";解析系统属性
        location = getReaderContext().getEnvironment().resolveRequiredPlaceholders(location);

        Set<Resource> actualResources = new LinkedHashSet<>(4);

        // Discover whether the location is an absolute or relative URI;判定location是决定URL还是相对URL
        boolean absoluteLocation = false;
        try {
            absoluteLocation = ResourcePatternUtils.isUrl(location) || ResourceUtils.toURI(location).isAbsolute();
        } catch (URISyntaxException ex) {
            // cannot convert to an URI, considering the location relative
            // unless it is the well-known Spring prefix "classpath*:"
        }

        // Absolute or relative?;如果是绝对URL则直接根据地址加载对应的配置文件
        if (absoluteLocation) {
            try {
                int importCount = getReaderContext().getReader().loadBeanDefinitions(location, actualResources);
                if (logger.isTraceEnabled()) {
                    logger.trace("Imported " + importCount + " bean definitions from URL location [" + location + "]");
                }
            } catch (BeanDefinitionStoreException ex) {
                getReaderContext()
                        .error("Failed to import bean definitions from URL location [" + location + "]", ele, ex);
            }
        } else {
            // No URL -> considering resource location as relative to the current file.
//            如果是相对地址则根据相对地址计算出绝对地址
            try {
                int importCount;
//                Resource 存在多个子实现类,如VfsResource,FileSystemResource等,
//                而每个Resource的createRelative 方式实现都不一样,所以这里先使用子类的方法尝试解析。
                Resource relativeResource = getReaderContext().getResource().createRelative(location);
                if (relativeResource.exists()) {
                    importCount = getReaderContext().getReader().loadBeanDefinitions(relativeResource);
                    actualResources.add(relativeResource);
                } else {
//                    如果解析不成功,则使用默认的解析器ResourcePatternResolver进行解析
                    String baseLocation = getReaderContext().getResource().getURL().toString();
                    importCount = getReaderContext().getReader()
                            .loadBeanDefinitions(StringUtils.applyRelativePath(baseLocation, location),
                                    actualResources);
                }
                if (logger.isTraceEnabled()) {
                    logger.trace(
                            "Imported " + importCount + " bean definitions from relative location [" + location + "]");
                }
            } catch (IOException ex) {
                getReaderContext().error("Failed to resolve current resource location", ele, ex);
            } catch (BeanDefinitionStoreException ex) {
                getReaderContext()
                        .error("Failed to import bean definitions from relative location [" + location + "]", ele, ex);
            }
        }
//        解析后进行监听器激活处理
        Resource[] actResArray = actualResources.toArray(new Resource[0]);
        getReaderContext().fireImportProcessed(location, actResArray, extractSource(ele));
    }

    /**
     * Process the given alias element, registering the alias with the registry.
     */
    protected void processAliasRegistration(Element ele) {
//        获取beanName
        String name = ele.getAttribute(NAME_ATTRIBUTE);
//        获取alias
        String alias = ele.getAttribute(ALIAS_ATTRIBUTE);
        boolean valid = true;
        if (!StringUtils.hasText(name)) {
            getReaderContext().error("Name must not be empty", ele);
            valid = false;
        }
        if (!StringUtils.hasText(alias)) {
            getReaderContext().error("Alias must not be empty", ele);
            valid = false;
        }
        if (valid) {
            try {
//                注册alias
                getReaderContext().getRegistry().registerAlias(name, alias);
            } catch (Exception ex) {
                getReaderContext()
                        .error("Failed to register alias '" + alias + "' for bean with name '" + name + "'", ele, ex);
            }
//            别名注册后监听器做相应处理
            getReaderContext().fireAliasRegistered(name, alias, extractSource(ele));
        }
    }

    /**
     * Process the given bean element, parsing the bean definition
     * and registering it with the registry.
     * 对bean标签的解析(最复杂、最重要)
     */
    protected void processBeanDefinition(Element ele, BeanDefinitionParserDelegate delegate) {
        //        委托BeanDefinitionParserDelegate中的parseBeanDefinitionElement进行元素解析;返回BeanDefinitionHolder类型的bdHolder
        //        (包含配置文件中的各种属性,class、name、id、alias)
        BeanDefinitionHolder bdHolder = delegate.parseBeanDefinitionElement(ele);
        if (bdHolder != null) {
            //            如果默认标签的子节点有自定义属性，则对自定义标签进行解析
            bdHolder = delegate.decorateBeanDefinitionIfRequired(ele, bdHolder);
            try {
                // Register the final decorated instance.;对解析后的bdHolder进行注册，同时注册操作委托给了registerBeanDefinition方法
                BeanDefinitionReaderUtils.registerBeanDefinition(bdHolder, getReaderContext().getRegistry());
            } catch (BeanDefinitionStoreException ex) {
                getReaderContext()
                        .error("Failed to register bean definition with name '" + bdHolder.getBeanName() + "'", ele,
                                ex);
            }
            // Send registration event.;发出事件响应，通知监视器,这个Bean已经加载完成.
            getReaderContext().fireComponentRegistered(new BeanComponentDefinition(bdHolder));
        }
    }

    /**
     * Allow the XML to be extensible by processing any custom element types first,
     * before we start to process the bean definitions. This method is a natural
     * extension point for any other custom pre-processing of the XML.
     * <p>The default implementation is empty. Subclasses can override this method to
     * convert custom elements into standard Spring bean definitions, for example.
     * Implementors have access to the parser's bean definition reader and the
     * underlying XML resource, through the corresponding accessors.
     *
     * @see #getReaderContext()
     */
    protected void preProcessXml(Element root) {
    }

    /**
     * Allow the XML to be extensible by processing any custom element types last,
     * after we finished processing the bean definitions. This method is a natural
     * extension point for any other custom post-processing of the XML.
     * <p>The default implementation is empty. Subclasses can override this method to
     * convert custom elements into standard Spring bean definitions, for example.
     * Implementors have access to the parser's bean definition reader and the
     * underlying XML resource, through the corresponding accessors.
     *
     * @see #getReaderContext()
     */
    protected void postProcessXml(Element root) {
    }

}
