package huise.RITD;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Unit unit;
    public Unit unit2;
    public Unit[] units;

    public Tower tower;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(30, 30 * (Gdx.graphics.getWidth()/Gdx.graphics.getHeight()));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 10);
        camera.update();

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new InputProcessorMain(this));
        multiplexer.addProcessor(new GestureDetector(new InputProcessorMain(this)));
        Gdx.input.setInputProcessor(multiplexer);

        unit = new Unit(this);
        unit2 = new Unit(this);
        unit2.sprite.setPosition(100,300);
        units = new Unit[]{unit, unit2};
        camera.zoom = 50;

        tower = new Tower(this);
        tower.sprite.setPosition(0,600);

    }

    @Override
    public void render() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        unit.render();
        unit2.render();
        tower.render();

    }


    @Override
    public void dispose() {
        dispose();
    }

}
