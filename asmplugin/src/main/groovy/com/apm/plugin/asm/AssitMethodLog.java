package com.apm.plugin.asm;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;

public class AssitMethodLog {
    private final static ClassPool pool = ClassPool.getDefault();
//    public static void inject(String path){
//        File file = new File(path);
//        try {
//            if (file.getName().endsWith("class")
//                    && !file.getName().contains("R$")
//                    && !file.getName().contains("R.class")
//                    && !file.getName().contains("BuildConfig.class")&& file.exists()) {
//                InputStream io = new FileInputStream(file);
//                CtClass ctClass = pool.makeClass(io);
//                if (ctClass.isFrozen()) {
//                    ctClass.defrost();
//                };
//                for (CtMethod method : ctClass.getDeclaredMethods()) {
//                    //排除空方法和 native 方法
//                    if (method.isEmpty() || Modifier.isNative(method.getModifiers())) {
//                        return;
//                    }
//                    insertTime(project.Trace.logLevel, className.replace(packageName + ".", ""), method)
//                }
//                ctClass.writeFile(path);
//                ctClass.detach();//释放
//            }
//        } catch (Exception e) {
//        }
//
//    }
//    static void insertTime(String logLevel, String className, CtMethod method) {
//        try {
//            int pos = 1;
//
//            CodeAttribute codeAttribute = method.getMethodInfo().getCodeAttribute();
//            LocalVariableAttribute attribute = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag)
//            int size = method.getParameterTypes().length;
//            String[] paramTypes = new String[size];
//            if (attribute == null) {
//                return;
//            }
//            for (int i = 0; i < size; i++) {
//                //获取入参类型
//                paramTypes[i] = method.getParameterTypes()[i].name;
//            }
//
//            def stringType = POOL.getCtClass("java.lang.String");
//            def objType = POOL.getCtClass("java.lang.Object");
//            //定义局部变量
//            method.addLocalVariable("startTime", CtClass.longType);
//            method.addLocalVariable("endTime", CtClass.longType);
//            //打印类名
//            method.addLocalVariable("className", stringType);
//            //打印方法名
//            method.addLocalVariable("methodName", stringType);
//            //打印行号
//            method.addLocalVariable("lineNumber", CtClass.intType);
//            //打印返回值
//            method.addLocalVariable("returnObj", objType);
//
//            StringBuilder startInjectSB = new StringBuilder();
//
//            //*** 省略插入代码
//
//            //方法前插入 Log
//            method.insertBefore(startInjectSB.toString())
//
//            StringBuilder endInjectSB = new StringBuilder()
//            //*** 省略插入代码
//
//            //方法 return 前插入 Log
//            method.insertAfter(endInjectSB.toString())
//
//        } catch (Exception e) {
//            e.printStackTrace()
//        }
//    }
}
