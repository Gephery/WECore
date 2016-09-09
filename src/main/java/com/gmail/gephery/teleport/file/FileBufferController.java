package com.gmail.gephery.teleport.file;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

/**
 * Used keep track of the FileBuffers and fetch them.
 * @author Gephery
 */
public class FileBufferController
{
    private static HashMap<String, FileBufferController> FILE_CONTROLLERS;
    private JavaPlugin PLUGIN;

    private final HashMap<String, FileBuffer> fileBuffers;

    /** Initializes the FileBufferController. Must be called first in Main. */
    static public void init(final JavaPlugin plugin)
    {
        if (FILE_CONTROLLERS == null)
            FILE_CONTROLLERS = new HashMap<>();
        if (!FILE_CONTROLLERS.containsKey(plugin.getName()))
            FILE_CONTROLLERS.put(plugin.getName(), new FileBufferController(plugin));
    }

    /** @return Initialized FileBufferController. */
    static public FileBufferController instance(final String pluginName)
    { return FILE_CONTROLLERS.get(pluginName); }


    /**
     * Constructor for the Controller.
     * @param plugin The plugin using the fileBuffers.
     */
    private FileBufferController(final JavaPlugin plugin)
    {
        PLUGIN = plugin;
        this.fileBuffers = new HashMap<>();
    }

    /** @return FileBuffer for that world. */
    public FileBuffer getFile(final World world, final String fileName)
    {
        String fileID = FileBuffer.buildID(world, fileName);
        if (!fileBuffers.containsKey(fileID))
            fileBuffers.put(fileID, new FileBuffer(PLUGIN, fileName));
        return fileBuffers.get(fileID);
    }

    /** Used for when you want a central file. */
    public FileBuffer getFile(final String fileName)
    {
        String fileID = FileBuffer.buildID(fileName);
        if (!fileBuffers.containsKey(fileID))
            fileBuffers.put(fileID, new FileBuffer(PLUGIN, fileName));
        return fileBuffers.get(fileID);
    }
}