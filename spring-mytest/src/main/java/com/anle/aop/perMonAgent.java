//package com.anle.aop;
//
//import java.lang.instrument.ClassFileTransformer;
//import java.lang.instrument.Instrumentation;
//
///**
// * @author AnnLee
// * @version 1.0
// * @date 2021-10-18 13:39
// * @description org.toy.App的字节码加入监控diamond确实生效,通过Instrment实现agent使得监控代码和应用代码完全隔离了
// */
//public class perMonAgent {
//        static private Instrumentation inst = null;
//        public static void permain(String agentArgs, Instrumentation _inst){
//            System.out.println("=====================");
//            inst = _inst;
//            ClassFileTransformer trans = new PerfMonXformer();
//            System.out.println("=============================2");
//            inst.addTransformer(trans);
//        }
//}
