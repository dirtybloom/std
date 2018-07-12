package com.std.engine.entities.group;

import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dirtybloom on 09/01/17.
 */
public class CollisionManager implements Disposable {

    private Map<String, CollisionPair> collisionPairMap;
    private List<CollisionPair> collisionPairList;

    public CollisionManager() {
        collisionPairMap = new HashMap<String, CollisionPair>();
        collisionPairList = new ArrayList<CollisionPair>();
    }

    public CollisionPair getCollisionPair(String name) {
        return collisionPairMap.get(name);
    }

    public CollisionPair removeCollisionManager(String name) {
        CollisionPair collisionPair = collisionPairMap.remove(name);
        if (collisionPair != null) {
            collisionPairList.remove(collisionPair);
        }
        return collisionPair;
    }

    public void addCollisionPair(CollisionPair collisionPair) {
        collisionPairMap.put(collisionPair.getName(), collisionPair);
        collisionPairList.add(collisionPair);
    }

    public CollisionPair newCollisionPair(String newCollisionPairName) {
        CollisionPair collisionPair = collisionPairMap.get(newCollisionPairName);
        if (collisionPair == null) {
            collisionPair = new CollisionPair(newCollisionPairName);
            addCollisionPair(collisionPair);
        }
        return collisionPair;
    }

    public void checkCollisions() {
        for (CollisionPair collisionPair : collisionPairList) {
            collisionPair.checkCollisions();
        }
    }

    @Override
    public void dispose() {
        collisionPairMap.clear();
        for (CollisionPair collisionPair : collisionPairList) {
            collisionPair.dispose();
        }
        collisionPairList.clear();
    }
}
