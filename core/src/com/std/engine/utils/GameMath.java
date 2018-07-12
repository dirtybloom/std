package com.std.engine.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by dirtybloom on 14/12/16.
 */
public class GameMath {

    private static int[] sinTable;
    private static int[] cosTable;

    static {
        sinTable = new int[360];
        cosTable = new int[360];
        for (int i = 0; i < 360; i++) {
            double angle = 2 * Math.PI * i / 360;
            sinTable[i] = (int) (256 * Math.sin(angle));
            cosTable[i] = (int) (256 * Math.cos(angle));
        }
    }

    public static int[] getSinTable() {
        return sinTable;
    }

    public static int[] getCosTable() {
        return cosTable;
    }

    public static Vector2 getCentre(float x, float y, float width, float height) {
        return new Vector2(x + width / 2, y + height / 2);
    }

    public static float lerp(float v0, float v1, float t) {
        return (1f - t) * v0 + t * v1;
    }
}
