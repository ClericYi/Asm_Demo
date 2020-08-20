package com.example.buildsrc;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Opcodes;


/**
 * Create by yiyonghao on 2020-08-20
 * Email: yiyonghao@bytedance.com
 */
public class AsmAnnotationVisitor extends AnnotationVisitor implements Opcodes {
    public AsmAnnotationVisitor(AnnotationVisitor annotationVisitor) {
        super(ASM7, annotationVisitor);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String name, String descriptor) {
        System.out.println(name +" "+descriptor);
        return av;
    }
}
