package com.box2d.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.box2d.game.box2d.UserData;

/**
 * Created by cullycross on 3/29/15.
 */
public abstract class BaseActor extends Actor {

    protected Body mBody;
    protected UserData mUserData;

    public BaseActor(Body body) {
        this.mBody = body;
        this.mUserData = (UserData) mBody.getUserData();
    }

    public abstract UserData getUserData();
}
