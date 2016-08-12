package com.gmail.gephery.teleport.file;

import java.util.UUID;

/**
 * Created by maxgr on 8/9/2016.
 */
public class FilePath
{

    public static final String DIRECTION_ROOT = "direction";
    public static final String SIGN_ROOT = "sign";
    public static final String WORLD_ROOT = "world";

    public static String worlds()
    {
        return WORLD_ROOT;
    }

    public static String directionTypes()
    {
        return DIRECTION_ROOT;
    }

    public static String usersHome()
    {
        return directionTypes() + ".user";
    }

    public static String townsHome()
    {
        return directionTypes() + ".town";
    }

    public static String userHome(UUID user)
    {
        return usersHome() + "." + user.toString();
    }

    public static String townDirection(String townName)
    {
        return townsHome() + "." + townName;
    }

    public static String signs()
    {
        return SIGN_ROOT;
    }

    public static String sign(String signUUID)
    {
        return signs() + "." + signUUID;
    }

    public static String signCommands(String signUUID)
    {
        return sign(signUUID) + ".commands";
    }



}
