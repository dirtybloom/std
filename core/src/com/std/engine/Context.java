package com.std.engine;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.std.engine.camera.CameraDebugger;
import com.std.engine.camera.MultipleVirtualViewportBuilder;
import com.std.engine.camera.OrthographicCameraWithVirtualViewport;
import com.std.engine.camera.VirtualViewport;
import com.std.engine.gamestate.GameStateManager;
import com.std.engine.resources.Assets;
import com.std.engine.utils.ShapeTextureCache;

/**
 * Created by dirtybloom on 07/01/17.
 */

/**
 * Context rappresenta il cuore dell'applicazione,
 */
public class Context implements ApplicationListener {

    private GameStateManager gameStateManager;

    private MultipleVirtualViewportBuilder viewportBuilder;
    private VirtualViewport viewport;
    private OrthographicCameraWithVirtualViewport camera;

    private SpriteBatch spriteBatch;

    private CameraDebugger cameraDebugger;

    /**
     * @param contextConfiguration contiene tutte le informazini necessarie
     *                             per configurare la camera
     */
    public Context(ContextConfiguration contextConfiguration) {
        gameStateManager = new GameStateManager();

        viewportBuilder = new MultipleVirtualViewportBuilder(contextConfiguration.minWidth,
                contextConfiguration.minHeight,
                contextConfiguration.maxWidth,
                contextConfiguration.maxHeight);

        if (contextConfiguration.debugCamera) {
            cameraDebugger = new CameraDebugger(contextConfiguration.minWidth,
                    contextConfiguration.minHeight,
                    contextConfiguration.maxWidth,
                    contextConfiguration.maxHeight);
        }
    }

    /**
     * Questo metodo viene invocato automaticamente dal framework all'avvio
     * dell'applicazione; Quì viene instanziato lo spritebatch e viene configurata
     * la camera
     */
    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        viewport = viewportBuilder.getVirtualViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera = new OrthographicCameraWithVirtualViewport(viewport);
        camera.position.set(0f, 0f, 0f);
    }

    /**
     * Questo metodo viene invocato automaticamente dal framweork all'avvio
     * dell'applicazione e ogni volta che la finestra di gioco viene ridimensionata
     *
     * @param width  specifica la larghezza del contesto grafico
     * @param height specifica l'altezza del contesto grafico
     */
    @Override
    public void resize(int width, int height) {
        viewport = viewportBuilder.getVirtualViewport(width, height);
        camera.setVirtualViewport(viewport);
        camera.updateViewport();
        camera.position.set(0, 0, 0f);
    }

    /**
     * Questo metodo viene invocato automaticamente dal framework ad ogni "ciclo",
     * Effettua le operazioni di update e rendering
     */
    @Override
    public void render() {
        gameStateManager.update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        if (cameraDebugger != null) cameraDebugger.render(spriteBatch);
        gameStateManager.render(spriteBatch);
        spriteBatch.end();
    }

    /**
     * Questo metodo viene invocato automaticamente dal framework ogni volta
     * che si entra in uno stato di pause
     */
    @Override
    public void pause() {
        gameStateManager.pause();
    }

    /**
     * Questo metodo viene invocato automaticamente dal framework ogni volta
     * che si torna da uno stato di pausa
     */
    @Override
    public void resume() {
        gameStateManager.resume();
    }

    /**
     * Questo metodo viene invocato automaticamente dal framework
     * quando si esce dall'applicazione
     */
    @Override
    public void dispose() {
        spriteBatch.dispose();
        gameStateManager.dispose();
        ShapeTextureCache.getInstance().dispose();
        Assets.getInstance().dispose();
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public OrthographicCameraWithVirtualViewport getCamera() {
        return camera;
    }

    public float getOriginX(){
        return -viewport.getVirtualWidth() * .5f;
    }

    public float getOriginY(){
        return -viewport.getVirtualHeight() * .5f;
    }

    public float getWidth(){
        return viewport.getVirtualWidth();
    }

    public float getHeight(){
        return viewport.getVirtualHeight();
    }
}
