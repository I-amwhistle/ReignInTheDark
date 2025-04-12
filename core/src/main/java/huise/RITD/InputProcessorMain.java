package huise.RITD;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class InputProcessorMain implements com.badlogic.gdx.input.GestureDetector.GestureListener {

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
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {
        return;
    }
}
