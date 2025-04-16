package huise.RITD;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class InputProcessorMain implements com.badlogic.gdx.input.GestureDetector.GestureListener, InputProcessor {

    Main main;
    float dragSpeed = 3f;

    public InputProcessorMain(Main main) {
        this.main = main;
    }



    @Override
    public boolean pan (float x, float y, float deltaX, float deltaY) {
        main.isDragging = true;
        main.camera.position.add( -deltaX * dragSpeed, deltaY * dragSpeed,0);
        main.camera.update();
        return true;
    }


    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        Vector3 touchPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        main.camera.unproject(touchPoint);

        for (int i = 0; i < main.units.length; i ++) {
            switch (button) {
                case (0): // Mouse Left

                    if (main.units[i].sprite.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                        System.out.println("Unit Selected." + sel);
                        sel += 1;
                        main.units[i].isSelected = true;
                    } else {
                        main.units[i].isSelected = false;
                    }
                    break;

                case (1): // Mouse Right

                    main.units[i].moveTo(touchPoint.x, touchPoint.y);
                    break;
            }
        }

        System.out.println(button);
        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        main.camera.translate(new Vector3(0f, 0f, distance));
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {
        return;
    }



    @Override
    public boolean keyDown(int keycode) {
        return false;
    }



    @Override
    public boolean keyUp(int keycode) {
        return false;
    }



    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    int sel = 0;

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }



    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;

    }



    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }



    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }



    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }



    @Override
    public boolean scrolled(float amountX, float amountY) {
        main.camera.zoom += amountY * 10 * Gdx.graphics.getDeltaTime();
        main.camera.zoom = MathUtils.clamp(main.camera.zoom, 0.1f, 100f);
        System.out.println("Zoom: " + main.camera.zoom);
        return true;
    }

}
