package me.nebu;

import org.bukkit.plugin.java.JavaPlugin;

public class QuartzConfig {

    private JavaPlugin instance;

    private boolean autoSave = true;
    private boolean persistDefaults = true;
    private String dataFolderPath = "quartz-storage";


    /**
     * Configure the instance to use as your plugin's main instance.
     * This is used with the data folder path to correctly store
     * data in your plugin's root data folder provided by Bukkit.
     *
     * @param instance The instance to use as the plugin's instance.
     *
     * @return An instance of the config for easy method chaining.
     */
    public QuartzConfig setPluginInstance(JavaPlugin instance) {
        this.instance = instance;
        return this;
    }

    /**
     * Get the configured plugin instance class.
     * Must be initialized before using Quartz.
     *
     * @return The class to use as the plugin's instance.
     */
    public JavaPlugin getPluginInstance() {
        return instance;
    }

    /**
     * Configure if Quartz should automatically save player data when
     * you write to it.
     *
     * @param autoSave If Quartz should auto save.
     *
     * @return An instance of the config for easy method chaining.
     */
    public QuartzConfig setAutoSave(boolean autoSave) {
        this.autoSave = autoSave;
        return this;
    }

    /**
     * Get if auto saving is enabled or not.
     * Default is 'true'.
     *
     * @return If Quartz should auto save.
     */
    public boolean shouldAutoSave() {
        return autoSave;
    }

    /**
     * Configure whether missing keys should be written to disk when first accessed.
     *
     * @param persistDefaults If defaults should persist or not.
     *
     * @return An instance of the config for easy method chaining.
     */
    public QuartzConfig setPersistDefaults(boolean persistDefaults) {
        this.persistDefaults = persistDefaults;
        return this;
    }

    /**
     * Get if defaults persisting is enabled or not.
     * Default is 'true'.
     *
     * @return If Quartz should persist defaults.
     */
    public boolean shouldPersistDefaults() {
        return persistDefaults;
    }

    /**
     * Configure the root folder where Quartz will store the player
     * data.
     *
     * @param path The folder path where Quartz will store data.
     *
     * @return An instance of the config for easy method chaining.
     */
    public QuartzConfig setDataFolderPath(String path) {
        this.dataFolderPath = path;
        return this;
    }

    /**
     * Get the configured data folder path.
     * Default is "quartz-storage"
     *
     * @return The path to the data folder.
     */
    public String getDataFolderPath() {
        return dataFolderPath;
    }

}
