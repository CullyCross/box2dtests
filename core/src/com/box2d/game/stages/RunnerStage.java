package com.box2d.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.box2d.game.actors.Ground;
import com.box2d.game.actors.Runner;
import com.box2d.game.utils.BodyUtils;
import com.box2d.game.utils.CustomObserver;
import com.box2d.game.utils.WorldUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by cullycross on 3/29/15.
 */
public class RunnerStage extends Stage implements ContactListener {

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

    private Rectangle mScreenRightSide;
    private Rectangle mScreenLeftSide;

    private Vector3 mTouchPoint;

    /**
     * shaman about this (public/private)
     */
    public List<CustomObserver> mCollisionObservers = new ArrayList<CustomObserver>();

    public RunnerStage() {
        setupWorld();
        setupCamera();
        setupTouchControlAreas();
        mRenderer = new Box2DDebugRenderer();
    }

    private void setupWorld() {
        mWorld = WorldUtils.createWorld();
        mWorld.setContactListener(this);
        setupGround();
        setupRunner();
    }

    private void setupRunner() {
        mRunner = new Runner(WorldUtils.createRunner(mWorld));
        addActor(mRunner);

        mCollisionObservers.add(mRunner);
    }

    private void setupGround() {
        mGround = new Ground(WorldUtils.createGround(mWorld));
        addActor(mGround);
    }

    private void setupTouchControlAreas() {
        mTouchPoint = new Vector3();
        mScreenLeftSide =
                new Rectangle(0, 0, getCamera().viewportWidth / 2, getCamera().viewportHeight);
        mScreenRightSide =
                new Rectangle(getCamera().viewportWidth / 2, 0, getCamera().viewportWidth / 2,
                    getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Need to get the actual coordinates
        translateScreenToWorldCoordinates(screenX, screenY);

        if (rightSideTouched(mTouchPoint.x, mTouchPoint.y)) {
            mRunner.handleInput(Runner.Input.RIGHT_TOUCH_DOWN);
        } else if(leftSideTouched(mTouchPoint.x, mTouchPoint.y)) {
            mRunner.handleInput(Runner.Input.LEFT_TOUCH_DOWN);
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        // Need to get the actual coordinates
        translateScreenToWorldCoordinates(screenX, screenY);

        if (rightSideTouched(mTouchPoint.x, mTouchPoint.y)) {
            mRunner.handleInput(Runner.Input.RIGHT_TOUCH_UP);
        } else if (leftSideTouched(mTouchPoint.x, mTouchPoint.y)) {
            mRunner.handleInput(Runner.Input.LEFT_TOUCH_UP);
        }

        return super.touchUp(screenX, screenY, pointer, button);
    }

    private void setupCamera() {
        mCamera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        mCamera.position.set(mCamera.viewportWidth / 2, mCamera.viewportHeight / 2, 0f);
        mCamera.update();
    }

    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(mTouchPoint.set(x, y, 0));
    }

    private boolean rightSideTouched(float x, float y) {
        return mScreenRightSide.contains(x, y);
    }

    private boolean leftSideTouched(float x, float y) {
        return mScreenLeftSide.contains(x, y);
    }

    public void notifyObservers() {
        for(CustomObserver obs : mCollisionObservers) {
            obs.update();
        }
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

    @Override
    public void beginContact(Contact contact) {

        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsGround(b)) ||
                (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsRunner(b))) {
            notifyObservers();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
