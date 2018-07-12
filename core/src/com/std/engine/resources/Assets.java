package com.std.engine.resources;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Created by dirtybloom on 14/12/16.
 */

/**
 * Assets offre dei metodi per la gestione delle risorse,
 * immagini,suoni e font.
 * Assets è un Singleton
 * <p>
 * Ogni risorsa deve prima essere caricata nell'AssetManager,
 * una volta caricata, è possibile caricare effettivamente
 * ogni file.
 */
public class Assets implements Disposable {

    private static final Assets instance = new Assets();
    private AssetManager assetManager;

    private ObjectMap<String, BitmapFont> fontsBySize;

    private Assets() {
        assetManager = new AssetManager();
        fontsBySize = new ObjectMap<String, BitmapFont>();
        FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
    }

    public static final Assets getInstance() {
        return instance;
    }

    public void loadTextureAtlas(String atlasName) {
        assetManager.load(atlasName, TextureAtlas.class);
    }

    public void loadTexture(String textureName){
        assetManager.load(textureName,Texture.class);
    }

    public void loadFreeTypeFont(String fileName, int size) {
        FreetypeFontLoader.FreeTypeFontLoaderParameter freeTypeFontLoaderParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        freeTypeFontLoaderParameter.fontFileName = fileName;
        freeTypeFontLoaderParameter.fontParameters.size = size;
        assetManager.load(fileName, BitmapFont.class, freeTypeFontLoaderParameter);
    }

    public void loadTiledMap(String mapName){
        assetManager.load(mapName,TiledMap.class);
    }

    public void loadSound(String soundName) {
        assetManager.load(soundName, Sound.class);
    }

    public void loadMusic(String musicName) {
        assetManager.load(musicName, Music.class);
    }

    /**
     * @return un valore compre tra 0 e 1, indica la percentuale di completamento
     * delle operazioni di carimaneto. loadNext() deve essere invocato fino a che
     * il valore di ritorno non è 1 che vuol dire che tutto è stato caricato
     */
    public float loadNext() {
        assetManager.update();
        return getProgress();
    }

    public float getCompletedPercentage() {
        return assetManager.getProgress();
    }

    public boolean hasDone() {
        return getCompletedPercentage() >= 1f;
    }

    public float getProgress() {
        return assetManager.getProgress();
    }

    public TextureAtlas getAtlas(String atlasName) {
        return assetManager.get(atlasName, TextureAtlas.class);
    }

    public TextureRegion getTextureRegion(String regionName, TextureAtlas textureAtlas) {
        return textureAtlas.findRegion(regionName);
    }

    public TextureRegion getTextureRegion(String regionName, String atlasName) {
        return getTextureRegion(regionName, getAtlas(atlasName));
    }

    public Texture getTexture(String textureName){
        return assetManager.get(textureName,Texture.class);
    }

    public Sound getSound(String soundName) {
        return assetManager.get(soundName, Sound.class);
    }

    public Music getMusic(String musicName) {
        return assetManager.get(musicName, Music.class);
    }

    public BitmapFont getFont(String fontName) {
        return assetManager.get(fontName, BitmapFont.class);
    }

    public TiledMap getMap(String mapName){ return assetManager.get(mapName,TiledMap.class); }
    @Override
    public void dispose() {
        assetManager.clear();
    }
}
