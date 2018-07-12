package com.std.engine.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;

/**
 * Created by dirtybloom on 14/11/2016.
 */

/**
 * ShapeTextureChache permette di creare semplici figure
 * geometriche e di tenerle in memoria per un uso futuro
 */
public class ShapeTextureCache implements Disposable {

    private static final ShapeTextureCache instance = new ShapeTextureCache();
    private HashMap<Integer, Texture> filledRectangleTexture;
    private HashMap<String, Texture> rectangleTexture;
    private HashMap<String, Texture> circleTexture;
    private HashMap<String, Texture> filledCircleTexture;

    private ShapeTextureCache() {
        filledRectangleTexture = new HashMap<Integer, Texture>();
        rectangleTexture = new HashMap<String, Texture>();
        circleTexture = new HashMap<String, Texture>();
        filledCircleTexture = new HashMap<String, Texture>();
    }

    public static final ShapeTextureCache getInstance() {
        return instance;
    }

    public Texture getFilledRectangleTexture(Color color) {
        int bits = color.toIntBits();

        if (!filledRectangleTexture.containsKey(bits)) {
            Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            pixmap.setColor(color);
            pixmap.fillRectangle(0, 0, 1, 1);
            filledRectangleTexture.put(bits, new Texture(pixmap));
            pixmap.dispose();
        }
        return filledRectangleTexture.get(bits);
    }

    public Texture getRectagleTexture(Color color, int width, int height, int lineHeight) {
        int bits = color.toIntBits();

        String key = "" + bits + "," + width + "," + height + "," + lineHeight;

        if (!rectangleTexture.containsKey(key)) {
            Pixmap pixmap = new Pixmap(width + 1, height + 1, Pixmap.Format.RGBA8888);
            pixmap.setColor(color);
            for (int i = 0; i < lineHeight; i++) {
                pixmap.drawRectangle(i, i, width - (i * 2), height - (i * 2));
            }

            rectangleTexture.put(key, new Texture(pixmap));
            pixmap.dispose();
        }
        return rectangleTexture.get(key);
    }

    public Texture getCircleTexture(Color color, int radius, int lineHeight) {

        int bits = color.toIntBits();

        String key = "" + bits + "," + radius + "," + lineHeight;

        if (!circleTexture.containsKey(key)) {
            Pixmap pixmap = new Pixmap((radius * 2) + 1, (radius * 2) + 1, Pixmap.Format.RGBA8888);
            pixmap.setColor(color);
            for (int i = 0; i < lineHeight; i++) {
                pixmap.drawCircle(radius, radius, radius - i);
            }
            circleTexture.put(key, new Texture(pixmap));
            pixmap.dispose();
        }
        return circleTexture.get(key);
    }

    public Texture getFilledCircleTexture(Color color, int radius) {
        int bits = color.toIntBits();

        String key = "" + bits + "," + radius;

        if (!filledCircleTexture.containsKey(key)) {
            Pixmap pixmap = new Pixmap((radius * 2) + 1, (radius * 2) + 1, Pixmap.Format.RGBA8888);
            pixmap.setColor(color);
            pixmap.drawCircle(radius, radius, radius);
            filledCircleTexture.put(key, new Texture(pixmap));
            pixmap.dispose();
        }
        return filledCircleTexture.get(key);
    }

    @Override
    public void dispose() {
        filledRectangleTexture:
        for (Texture texture : filledRectangleTexture.values()) {
            texture.dispose();
        }
        filledRectangleTexture.clear();

        rectangleTexture:
        for (Texture texture : rectangleTexture.values()) {
            texture.dispose();
        }
        rectangleTexture.clear();

        circleTexture:
        for (Texture texture : circleTexture.values()) {
            texture.dispose();
        }
        circleTexture.clear();

        filledCircleTexture:
        for (Texture texture : filledCircleTexture.values()) {
            texture.dispose();
        }
        filledCircleTexture.clear();
    }
}
