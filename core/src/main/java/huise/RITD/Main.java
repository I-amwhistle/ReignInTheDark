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

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(30, 30 * (Gdx.graphics.getWidth()/Gdx.graphics.getHeight()));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 10);
        camera.update();
        Gdx.input.setInputProcessor(new GestureDetector(new InputProcessorMain(this)));
        unit = new Unit(this);

    }

    @Override
    public void render() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.end();
        camera.zoom = 50;


        unit.render();

    }


    @Override
    public void dispose() {
        batch.dispose();
    }

}
