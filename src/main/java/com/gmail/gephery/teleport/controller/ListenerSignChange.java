package com.gmail.gephery.teleport.controller;

import com.gmail.gephery.guard.buffers.RegionBuffer;
import com.gmail.gephery.teleport.file.FileRead;
import com.gmail.gephery.teleport.file.FileWrite;
import com.gmail.gephery.teleport.main.WorldExplorerCore;
import com.gmail.gephery.teleport.util.ChatHelper;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by maxgr on 7/5/2016.
 */
public class ListenerSignChange implements Listener {
    private JavaPlugin plugin;

    /**
     * Description: Sets up a reference to the main plugin.
     * @param plugin = The plugin being referenced.
     */
    public ListenerSignChange(WorldExplorerCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSignChangeEvent(SignChangeEvent event) {
        if (event.getPlayer().hasPermission("WE.sign") && event.getLine(0).startsWith("[")) {
            event.setLine(0, ChatColor.GREEN + event.getLine(0));

            FileWrite.writeSignCommands(event.getPlayer().getWorld(),
                                        ChatHelper.pluginMSGColor + event.getLine(0),
                                        new ArrayList<String>());

            ChatHelper.sendPlayerMSG(event.getPlayer(), "Your sign has been set up!");
        }
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        if (event.getPlayer().hasPermission("WE.wesignuse")) {
            Player player = event.getPlayer();
            World world = player.getWorld();
            Set<Material> mat = null;
            BlockState block = player.getTargetBlock(mat, 10).getState();
            if (block instanceof Sign) {
                Sign sign = (Sign) block;
                sign.getLine(0);
                if (sign.getLine(0).contains("[")) {
                    List<String> cmds = FileRead.readSignCmds(world, ChatHelper.pluginMSGColor + sign.getLine(0));
                    CommandSender sender = plugin.getServer().getConsoleSender();
                    for (String cmd : cmds) {

                        // Changing player to the player that touched the sign.
                        while (cmd.contains("player")) {
                            cmd = cmd.replace("player", player.getName());
                        }

                        // Changing world keyword to player's current world
                        while (cmd.contains("world")) {
                            cmd = cmd.replace("world", player.getWorld().getName());
                        }

                        // If WEG is on server, then supports cmd region to current region.
                        if (plugin.getServer().getPluginManager().getPlugin("WorldExplorerGuard") != null) {
                            while (cmd.contains("region"))
                            {
                                cmd = cmd.replace("region", RegionBuffer.getRegion(block.getLocation()));
                            }
                        }
                        plugin.getServer().dispatchCommand(sender, cmd);
                    }
                }
            }

        }
    }
}
