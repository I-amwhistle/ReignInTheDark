package huise.RITD;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class Tower {

    Main main;

    public Texture texture;
    public Sprite sprite;

    public Tower(Main main) {
        this.main = main;
        texture = new Texture("finder.png");
        sprite = new Sprite(texture);
    }


    public void render() {
        main.batch.begin();
        sprite.draw(main.batch);
        main.batch.end();
    }

}
