package com.std.engine.entities.group;

import com.badlogic.gdx.utils.Disposable;
import com.std.engine.entities.ICollidable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dirtybloom on 09/01/17.
 */

/**
 * CollisionPair determina se si sono verificate collisioni tra
 * gli elementi contenuti nei due gruppi, se queste avvengono,
 * viene notificato,alle entità in questione, che c'è stato un
 * contatto.
 */
public class CollisionPair implements Disposable {

    private String name;

    private List<ICollidable> firstGroup;
    private List<ICollidable> secondGroup;

    public CollisionPair(String name) {
        this.name = name;
        this.firstGroup = new ArrayList<ICollidable>();
        this.secondGroup = new ArrayList<ICollidable>();
    }

    public String getName() {
        return name;
    }

    public void addToFirstGroup(ICollidable collidable) {
        firstGroup.add(collidable);
    }

    public void addToSecondGroup(ICollidable collidable) {
        secondGroup.add(collidable);
    }

    public void checkCollisions() {
        Iterator<ICollidable> it1 = firstGroup.iterator();
        while (it1.hasNext()) {
            ICollidable first = it1.next();
            if (first.isDisposed()) {
                it1.remove();
            } else if (first.canCollide()) {
                Iterator<ICollidable> it2 = secondGroup.iterator();
                while (!first.isDisposed() && it2.hasNext()) {
                    ICollidable second = it2.next();
                    if (second.isDisposed()) {
                        it2.remove();
                    } else if (second.canCollide()) {
                        if (collided(first, second)) {
                            first.collided(second);
                            second.collided(first);
                        }
                    }
                }
            }
        }
    }

    private boolean collided(ICollidable first, ICollidable second) {
        float x1 = first.getPosition().x;
        float y1 = first.getPosition().y;
        float w1 = x1 + first.getTextureRegion().getRegionWidth();
        float h1 = y1 + first.getTextureRegion().getRegionHeight();

        float x2 = second.getPosition().x;
        float y2 = second.getPosition().y;
        float w2 = x2 + second.getTextureRegion().getRegionWidth();
        float h2 = y2 + second.getTextureRegion().getRegionHeight();

        float rx = Math.max(x1, x2);
        float ry = Math.max(y1, y2);
        float rw = Math.min(w1, w2) - rx;
        float rh = Math.min(h1, h2) - ry;

        return !(rw <= 0 || rh <= 0);
    }

    @Override
    public void dispose() {
        firstGroup.clear();
        secondGroup.clear();
    }
}