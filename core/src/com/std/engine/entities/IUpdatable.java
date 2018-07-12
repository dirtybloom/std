package com.std.engine.entities;

/**
 * Created by dirtybloom on 09/01/17.
 */
public interface IUpdatable {

    boolean isDisposed();

    boolean hasToBeUpdated();

    void update(float delta);
}
