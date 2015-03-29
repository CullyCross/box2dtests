package com.box2d.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.box2d.game.box2d.GroundUserData;
import com.box2d.game.box2d.RunnerUserData;

/**
 * Created by cullycross on 3/29/15.
 */
public class WorldUtils {

    public static World createWorld() {
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body createGround(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));

        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.GROUND_WIDTH / 2, Constants.GROUND_HEIGHT / 2);

        body.createFixture(shape, Constants.GROUND_DENSITY);
        body.setUserData(new GroundUserData());
        shape.dispose();

        return body;
    }

    public static Body createRunner(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y)));

        Body body = world.createBody(bodyDef);
        body.setGravityScale(Constants.RUNNER_GRAVITY_SCALE);
        CircleShape shape = new CircleShape();
        shape.setRadius(Constants.RUNNER_RADIUS / 2);

        body.createFixture(shape, Constants.RUNNER_DENSITY);
        body.resetMassData();
        body.setUserData(new RunnerUserData());
        shape.dispose();

        return body;
    }

}
