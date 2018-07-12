package com.std.engine;

/**
 * Created by dirtybloom on 07/01/17.
 */
public class ContextConfigurationBuilder {
    private int minWidth;
    private int minHeight;
    private int maxWidth;
    private int maxHeight;

    private boolean debugCamera;

    public ContextConfigurationBuilder setMinWidth(int minWidth) {
        this.minWidth = minWidth;
        return this;
    }

    public ContextConfigurationBuilder setMinHeight(int minHeight) {
        this.minHeight = minHeight;
        return this;
    }

    public ContextConfigurationBuilder setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
        return this;
    }

    public ContextConfigurationBuilder setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
        return this;
    }

    public ContextConfigurationBuilder setDebugCamera(boolean debugCamera) {
        this.debugCamera = debugCamera;
        return this;
    }

    public ContextConfiguration createContextConfiguration() {
        return new ContextConfiguration(minWidth, minHeight, maxWidth, maxHeight, debugCamera);
    }
}