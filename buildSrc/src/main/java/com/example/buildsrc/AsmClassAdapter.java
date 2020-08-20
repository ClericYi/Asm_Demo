package com.example.buildsrc;


import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Create by yiyonghao on 2020-08-08
 * Email: yiyonghao@bytedance.com
 */
public class AsmClassAdapter extends ClassVisitor implements Opcodes {
    public AsmClassAdapter(ClassVisitor classVisitor) {
        super(ASM7, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
//        System.out.println(descriptor);
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        return (mv == null) ? null : new AsmMethodVisitor(mv, access, name, descriptor) ;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        System.out.println(descriptor);
        AnnotationVisitor av = super.visitAnnotation(descriptor, visible);
        return (av == null) ? null : new AsmAnnotationVisitor(av) ;
    }
}
