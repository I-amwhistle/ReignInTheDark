package huise.RITD;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public class Unit {

    public Texture texture;
    public Sprite sprite;

    public Texture selectionTexture;
    public Sprite selection;

    boolean isMoving = false;
    Vector2 movingTarget = null;

    float speed = 6;

    Main main;

    public boolean isSelected = false;
    public Vector2 velocity = new Vector2(0, 0);

    public ShapeRenderer sr;

    public Unit(Main main) {
        this.main = main;
        texture = new Texture("libgdx.png");
        sprite = new Sprite(texture);
        selectionTexture = new Texture("Selection.png");
        selection = new Sprite(selectionTexture);
        sr = new ShapeRenderer();
        sr.setColor(Color.BLACK);
        sr.setProjectionMatrix(main.camera.combined);
    }

    public void moveTo(float x, float y) {

        if (! isSelected) return;
        movingTarget = new Vector2(x, y);
        isMoving = true;
        Vector2 spritePosition = new Vector2(sprite.getX() + sprite.getWidth() / 2, sprite.getY());
        velocity = new Vector2(x, y).sub(spritePosition).nor();
    }

    public void render() {

        // Unit moving

        Vector2 spritePosition = new Vector2(sprite.getX() + sprite.getWidth() / 2, sprite.getY());

        if (! isMoving) {
            velocity = Vector2.Zero;
        }

        if (movingTarget != null && spritePosition.dst(movingTarget) < 10) {
            isMoving = false;
        }

        main.batch.begin();

        sprite.draw(main.batch);

        if (isSelected)
            selection.draw(main.batch);

        main.batch.end();

        sprite.translate(velocity.x * speed, velocity.y * speed);
        selection.setPosition(sprite.getX(), sprite.getY());

        // draw line from Unit to movingTarget

        if (isMoving) {
            sr.setProjectionMatrix(main.camera.combined); // Update projection matrix
            sr.begin(ShapeType.Filled);
            sr.rectLine(sprite.getX() + sprite.getWidth() / 2, sprite.getY(), movingTarget.x, movingTarget.y, 10);
            sr.end();
        }

    }

}
