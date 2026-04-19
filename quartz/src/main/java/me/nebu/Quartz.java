package me.nebu;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

/**
 * This class handles storing and parsing player data.
 */
public class Quartz {

    private static QuartzConfig config;

    private static final HashMap<UUID, YamlConfiguration> cachedPlayerConfigs = new HashMap<>();

    /**
     * Initialize Quartz with the configuration provided.
     *
     * @param config The configuration.
     */
    public static void configure(QuartzConfig config) {
        if (config == null) throw new IllegalStateException("Config cannot be null.");
        if (config.getPluginInstance() == null) throw new IllegalStateException("Plugin instance must be configured in config.");
        if (Quartz.config != null) throw new IllegalStateException("Quartz can only be configured once.");

        Quartz.config = config;

        config.getPluginInstance().getServer().getPluginManager().registerEvents(
                new Listener() {
                    @EventHandler
                    public void onDisconnect(PlayerQuitEvent e) {
                        cachedPlayerConfigs.remove(e.getPlayer().getUniqueId());
                    }
                }, config.getPluginInstance()
        );
    }

    /**
     * Obtain the configuration that Quartz is using.
     *
     * @return The configuration Quartz is using.
     */
    public static QuartzConfig getConfig() {
        return config;
    }

    /**
     * Obtain the PlayerData of a selected player based off their UUID.
     *
     * @param id The unique ID of the player.
     * @return A PlayerData object to change this player's data.
     */
    public static PlayerData get(UUID id) {
        HashMap<String, Object> playerData = parsePlayerData(id);
        return new PlayerData(id, playerData);
    }

    /**
     * Obtain the PlayerData of a selected player based off their UUID.
     *
     * @param id The unique ID of the player.
     * @param autoSave If Quartz should automatically save the PlayerData to disk after calling set().
     */
    public static PlayerData get(UUID id, boolean autoSave) {
        HashMap<String, Object> playerData = parsePlayerData(id);
        return new PlayerData(id, playerData, autoSave);
    }

    /**
     * Handy method of reading PlayerData on disk and parsing it into a HashMap.
     *
     * @param id The unique ID of the player.
     */
    private static HashMap<String, Object> parsePlayerData(UUID id) {
        HashMap<String, Object> playerData = new HashMap<>();

        YamlConfiguration config = cachedPlayerConfigs.computeIfAbsent(id, uuid -> YamlConfiguration.loadConfiguration(getStorageFile(uuid)));

        config.getKeys(true).forEach(key -> playerData.put(key, config.get(key)));

        return playerData;
    }

    /**
     * Save the PlayerData of a specific player based off their uuid and the player data.
     * This is useful if you want to bypass the PlayerData's data owner, for example to
     * copy player data from one player to another.
     *
     * @param id The player UUID that will own the new PlayerData.
     * @param playerData The new PlayerData the player will own.
     */
    public static void save(UUID id, PlayerData playerData) {
        HashMap<String, Object> data = playerData.getRawPlayerData();

        File saveFile = getStorageFile(id);
        YamlConfiguration config = cachedPlayerConfigs.computeIfAbsent(id, uuid -> YamlConfiguration.loadConfiguration(getStorageFile(uuid)));

        data.keySet().forEach(key -> config.set(key, data.get(key)));

        try {
            config.save(saveFile);
        } catch (Exception e) {
            throw new IllegalStateException("An error occurred while saving the data of " + playerData.getDataOwner());
        }
    }

    /**
     * Save the PlayerData specified.
     *
     * @param playerData The PlayerData to save.
     */
    public static void save(PlayerData playerData) {
        save(playerData.getDataOwner(), playerData);
    }

    /**
     * This method must be called in your plugin's onDisable method.
     * Makes sure all cached player data is stored on disk before shutting down the
     * server.
     */
    public static void quit() {
        cachedPlayerConfigs.forEach((uuid, yaml) -> {
            try {
                yaml.save(getStorageFile(uuid));
            } catch (Exception e) {
                throw new IllegalStateException("Failed to save data for " + uuid, e);
            }
        });

        cachedPlayerConfigs.clear();
    }

    /**
     * Handy method to obtain the file endpoint of a player's data.
     *
     * @param uuid The UUID of the player.
     * @return A file pointing to the player data.
     */
    private static File getStorageFile(UUID uuid) {
        return new File(config.getPluginInstance().getDataFolder(), config.getDataFolderPath() + "/" + uuid + ".yml");
    }

}
