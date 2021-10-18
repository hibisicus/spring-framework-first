package com.anle.aop;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.NotFoundException;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-10-18 11:40
 */
public class PerfMonXformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] transformed = null;
        System.out.println("Transforming " + className);
        ClassPool pool = ClassPool.getDefault();
        CtClass c1 = null;
        try {
            c1 = pool.makeClass(new java.io.ByteArrayInputStream(classfileBuffer));
            if (c1.isInterface() == false) {
                CtBehavior[] methods = c1.getDeclaredBehaviors();
                for (int i = 0; i < methods.length; i++) {
                    if (methods[i].isEmpty() == false) {
                        // 修改method字节码
                        doMethod(methods[i]);
                    }
                }
                transformed = c1.toBytecode();
            }
        } catch (NotFoundException | CannotCompileException | IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private void doMethod(CtBehavior method) throws NotFoundException, CannotCompileException {

        method.insertBefore("long stime = System.nanoTime()");
        method.insertAfter(
                "System.out.println(\"leave\"+ method.getName() +\"and time:\" +(System.nanoTime()  - stime));\n");
    }
}
