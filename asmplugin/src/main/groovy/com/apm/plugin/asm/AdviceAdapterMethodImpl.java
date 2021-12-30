package com.apm.plugin.asm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class AdviceAdapterMethodImpl extends AdviceAdapter {

    private String className = "";
    private String classPath = "";
    private String classSuperName = "";
    private String TAG = "";

    protected AdviceAdapterMethodImpl(int api, MethodVisitor methodVisitor, int access, String name, String descriptor,String tag,String classname, String path, String superClass) {
        super(api, methodVisitor, access, name, descriptor);
        TAG = tag;
        className = classname;
        classPath = path;
        classSuperName = superClass;
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();

        System.out.println("AdviceAdapterImpl : 进入 name = " + getName() + " ");
//            Label label1 = new Label();
//            mv.visitLabel(label1);
//            mv.visitLdcInsn("AD-PLUGIN");
//            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
//            mv.visitInsn(DUP);
//            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
//            mv.visitLdcInsn("---->");
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
//            mv.visitVarInsn(ALOAD, 0);
////            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
////            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
//            mv.visitLdcInsn(" method : " + name);
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
//            mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "e", "(Ljava/lang/String;Ljava/lang/String;)I", false);
//            mv.visitInsn(POP);


            Label label1 = new Label();
            mv.visitLabel(label1);
            mv.visitLdcInsn(TAG);
            mv.visitLdcInsn(" className : "+className +" classPath : "+classPath +" classSuper : "+classSuperName +" method : "+getName());
            mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "e", "(Ljava/lang/String;Ljava/lang/String;)I", false);
            mv.visitInsn(POP);



        System.out.println("AdviceAdapterImpl : 完成 name = " + getName() + " ");

    }
}
