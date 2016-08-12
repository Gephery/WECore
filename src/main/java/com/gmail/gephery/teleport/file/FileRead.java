package com.gmail.gephery.teleport.file;

import com.gmail.gephery.teleport.buffers.FileBuffer;
import net.projectzombie.survivalteams.file.WorldCoordinate;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

/**
 * Created by maxgr on 8/9/2016.
 */
public class FileRead {

    public static List<String> readWorlds()
    {
        FileBuffer.loadFile("");

        return FileBuffer.isSafePath(FilePath.worlds()) ?
                FileBuffer.file.getStringList(FilePath.worlds()) :
                null;
    }

    public static Set<String> readDirectionTypes(World world)
    {
        FileBuffer.loadFile(world);

        return FileBuffer.isSafePath(FilePath.directionTypes()) ?
                FileBuffer.file.getConfigurationSection(FilePath.directionTypes()).getKeys(false) :
                null;
    }

    public static Location readUserHome(Player player)
    {
        FileBuffer.loadFile(player.getWorld());
        String pathToUserHome = FilePath.userHome(player.getUniqueId());

        return FileBuffer.isSafePath(pathToUserHome) ?
                WorldCoordinate.toLocation(FileBuffer.file.getString(pathToUserHome)) :
                null;

    }

    public static Set<String> readTowns(World world)
    {
        FileBuffer.loadFile(world);
        String pathToTowns = FilePath.townsHome();

        return FileBuffer.isSafePath(pathToTowns) ?
                FileBuffer.file.getConfigurationSection(pathToTowns).getKeys(false) :
                null;
    }

    public static Location readTownDirection(World world, String town)
    {
        FileBuffer.loadFile(world);
        String pathToTownDirect = FilePath.townDirection(town);

        return FileBuffer.isSafePath(pathToTownDirect) ?
                WorldCoordinate.toLocation(FileBuffer.file.getString(pathToTownDirect)) :
                null;
    }

    public static List<String> readSignCmds(World world, String signUUID)
    {
        FileBuffer.loadFile(world);
        String pathToSignCmds = FilePath.signCommands(signUUID);

        return FileBuffer.isSafePath(pathToSignCmds) ?
                FileBuffer.file.getStringList(pathToSignCmds) :
                null;
    }

    public static Set<String> readSigns(World world)
    {
        FileBuffer.loadFile(world);
        String pathToSign = FilePath.signs();

        return FileBuffer.isSafePath(pathToSign) ?
                FileBuffer.file.getConfigurationSection(pathToSign).getKeys(false) :
                null;
    }
}