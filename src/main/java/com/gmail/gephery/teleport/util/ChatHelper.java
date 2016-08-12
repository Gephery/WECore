package com.gmail.gephery.teleport.util;

import org.bukkit.Color;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Map;

/**
 * Created by maxgr on 8/11/2016.
 */
public class ChatHelper
{
    public static String pluginName;
    public static Color pluginNameColor,
                        pluginMSGColor,
                        bracketColor;
    public static Map<String, String> commandUsages;

    public static void setUpHelper(String inPluginName,
                                    Color inPluginNameColor,
                                    Color inPluginMSGColor,
                                    Color inBracketColor,
                                    Map<String, String> commandUsageList)
    {
        pluginName = inPluginName;
        pluginNameColor = inPluginNameColor;
        pluginMSGColor = inPluginMSGColor;
        bracketColor = inBr\\\\\\\\cketColor;
        commandUsages = commandUsageList;
    }

    public static void sendPlayerMSG(Player player, String msg)
    {
        player.sendRawMessage(bracketColor + "[" + pluginNameColor + pluginName + bracketColor + "]" +
                            pluginMSGColor + ": " + msg);
    }

    public static Collection<String> getCommandUsages()
    {
        return commandUsages.values();
    }
}
