package com.gmail.gephery.teleport.file;

import com.gmail.gephery.teleport.buffers.FileBuffer;
import net.projectzombie.survivalteams.file.WorldCoordinate;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

import static com.gmail.gephery.teleport.file.FilePath.*;

/**
 * Created by maxgr on 8/9/2016.
 */
public class FileWrite
{
    public static boolean writeWorlds()
    {
        FileBuffer.loadFile("");
        String pathToWorlds = worlds();

        FileBuffer.file.set(pathToWorlds, null);
        return FileBuffer.saveFiles();
    }

    public static boolean flushUserHomes(World world)
    {
        FileBuffer.loadFile(world);
        String pathToUsers = usersHome();

        FileBuffer.file.set(pathToUsers, null);
        return FileBuffer.saveFiles();
    }

    public static boolean writePlayerHome(Player player)
    {
        FileBuffer.loadFile(player.getWorld());
        String pathToUserHome = userHome(player.getUniqueId());

        FileBuffer.file.set(pathToUserHome, WorldCoordinate.toString(player.getLocation().getBlock()));
        return FileBuffer.saveFiles();
    }

    public static boolean flushTowns(World world)
    {
        FileBuffer.loadFile(world);
        String pathToTowns = townsHome();

        FileBuffer.file.set(pathToTowns, null);
        return FileBuffer.saveFiles();
    }

    public static boolean flushTown(World world, String townName)
    {
        FileBuffer.loadFile(world);
        String pathToTown = townDirection(townName);

        FileBuffer.file.set(pathToTown, null);
        return FileBuffer.saveFiles();
    }

    public static boolean writeTownDirection(String townName, Location location)
    {
        FileBuffer.loadFile(location.getWorld());
        String pathToTown = townDirection(townName);

        FileBuffer.file.set(pathToTown, WorldCoordinate.toString(location.getBlock()));
        return FileBuffer.saveFiles();
    }

    public static boolean flushSigns(World world)
    {
        FileBuffer.loadFile(world);
        String pathToSigns = signs();

        FileBuffer.file.set(pathToSigns, null);
        return FileBuffer.saveFiles();
    }

    public static boolean flushSign(World world, String signUUID)
    {
        FileBuffer.loadFile(world);
        String pathToSign = sign(signUUID);

        FileBuffer.file.set(pathToSign, null);
        return FileBuffer.saveFiles();
    }

    public static boolean writeSignCommands(World world, String signUUID, List<String> cmds)
    {
        FileBuffer.loadFile(world);
        String pathToSignCmds = signCommands(signUUID);

        FileBuffer.file.set(pathToSignCmds, cmds);
        return FileBuffer.saveFiles();
    }
}
