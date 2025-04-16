package huise.RITD;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
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

    public Vector2 spritePosition;

    public boolean isSelected = false;
    public Vector2 velocity = new Vector2(0, 0);

    public ShapeRenderer sr;

    float rotationVelocity = 0f;

    public Unit(Main main) {
        this.main = main;
        texture = new Texture("Unit.png");
        sprite = new Sprite(texture);
        selectionTexture = new Texture("Selection.png");
        selection = new Sprite(selectionTexture);
        sr = new ShapeRenderer();
        sr.setColor(Color.ORANGE);
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

        sprite.setScale(1, 1.2f);;
        spritePosition = new Vector2(sprite.getX() + sprite.getWidth() / 2, sprite.getY());
        Vector3 mousePos = main.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0f));

        // face mouse pointer

        if (movingTarget != null) {
            float radians = (float) Math.atan2(movingTarget.y - spritePosition.y, movingTarget.x - spritePosition.x);
            if ((int)sprite.getRotation() != radians * 57f - 90f) {

                if (sprite.getRotation() > radians * 57f - 90f) rotationVelocity = 1;
                else rotationVelocity = 0;

            }
            System.out.println("rotation: " + sprite.getRotation() % 360 + ", expected:" + (radians * 57f - 90f));
        }




        sprite.rotate(rotationVelocity);

        // Unit moving

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
            sr.rectLine(sprite.getX() + sprite.getWidth() / 2, sprite.getY(), movingTarget.x, movingTarget.y, 30);
            sr.end();
        }

        // attacking a tower

        if (spritePosition.dst(main.tower.spritePosition) < 50 && main.tower.hp > 0) {
            System.out.println("Touching Tower hp:" + main.tower.hp);
            isMoving = false;
            main.tower.hp -= 1;

        }

        System.out.println(main.tower.spritePosition + " " + spritePosition);

    }

}
