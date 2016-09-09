package com.gmail.gephery.teleport.file;

import com.gmail.gephery.teleport.main.WorldExplorerCore;
import net.projectzombie.survivalteams.file.WorldCoordinate;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static com.gmail.gephery.teleport.file.FilePath.*;

/**
 * Created by maxgr on 8/9/2016.
 */
public class FileWrite
{
    public static boolean writeWorld(World world)
    {
        String pluginName = WorldExplorerCore.getPluginName(); 
        FileBuffer fileBuffer = FileBufferController.instance(pluginName).getFile(fileName());
        fileBuffer.safeLoadFileNoFF();
        String pathToWorlds = worlds();

        List<String> worlds = fileBuffer.file.getStringList(pathToWorlds);
        if (worlds == null)
            worlds = new ArrayList<>();
        worlds.add(world.getName());
        fileBuffer.file.set(pathToWorlds, worlds);
        return fileBuffer.saveFiles();
    }

    public static boolean flushUserHomes(World world)
    {
        String pluginName = WorldExplorerCore.getPluginName();
        FileBuffer fileBuffer = FileBufferController.instance(pluginName).getFile(world,
                                                                                  FILE_NAME);
        fileBuffer.safeLoadFile(world);
        String pathToUsers = usersHome();

        fileBuffer.file.set(pathToUsers, null);
        return fileBuffer.saveFiles();
    }

    public static boolean writePlayerHome(Player player)
    {
        String pluginName = WorldExplorerCore.getPluginName();
        World world = player.getWorld();
        FileBuffer fileBuffer = FileBufferController.instance(pluginName).getFile(world,
                                                                                  FILE_NAME);
        fileBuffer.safeLoadFile(world);
        String pathToUserHome = userHome(player.getUniqueId());

        fileBuffer.file.set(pathToUserHome, WorldCoordinate.toString(player.getLocation().getBlock()));
        return fileBuffer.saveFiles();
    }

    public static boolean flushTowns(World world)
    {
        String pluginName = WorldExplorerCore.getPluginName();
        FileBuffer fileBuffer = FileBufferController.instance(pluginName).getFile(world,
                                                                                  FILE_NAME);
        fileBuffer.safeLoadFile(world);
        String pathToTowns = townsHome();

        fileBuffer.file.set(pathToTowns, null);
        return fileBuffer.saveFiles();
    }

    public static boolean flushTown(World world, String townName)
    {
        String pluginName = WorldExplorerCore.getPluginName();
        FileBuffer fileBuffer = FileBufferController.instance(pluginName).getFile(world,
                                                                                  FILE_NAME);
        fileBuffer.safeLoadFile(world);
        String pathToTown = townDirection(townName);

        fileBuffer.file.set(pathToTown, null);
        return fileBuffer.saveFiles();
    }

    public static boolean writeTownDirection(String townName, Location location)
    {
        String pluginName = WorldExplorerCore.getPluginName();
        World world = location.getWorld();
        FileBuffer fileBuffer = FileBufferController.instance(pluginName).getFile(world,
                                                                                  FILE_NAME);
        fileBuffer.safeLoadFile(world);
        String pathToTown = townDirection(townName);

        fileBuffer.file.set(pathToTown, WorldCoordinate.toString(location.getBlock()));
        return fileBuffer.saveFiles();
    }

    public static boolean flushSigns(World world)
    {
        String pluginName = WorldExplorerCore.getPluginName();
        FileBuffer fileBuffer = FileBufferController.instance(pluginName).getFile(world,
                                                                                  FILE_NAME);
        fileBuffer.safeLoadFile(world);
        String pathToSigns = signs();

        fileBuffer.file.set(pathToSigns, null);
        return fileBuffer.saveFiles();
    }

    public static boolean flushSign(World world, String signUUID)
    {
        String pluginName = WorldExplorerCore.getPluginName();
        FileBuffer fileBuffer = FileBufferController.instance(pluginName).getFile(world,
                                                                                  FILE_NAME);
        fileBuffer.safeLoadFile(world);
        String pathToSign = sign(signUUID);

        fileBuffer.file.set(pathToSign, null);
        return fileBuffer.saveFiles();
    }

    public static boolean writeSignCommands(World world, String signUUID, List<String> cmds)
    {
        String pluginName = WorldExplorerCore.getPluginName();
        FileBuffer fileBuffer = FileBufferController.instance(pluginName).getFile(world,
                                                                                  FILE_NAME);
        fileBuffer.safeLoadFile(world);
        String pathToSignCmds = signCommands(signUUID);

        fileBuffer.file.set(pathToSignCmds, cmds);
        return fileBuffer.saveFiles();
    }
}
