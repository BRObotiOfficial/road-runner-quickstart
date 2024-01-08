package org.firstinspires.ftc.teamcode;

public class KeyState {

    private boolean previousState = false;
    private boolean currentState = false;

    // update the current state to the given value
    public void update(boolean newState) {
        previousState = currentState;
        currentState = newState;
    }

    // returns whether or not the key is pressed
    public boolean isKeyPressed() {
        return currentState;
    }

    // returns whether or not the key is released
    public boolean isKeyReleased() {
        return !currentState;
    }

    // returns whether or not the key was just pressed (this frame)
    public boolean isKeyJustPressed() {
        return currentState && !previousState;
    }

    // returns whether or not the key was just released (this frame)
    public boolean isKeyJustReleased() {
        return previousState && !currentState;
    }

}