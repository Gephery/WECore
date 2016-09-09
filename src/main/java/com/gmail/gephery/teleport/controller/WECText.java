package com.gmail.gephery.teleport.controller;

import net.projectzombie.consistentchatapi.PluginChat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by maxgr on 8/19/2016.
 */
public class WECText
{
    private static final PluginChat chat = new PluginChat("WEC", ChatColor.GREEN,
                                                          ChatColor.WHITE, ChatColor.BOLD,
                                                          ChatColor.ITALIC);
    private static final String tag = chat.getTag();
    public static final ChatColor pluginMSGColor = ChatColor.WHITE;

    public static final String COMMAND_ROOT = "wec";

    /** Used to add the tag and allow difference in failed and success message.
     * txtS will be used if success is true and txtF will be used if success is false.
     */
    public static String formatForChat(final String txtS, final String txtF, final boolean success)
    { return tag + (success ? txtS : txtF); }

    public static void sendPlayerMSG(final Player player, final String msg)
    { player.sendMessage(tag + msg); }
}
