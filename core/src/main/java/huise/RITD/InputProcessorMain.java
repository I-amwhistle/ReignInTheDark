package huise.RITD;

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
        main.camera.position.add( -deltaX * dragSpeed, deltaY * dragSpeed,0);
        main.camera.update();
        return true;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if (button == 1) {
            Vector3 projectedCoords = main.camera.unproject(new Vector3(x, y, 0f));
            main.unit.moveTo(projectedCoords.x, projectedCoords.y);
        }
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
        main.camera.zoom += amountY * 0.1f;
        main.camera.zoom = MathUtils.clamp(main.camera.zoom, 0.1f, 100f);
        System.out.println("Zoom: " + main.camera.zoom);
        return true;
    }

}
