package huise.RITD;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

public class Tower {

    Main main;

    public Texture texture;
    public Sprite sprite;

    public float maxHp = 100;
    public float hp = maxHp;

    Vector2 spritePosition;

    public Tower(Main main) {
        this.main = main;
        texture = new Texture("libgdx.png");
        sprite = new Sprite(texture);
        sprite.setScale(0.2f);
        spritePosition = new Vector2(sprite.getX() + sprite.getWidth() / 2, sprite.getY());
    }


    public void render() {
        main.batch.begin();
        sprite.draw(main.batch);
        main.batch.end();
        spritePosition = new Vector2(sprite.getX() + sprite.getWidth() / 2, sprite.getY());
    }

}
