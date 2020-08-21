package com.example.asm_plugin;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;


/**
 * Create by yiyonghao on 2020-08-08
 * Email: yiyonghao@bytedance.com
 */
public class AsmMethodVisitor extends AdviceAdapter implements Opcodes {

    protected AsmMethodVisitor(MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(ASM7, methodVisitor, access, name, descriptor);
    }

    private static final String ANNOTATION_TRACK_METHOD = "Lcom/example/asm/Cat;";
    private boolean isMatch = false;

    @Override
    public void visitCode() {
        super.visitCode();
        if (isMatch) {
            //方法执行前插入
            mv.visitLdcInsn("tag");
            mv.visitLdcInsn("onCreate start");
            mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
            mv.visitInsn(POP);
        }
    }

    @Override
    public void visitInsn(int opcode) {
        if (isMatch) {
            //方法执行后插入
            // 如果不是return会出现两个end
            if (opcode == Opcodes.RETURN) {
                mv.visitLdcInsn("tag");
                mv.visitLdcInsn("onCreate end");
                mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
                mv.visitInsn(POP);
            }
        }
        super.visitInsn(opcode);
    }

    @Override
    public void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor, boolean isInterface) {
        if (isMatch) {
            mv.visitLdcInsn("tag");
            mv.visitLdcInsn("onCreate 1");
            mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
            mv.visitInsn(POP);
        }
            super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
        if (isMatch) {
            mv.visitLdcInsn("tag");
            mv.visitLdcInsn("onCreate 2");
            mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
            mv.visitInsn(POP);
        }
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if(ANNOTATION_TRACK_METHOD.equals(descriptor)) isMatch = true;
        return super.visitAnnotation(descriptor, visible);
    }
}
