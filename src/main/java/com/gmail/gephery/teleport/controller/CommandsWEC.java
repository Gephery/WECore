package com.gmail.gephery.teleport.controller;

import com.gmail.gephery.teleport.buffers.UtilBuffer;
import com.gmail.gephery.teleport.file.FileRead;
import com.gmail.gephery.teleport.file.FileWrite;
import com.gmail.gephery.teleport.util.ChatHelper;

import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Creator: Gephery
 * Description: The world teleport command, which teleports a player to the specified
 *              world.
 */
public class CommandsWEC implements CommandExecutor
{

    /**
     * Description: The execution of the wsethome command.
     * @param sender = The player setting their home.
     * @param cmd = The command, wsethome.
     * @param label = The string being wsethome.
     * @param args = One arg is supported if the label is town, should be town name.
     * @return
     */
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {

        if (sender instanceof Player && args.length > 0)
        {
            Player player = (Player) sender;

            if (args[0].equals("sethome") &&
                                player.hasPermission("WEC.sethome"))
            {
                // Setting Player's home
                boolean success = FileWrite.writePlayerHome(player);
                String msg = "";
                if (success)
                    msg = "Your home has been set :)";
                else
                    msg = "Unsuccessful save.";
                ChatHelper.sendPlayerMSG(player, msg);
                return true;
            } else if (args[0].equals("settown") && args.length > 1 &&
                        player.hasPermission("WEC.settown"))
            {
                // Setting a town up.
                player.sendMessage(args[1] + " Town is on the map! :)");
                boolean success = FileWrite.writeTownDirection(args[1], player.getLocation());
                String msg = "";
                if (success)
                    msg = args[1] + " Town is on the map! :)";
                else
                    msg = "Unsuccessful save.";

                ChatHelper.sendPlayerMSG(player, msg);
                return true;
            } else if (args[0].equals("direct") && args.length == 1 &&
                        player.hasPermission("WEC.direct"))
            {
                Set<String> towns = FileRead.readTowns(player.getWorld());
                if (towns != null)
                {
                    String townList = "";
                    for (String town : towns)
                        townList += ", " + town;
                    townList = "The towns are " + townList.substring(2, townList.length());
                    ChatHelper.sendPlayerMSG(player, townList);
                }
                return true;
            } else if (args[0].equals("direct") && args.length > 1 &&
                        player.hasPermission("WEC.direct"))
            {
                String playerMsg = "";
                if (args[1].equals("home")) { // Checking if home is the location.
                    Location home = FileRead.readUserHome(player);
                    if (home != null)
                        playerMsg = "Follow that compass home!";
                    else
                        playerMsg = "Your home is not on file.";
                } else if (FileRead.readTowns(player.getWorld()) != null &&
                            FileRead.readTowns(player.getWorld()).contains(args[2]))
                {
                    player.setCompassTarget(FileRead.readTownDirection(player.getWorld(), args[2]));
                    playerMsg = "Follow that compass to " + args[2] + " Town";
                }
                if (!playerMsg.equals(""))
                {
                    ChatHelper.sendPlayerMSG(player, playerMsg);
                    return true;
                }
                ChatHelper.sendPlayerMSG(player, "WAIT!!!....You may not have it right :(");
                return true;
            } else if (args[0].equals("signedit") && sender.hasPermission("WE.signedit") && args.length >= 4)
            {
                String signID = args[2];
                List<String> cmds = FileRead.readSignCmds(player.getWorld(), ChatHelper.pluginMSGColor + signID);
                if (cmds == null)
                    cmds = new LinkedList<String>();

                String pCmd = "";
                for (int i = 3; i < args.length; i++) {
                    pCmd += " " + args[i];
                }
                cmds.add(pCmd.trim());

                FileWrite.writeSignCommands(player.getWorld(), ChatHelper.pluginMSGColor + signID, cmds);
                ChatHelper.sendPlayerMSG(player, "Your cmd has been added to the sign.");
                return true;
            } else if (args[0].equals("create") && sender.hasPermission("WEC.create"))
            {
                World world = null;

                if (args.length > 1) {
                    // Making the world
                    WorldCreator wC = new WorldCreator(args[2]);

                    if (args.length > 2) {
                        // NETHER, NORMAL, THE_END
                        switch (args[3]) {
                            case "NETHER":
                                wC.environment(World.Environment.NETHER);
                                break;
                            case "NORMAL":
                                wC.environment(World.Environment.NORMAL);
                                break;
                            case "THE_END":
                                wC.environment(World.Environment.THE_END);
                                break;
                            default:
                                ChatHelper.sendPlayerMSG(player, "The Type you entered is wrong");
                                return true;
                        }
                    }

                    world = wC.createWorld();
                    UtilBuffer.plugin.getServer().createWorld(wC);

                    ChatHelper.sendPlayerMSG(player, "World " + args[2] + " is being created.");

                    if (args.length > 4) {
                        if (args[4].equals("t")) {
                            world.setPVP(true);
                        } else {
                            world.setPVP(false);
                        }
                    } if (args.length > 5) {
                        // EASY, HARD, NORMAL, PEACEFUL
                        switch (args[5]) {
                            case "EASY":
                                world.setDifficulty(Difficulty.EASY);
                                break;
                            case "HARD":
                                world.setDifficulty(Difficulty.HARD);
                                break;
                            case "NORMAL":
                                world.setDifficulty(Difficulty.NORMAL);
                                break;
                            case "PEACEFUL":
                                world.setDifficulty(Difficulty.PEACEFUL);
                                break;
                            default:
                                ChatHelper.sendPlayerMSG(player, "The difficulty you entered is wrong");
                                return true;
                        }
                    }
                    ChatHelper.sendPlayerMSG(player, "World" + args[2] + "has been finalized.");
                    return true;
                }
                } else if (args[0].equals("wtp") && sender.hasPermission("WEC.wtp"))
                {
                    if (args.length > 1)
                    {
                        player.saveData(); // Saving previous world data for the player.
                        WorldCreator wC;
                        World world = UtilBuffer.plugin.getServer().getWorld(args[1]);
                        if (world != null || (FileRead.readWorlds() != null &&
                                FileRead.readWorlds().contains(args[1])))
                        {

                            // Creating the reference to the map.
                            if (world == null) {
                                wC = new WorldCreator(args[1]);
                                world = wC.createWorld();
                                UtilBuffer.plugin.getServer().createWorld(wC);
                            }

                            Location loc = world.getSpawnLocation(); // Setting loc to spawn as default location.

                            world.getPlayers().add(player);
                            loc.setWorld(world); // Changing the location's world.
                            player.teleport(loc); // Teleport player to world's spawn.
                            ChatHelper.sendPlayerMSG(player, "Woosh!");

                            return true;
                        }
                    }
            }

            String usage = "";
            for (String use : ChatHelper.getCommandUsages())
                usage += ", " + use;
            usage = usage.substring(2, usage.length());
            ChatHelper.sendPlayerMSG(player, usage);
            return true;
        }
        return false;
    }
}
