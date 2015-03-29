package com.box2d.game.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.box2d.game.box2d.UserData;
import com.box2d.game.enums.UserDataType;

/**
 * Created by cullycross on 3/29/15.
 */
public class BodyUtils {

    private BodyUtils(){}

    public static boolean bodyIsRunner(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.RUNNER;
    }

    public static boolean bodyIsGround(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.GROUND;
    }
}
