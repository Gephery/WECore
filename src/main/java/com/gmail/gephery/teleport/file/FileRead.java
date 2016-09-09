package com.gmail.gephery.teleport.file;


import com.gmail.gephery.teleport.main.WorldExplorerCore;
import net.projectzombie.survivalteams.file.WorldCoordinate;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

import static com.gmail.gephery.teleport.file.FilePath.FILE_NAME;
import static com.gmail.gephery.teleport.file.FilePath.fileName;

/**
 * Created by maxgr on 8/9/2016.
 */
public class FileRead {

    public static List<String> readWorlds()
    {
        String pluginName = WorldExplorerCore.getPluginName(); 
        FileBuffer fileBuffer = FileBufferController.instance(pluginName).getFile(fileName());
        fileBuffer.safeLoadFileNoFF();

        return fileBuffer.isSafePath(FilePath.worlds()) ?
                fileBuffer.file.getStringList(FilePath.worlds()) :
                null;
    }

    public static Set<String> readDirectionTypes(World world)
    {
        String pluginName = WorldExplorerCore.getPluginName();
        FileBuffer fileBuffer = FileBufferController.instance(pluginName).getFile(world,
                                                                                  FILE_NAME);
        fileBuffer.safeLoadFile(world);

        return fileBuffer.isSafePath(FilePath.directionTypes()) ?
                fileBuffer.file.getConfigurationSection(FilePath.directionTypes()).getKeys(false) :
                null;
    }

    public static Location readUserHome(Player player)
    {
        String pluginName = WorldExplorerCore.getPluginName();
        World world = player.getWorld();
        FileBuffer fileBuffer = FileBufferController.instance(pluginName).getFile(world,
                                                                                  FILE_NAME);
        fileBuffer.safeLoadFile(world);
        String pathToUserHome = FilePath.userHome(player.getUniqueId());

        return fileBuffer.isSafePath(pathToUserHome) ?
                WorldCoordinate.toLocation(fileBuffer.file.getString(pathToUserHome)) :
                null;

    }

    public static Set<String> readTowns(World world)
    {
        String pluginName = WorldExplorerCore.getPluginName();
        FileBuffer fileBuffer = FileBufferController.instance(pluginName).getFile(world,
                                                                                  FILE_NAME);
        fileBuffer.safeLoadFile(world);
        String pathToTowns = FilePath.townsHome();

        return fileBuffer.isSafePath(pathToTowns) ?
                fileBuffer.file.getConfigurationSection(pathToTowns).getKeys(false) :
                null;
    }

    public static Location readTownDirection(World world, String town)
    {
        String pluginName = WorldExplorerCore.getPluginName();
        FileBuffer fileBuffer = FileBufferController.instance(pluginName).getFile(world,
                                                                                  FILE_NAME);
        fileBuffer.safeLoadFile(world);
        String pathToTownDirect = FilePath.townDirection(town);

        return fileBuffer.isSafePath(pathToTownDirect) ?
                WorldCoordinate.toLocation(fileBuffer.file.getString(pathToTownDirect)) :
                null;
    }

    public static List<String> readSignCmds(World world, String signUUID)
    {
        String pluginName = WorldExplorerCore.getPluginName();
        FileBuffer fileBuffer = FileBufferController.instance(pluginName).getFile(world,
                                                                                  FILE_NAME);
        fileBuffer.safeLoadFile(world);
        String pathToSignCmds = FilePath.signCommands(signUUID);

        return fileBuffer.isSafePath(pathToSignCmds) ?
                fileBuffer.file.getStringList(pathToSignCmds) :
                null;
    }

    public static Set<String> readSigns(World world)
    {
        String pluginName = WorldExplorerCore.getPluginName();
        FileBuffer fileBuffer = FileBufferController.instance(pluginName).getFile(world,
                                                                                  FILE_NAME);
        fileBuffer.safeLoadFile(world);
        String pathToSign = FilePath.signs();

        return fileBuffer.isSafePath(pathToSign) ?
                fileBuffer.file.getConfigurationSection(pathToSign).getKeys(false) :
                null;
    }
}