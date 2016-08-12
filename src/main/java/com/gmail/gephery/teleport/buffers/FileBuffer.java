package com.gmail.gephery.teleport.buffers;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Created by maxgr on 7/15/2016.
 */
public class FileBuffer {
    public static FileConfiguration file;
    public static JavaPlugin plugin;
    public static String fileName;
    public static String fileFolder;

    /**
     * Description: Constructor for the UtilUGFiles, sets up file dependencies.
     * @param plugin = Plugin managing the files, a JavaPlugin.
     * @param fileName = Name of file, sould be name.yml
     */
    public FileBuffer(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.file = null;

    }

    public static String getFileFolder()
    {
        return fileFolder;
    }

    private static void updateFileFolder(World world)
    {
        fileFolder = "/" + world.getUID() + "/";
    }

    public static void loadFile(World world)
    {
        updateFileFolder(world);
        if (!isFileLoaded() || isFileFolderSame(fileFolder))
            loadFile(fileFolder);
    }

    public static boolean isFileLoaded() {
        return file != null;
    }

    public static boolean isFileFolderSame(String cFileFolder) {
        return fileFolder.equals(cFileFolder);
    }

    /**
     * Description:
     */
    public static void loadFile(String cFileFolder) {
        fileFolder = cFileFolder;
        file = YamlConfiguration.loadConfiguration(
                new File(plugin.getDataFolder() + cFileFolder, fileName));
        saveFiles();
    }

    /**
     * Description: Save the two files, user.yml and group.yml.
     */
    public static boolean saveFiles() {
        try {
            file.save(new File(plugin.getDataFolder() + fileFolder, fileName));
            return true;
        } catch (IOException exc) {
            exc.printStackTrace();
            return false;
        }
    }

    public static boolean isSafePath(String path) {
        return file.get(path) != null;
    }

}
