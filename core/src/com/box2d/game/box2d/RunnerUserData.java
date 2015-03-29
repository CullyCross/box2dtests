package com.box2d.game.box2d;

import com.badlogic.gdx.math.Vector2;
import com.box2d.game.enums.UserDataType;
import com.box2d.game.utils.Constants;

/**
 * Created by cullycross on 3/29/15.
 */
public class RunnerUserData extends UserData {

    private Vector2 mJumpingLinearImpulse;

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
}
