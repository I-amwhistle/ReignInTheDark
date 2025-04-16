package huise.RITD;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

public class Tower {

    Main main;

    public Texture texture;
    public Sprite sprite;

    public Texture hpTexture;
    public Sprite hpSprite;

    public float maxHp = 100;
    public float hp = maxHp;

    Vector2 spritePosition;

    public Tower(Main main) {
        this.main = main;
        texture = new Texture("libgdx.png");
        sprite = new Sprite(texture);
        spritePosition = new Vector2(sprite.getX() + sprite.getWidth() / 2, sprite.getY());

        hpTexture = new Texture("hpBar.png");
        hpSprite = new Sprite(hpTexture);
        hpSprite.scale(2);
    }


    public void render() {

        if (hp <= 0) return;  // this statement is for when tower is killed
        main.batch.begin();
        sprite.draw(main.batch);
        hpSprite.draw(main.batch);
        main.batch.end();
        spritePosition = new Vector2(sprite.getX() + sprite.getWidth() / 2, sprite.getY());
        hpSprite.setPosition(sprite.getX(), sprite.getY() + 100);
        hpSprite.setScale(hp / 100 * 2, hpSprite.getScaleY());
        if (hp <= 0) return;  // this statement is for disabling the rect



    }

}
