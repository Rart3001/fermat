package com.bitdubai.smartwallet.core.system.user;

/**
 * Created by ciencias on 30.12.14.
 */
public class LocalCommercialUser implements LocalUser, CommercialUser {

    private String mId;
    private LoginType mLoginType;

    public LocalCommercialUser(String userId) {
        mId = userId;
        mLoginType = LoginType.NONE;
    }

    public LoginType getLoginType() {
        return mLoginType;
    }

    @java.lang.Override
    public boolean Login() {
        return false;
    }

    @java.lang.Override
    public boolean Login(int pPIN) {
        return false;
    }

    @java.lang.Override
    public boolean Login(String pPassword) {
        return false;
    }
}
