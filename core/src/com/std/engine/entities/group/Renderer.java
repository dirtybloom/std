package com.std.engine.entities.group;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.std.engine.entities.IRenderable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dirtybloom on 09/01/17.
 */

/**
 * Renderer permette di disegnare ogni entità, di tipo IRenderable,
 * inserita nel rispettivo GameState.
 */
public class Renderer implements Disposable {

    private List<IRenderable> renderableList;

    public Renderer() {
        renderableList = new ArrayList<IRenderable>();
    }

    public void add(IRenderable renderable) {
        renderableList.add(renderable);
    }

    public void render(SpriteBatch spriteBatch) {
        Iterator<IRenderable> it = renderableList.iterator();
        while (it.hasNext()) {
            IRenderable renderable = it.next();
            if (renderable.isDisposed()) it.remove();
            else if (renderable.hasToBeRendered()) renderable.render(spriteBatch);
        }
    }

    @Override
    public void dispose() {
        renderableList.clear();
    }
}
