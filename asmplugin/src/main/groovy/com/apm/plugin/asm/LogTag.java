package com.apm.plugin.asm;

import org.gradle.api.Project;
import org.gradle.api.provider.Property;

public class LogTag {

    private final Property<String> tag;

    public LogTag(Project project) {
        tag = project.getObjects().property(String.class);
    }

    public Property<String> getTag() {
        return tag;
    }
}
