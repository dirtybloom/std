package com.std.engine;

/**
 * Created by dirtybloom on 07/01/17.
 */
public final class ContextConfiguration {

    protected int minWidth, minHeight, maxWidth, maxHeight;

    protected boolean debugCamera;

    public ContextConfiguration(int minWidth, int minHeight, int maxWidth, int maxHeight, boolean debugCamera) {
        this.minWidth = minWidth;
        this.minHeight = minHeight;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.debugCamera = debugCamera;
    }

}
