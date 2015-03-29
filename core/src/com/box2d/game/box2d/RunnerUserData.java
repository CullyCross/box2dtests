package com.box2d.game.box2d;

import com.badlogic.gdx.math.Vector2;
import com.box2d.game.enums.UserDataType;
import com.box2d.game.utils.Constants;

/**
 * Created by cullycross on 3/29/15.
 */
public class RunnerUserData extends UserData {

    private Vector2 mJumpingLinearImpulse;
    private final Vector2 mRunningPosition =
            new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y);
    private final Vector2 mDodgePosition =
            new Vector2(Constants.RUNNER_DODGE_X, Constants.RUNNER_DODGE_Y);

    public RunnerUserData() {

        super();
        mJumpingLinearImpulse = Constants.RUNNER_JUMPING_LINEAR_IMPULSE;
        mUserDataType = UserDataType.RUNNER;
    }

    public Vector2 getJumpingLinearImpulse() {
        return mJumpingLinearImpulse;
    }

    public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse) {
        mJumpingLinearImpulse = jumpingLinearImpulse;
    }

    public float getDodgeAngle() {
        // In radians
        return (float) (-90f * (Math.PI / 180f));
    }

    public Vector2 getRunningPosition() {
        return mRunningPosition;
    }

    public Vector2 getDodgePosition() {
        return mDodgePosition;
    }
}
