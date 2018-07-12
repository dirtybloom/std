package com.std.engine.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.std.engine.gamestate.GameState;

/**
 * Created by dirtybloom on 09/01/17.
 */

/**
 * AbstractSprite astrae un'entità, ne specifica la posizione
 * all'interno nel mondo,la sua velocità e la sua immagine.
 * fornisce anche tutti i metodi necessari per eseguire l'update
 * ed il rendering della stessa.
 */
public abstract class AbstractSprite implements ISprite {

    protected GameState gameState;

    private TextureRegion textureRegion;

    private Vector2 position = new Vector2(),
            velocity = new Vector2();

    private boolean hasToBeUpdated = true,
            hasToBeRendered = true,
            canCollide = true,
            isDisposed = false;

    public AbstractSprite(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean isDisposed() {
        return isDisposed;
    }

    @Override
    public boolean hasToBeRendered() {
        return hasToBeRendered;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(textureRegion, position.x, position.y);
    }

    @Override
    public boolean hasToBeUpdated() {
        return hasToBeUpdated;
    }

    @Override
    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
    }

    @Override
    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    @Override
    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    @Override
    public boolean canCollide() {
        return canCollide;
    }

    @Override
    public void collided(ICollidable collidable) {

    }

    @Override
    public Vector2 getCenter() {
        return new Vector2(position.x + textureRegion.getRegionWidth() / 2,
                position.y + textureRegion.getRegionHeight() / 2);
    }

    @Override
    public void setCenter(Vector2 center) {
        position.set(center.x - textureRegion.getRegionWidth() / 2,
                center.y - textureRegion.getRegionHeight() / 2);
    }

    @Override
    public Vector2 getVelocity() {
        return velocity;
    }

    @Override
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    @Override
    public void hasToBeUpdated(boolean hasToBeUpdated) {
        this.hasToBeUpdated = hasToBeUpdated;
    }

    @Override
    public void hasToBeRendered(boolean hasToBeRendered) {
        this.hasToBeRendered = hasToBeRendered;
    }

    @Override
    public void canCollide(boolean canCollide) {
        this.canCollide = canCollide;
    }

    @Override
    public void dispose() {
        this.isDisposed = true;
    }
}
