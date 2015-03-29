package com.box2d.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.box2d.game.box2d.RunnerUserData;

/**
 * Created by cullycross on 3/29/15.
 */
public class Runner extends BaseActor {

    private State mRunnerState;

    private static RunState sRunState;
    private static JumpState sJumpState;
    private static DodgeState sDodgeState;

    private interface State {
        void handleInput(Runner runner,Input input);
        void update(Runner runner);
    }

    private enum Input {
        TOUCH_DOWN,
        TOUCH_UP
    }

    private class RunState implements State {

        @Override
        public void handleInput(Runner runner, Input input) {

            mBody.applyLinearImpulse(
                    getUserData().getJumpingLinearImpulse(),
                    mBody.getWorldCenter(),
                    true);
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
    }

    private class DodgeState implements State {

        @Override
        public void handleInput(Runner runner, Input input) {

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

    /**
     * todo: make a handleInput method with body:
     * mRunnerState.doAction(this);
     */

}
