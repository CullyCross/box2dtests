package com.box2d.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.box2d.game.box2d.RunnerUserData;
import com.box2d.game.utils.CustomObserver;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by cullycross on 3/29/15.
 */
public class Runner extends BaseActor implements CustomObserver {

    private State mRunnerState;

    private static RunState sRunState;
    private static JumpState sJumpState;
    private static DodgeState sDodgeState;

    private interface State {
        void handleInput(Runner runner,Input input);
        void update(Runner runner);
    }

    public enum Input {
        RIGHT_TOUCH_DOWN,
        RIGHT_TOUCH_UP,
        LEFT_TOUCH_DOWN,
        LEFT_TOUCH_UP
    }

    private class RunState implements State {

        @Override
        public void handleInput(Runner runner, Input input) {

            if(input == Input.RIGHT_TOUCH_DOWN) {

                mBody.applyLinearImpulse(
                        getUserData().getJumpingLinearImpulse(),
                        mBody.getWorldCenter(),
                        true);
                mRunnerState = sJumpState;
            } else if(input == Input.LEFT_TOUCH_DOWN) {

                mBody.setTransform(
                        getUserData().getDodgePosition(),
                        getUserData().getDodgeAngle()
                );
                mRunnerState = sDodgeState;
            }
        }

        @Override
        public void update(Runner runner) {

        }
    }

    private class JumpState implements State {

        @Override
        public void handleInput(Runner runner, Input input) {

        }

        @Override
        public void update(Runner runner) {

        }

        public void land() {
            mRunnerState = sRunState;
        }
    }

    private class DodgeState implements State {

        @Override
        public void handleInput(Runner runner, Input input) {
            if(input == Input.RIGHT_TOUCH_DOWN ||
                    input == Input.LEFT_TOUCH_UP ||
                    input == Input.RIGHT_TOUCH_UP) {

                mBody.setTransform(
                        getUserData().getRunningPosition(), 0f
                );
                mRunnerState = sRunState;
            }
        }

        @Override
        public void update(Runner runner) {

        }
    }

    public Runner(Body body) {
        super(body);

        sDodgeState = new DodgeState();
        sJumpState = new JumpState();
        sRunState = new RunState();

        mRunnerState = sRunState;
    }

    @Override
    public RunnerUserData getUserData() {
        return (RunnerUserData) mUserData;
    }

    public void handleInput(Input input) {
        mRunnerState.handleInput(this, input);
    }

    /**
     * pretty sad realization
     */
    @Override
    public void update() {
        if(mRunnerState instanceof JumpState) {
            ((JumpState) mRunnerState).land();
        }
    }
}
