package com.gmail.gephery.teleport;

import com.gmail.gephery.teleport.file.FileRead;
import com.gmail.gephery.teleport.main.WorldExplorerCore;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.util.List;

/**
 * Created by maxgr on 8/19/2016.
 */
public class WorldLoader
{
    public static void loadInWorlds()
    {
        List<String> worlds = FileRead.readWorlds();
        List<World> serverWorlds = WorldExplorerCore.getPlugin().getServer().getWorlds();
        if (worlds != null)
        {
            for (String world : worlds)
            {
                if (!serverWorlds.contains(world))
                {
                    WorldCreator wC = new WorldCreator(world);
                    wC.createWorld();
                    WorldExplorerCore.getPlugin().getServer().createWorld(wC);
                }
            }
        }
    }
}
