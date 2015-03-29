package com.box2d.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.box2d.game.box2d.GroundUserData;

/**
 * Created by cullycross on 3/29/15.
 */
public class Ground extends BaseActor {
    public Ground(Body body) {
        super(body);
    }

    @Override
    public GroundUserData getUserData() {
        return (GroundUserData) mUserData;
    }
}
