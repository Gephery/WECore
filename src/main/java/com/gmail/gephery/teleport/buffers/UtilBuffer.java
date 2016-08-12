package com.gmail.gephery.teleport.buffers;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by maxgr on 8/11/2016.
 */
public class UtilBuffer
{
    public static JavaPlugin plugin;

    public static void setUpUtilBuffer(JavaPlugin inPlugin)
    {
        plugin = inPlugin;
    }
}
