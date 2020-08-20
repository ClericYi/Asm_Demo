package com.example.buildsrc;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Create by yiyonghao on 2020-08-08
 * Email: yiyonghao@bytedance.com
 */
public class AsmPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        System.out.println("===========  doing  ============");
        AppExtension appExtension = project.getExtensions().findByType(AppExtension.class);
        appExtension.registerTransform(new AsmTransform(project));
    }
}
