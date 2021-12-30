package com.apm.plugin.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

public class ChangeVisitorMethod extends ClassNode {
    private String mTag = "";

    public void setTag(String tag) {
        mTag = tag;
    }

    public ChangeVisitorMethod(int opt) {
        super(opt);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
//        return super.visitMethod(access, name, descriptor, signature, exceptions);
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (name.equals("<init>") || name.equals("<clinit>")) {
            return methodVisitor;
        }
        return new AdviceAdapterMethodImpl(Opcodes.ASM7, methodVisitor, access, name, descriptor, mTag, sourceFile, this.name, superName);
    }

}