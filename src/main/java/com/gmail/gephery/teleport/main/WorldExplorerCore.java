package com.gmail.gephery.teleport.main;

import com.gmail.gephery.teleport.WorldLoader;
import com.gmail.gephery.teleport.file.FileBufferController;
import com.gmail.gephery.teleport.controller.CommandsWEC;
import com.gmail.gephery.teleport.controller.ListenerSignChange;

import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Creator: Gephery
 * Description: World explorer core.
 */
public class WorldExplorerCore extends JavaPlugin {

    private static JavaPlugin PLUGIN;
    private static String PLUGIN_NAME;

    public static JavaPlugin getPlugin() { return PLUGIN; }
    public static String getPluginName() { return PLUGIN_NAME; }

    /**
     * Description: Checks if wtp or wcreate has been done.
     */
    @Override
    public void onEnable() {
        //TODO Add separate inventories
        //TODO World Load in file to make the need to reload a world every time not necessary

        PLUGIN = this;
        PLUGIN_NAME = this.getName();

        Map<String, String> commandUsageMap = new HashMap<String, String>();
        commandUsageMap.put("wtp", "/wec wetp <world>");
        commandUsageMap.put("create", "/wec create <world>, Creating /wec create <world> <type= NETHER, " +
                            "NORMAL, THE_END> <PVP= t, f> <Difficulty= EASY, HARD, NORMAL, PEACEFUL>");
        commandUsageMap.put("sethome", "/wec sethome");
        commandUsageMap.put("direct", "/wec direct <town name or home>");
        commandUsageMap.put("settown", "/wec settown <name>");
        commandUsageMap.put("signedit", "/wec signedit <signID> <command...>");

        FileBufferController.init(this);
        WorldLoader.loadInWorlds();

        this.getServer().getPluginManager().registerEvents(new
                ListenerSignChange(this), this);

        this.getCommand("wec").setExecutor(new CommandsWEC());

        // Permissions
        String[] perms = {"WEC.wetp", "WEC.create", "WEC.sethome", "WEC.direct",
                            "WEC.settown", "WE.signedit"};

        // Adding the permissions
        PluginManager pM = getServer().getPluginManager();
        for (String perm : perms)
            pM.addPermission(new Permission(perm));

    }

    /**
     * Description: Nothing is here at current.
     */
    @Override
    public void onDisable() {
    }
}
