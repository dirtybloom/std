package com.std.engine.entities.group;

import com.badlogic.gdx.utils.Disposable;
import com.std.engine.entities.IUpdatable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dirtybloom on 09/01/17.
 */

/**
 * Updater si uccupare di eseguire l'update di ogni entità,
 * di tipo IUpdatable, inserita nel rispettivo GameState.
 */
public class Updater implements Disposable {

    private List<IUpdatable> updatableList, toAdd;

    public Updater() {
        updatableList = new ArrayList<IUpdatable>();
        toAdd = new ArrayList<IUpdatable>();
    }

    public void add(IUpdatable updatable) {
        toAdd.add(updatable);
    }

    public void update(float delta) {
        if (!toAdd.isEmpty()) {
            updatableList.addAll(toAdd);
            toAdd.clear();
        }

        Iterator<IUpdatable> it = updatableList.iterator();
        while (it.hasNext()) {
            IUpdatable updatable = it.next();
            if (updatable.isDisposed()) it.remove();
            else if (updatable.hasToBeUpdated()) updatable.update(delta);
        }
    }

    @Override
    public void dispose() {
        toAdd.clear();
        updatableList.clear();
    }
}
