package com.box2d.game.box2d;

import com.box2d.game.enums.UserDataType;

/**
 * Created by cullycross on 3/29/15.
 */
public abstract class UserData {

    protected UserDataType mUserDataType;

    public UserData() {

    }

    public UserDataType getUserDataType() {
        return mUserDataType;
    }
}
