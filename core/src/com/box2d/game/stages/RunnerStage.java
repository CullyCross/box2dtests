package com.box2d.game.stages;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.box2d.game.actors.Ground;
import com.box2d.game.actors.Runner;
import com.box2d.game.utils.WorldUtils;

/**
 * Created by cullycross on 3/29/15.
 */
public class RunnerStage extends Stage {

    // This will be our viewport measurements while working with the debug renderer
    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;

    private World mWorld;
    private Ground mGround;
    private Runner mRunner;

    private final float TIME_STEP = 1 / 300f;
    private float mAccumulator = 0f;

    private OrthographicCamera mCamera;
    private Box2DDebugRenderer mRenderer;

    public RunnerStage() {
        setupWorld();
        mRenderer = new Box2DDebugRenderer();
        setupCamera();
    }

    private void setupWorld() {
        mWorld = WorldUtils.createWorld();
        setupGround();
        setupRunner();
    }

    private void setupRunner() {
        mRunner = new Runner(WorldUtils.createRunner(mWorld));
        addActor(mRunner);
    }

    private void setupGround() {
        mGround = new Ground(WorldUtils.createGround(mWorld));
        addActor(mGround);
    }

    private void setupCamera() {
        mCamera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        mCamera.position.set(mCamera.viewportWidth / 2, mCamera.viewportHeight / 2, 0f);
        mCamera.update();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        mAccumulator += delta;

        while (mAccumulator >= delta) {
            mWorld.step(TIME_STEP, 6, 2);
            mAccumulator -= TIME_STEP;
        }

        //TODO: interpolation
    }

    @Override
    public void draw() {
        super.draw();
        mRenderer.render(mWorld, mCamera.combined);
    }
}
