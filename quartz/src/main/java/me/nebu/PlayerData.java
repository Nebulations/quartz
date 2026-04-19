package me.nebu;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

/**
 * This class allows you to modify a player's data
 */
public class PlayerData {

    private final HashMap<String, Object> playerData;
    private final UUID dataOwner;
    private boolean autoSave = false;

    /**
     * Do NOT use this method for creating PlayerData. Use Quartz.get(player.getUniqueId()) instead.
     */
    public PlayerData(UUID dataOwner, HashMap<String, Object> playerData) {
        this.dataOwner = dataOwner;
        this.playerData = playerData;
    }

    /**
     * Do NOT use this method for creating PlayerData. Use Quartz.get(player.getUniqueId()) instead.
     */
    public PlayerData(UUID dataOwner, HashMap<String, Object> playerData, boolean autoSave) {
        this.dataOwner = dataOwner;
        this.playerData = playerData;
        this.autoSave = autoSave;
    }

    /**
     * Obtain the player in the player data.
     * @return The player in the player data.
     */
    public UUID getDataOwner() {
        return dataOwner;
    }

    /**
     * Set player data with a key and value pair.
     * @param key The key to use.
     * @param value The value assigned to the key.
     */
    public void set(String key, Object value) {
        playerData.put(key, value);
        save();
    }

    /**
     * Obtain the raw data from the key.
     * @param key The key to use.
     * @return The data assigned to the key.
     */
    public Object get(String key) {
        return playerData.get(key);
    }

    /**
     * Obtain a copy of the in-memory player data.
     * @return The raw player data.
     */
    public HashMap<String, Object> getRawPlayerData() {
        return new HashMap<>(playerData);
    }

    /**
     * Obtain the integer at the specified key. If the value is not present,
     * uses the defaultValue instead.
     * @param key The key to use.
     * @param defaultValue The default value, if the value is not present.
     * @return An integer.
     */
    public int getInt(String key, int defaultValue) {
        Object val = validateKey(key, defaultValue);
        return (val instanceof Number) ? ((Number) val).intValue() : defaultValue;
    }

    /**
     * Obtain the string at the specified key. Default value is 0.
     * @param key The key to use.
     * @return An integer.
     */
    public int getInt(String key) {
        return getInt(key, 0);
    }

    /**
     * Obtain the double at the specified key. If the value is not present,
     * uses the defaultValue instead.
     * @param key The key to use.
     * @param defaultValue The default value, if the value is not present.
     * @return A double.
     */
    public double getDouble(String key, double defaultValue) {
        Object val = validateKey(key, defaultValue);
        return (val instanceof Number) ? ((Number) val).doubleValue() : defaultValue;
    }

    /**
     * Obtain the double at the specified key. Default value is 0.0.
     * @param key The key to use.
     * @return A double.
     */
    public double getDouble(String key) {
        return getDouble(key, 0.0);
    }

    /**
     * Obtain the boolean at the specified key. If the value is not present,
     * uses the defaultValue instead.
     * @param key The key to use.
     * @param defaultValue The default value, if the value is not present.
     * @return The boolean.
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        Object val = validateKey(key, defaultValue);
        return (val instanceof Boolean) ? (Boolean) val : defaultValue;
    }

    /**
     * Obtain the boolean at the specified key. Default value is false.
     * @param key The key to use.
     * @return A boolean.
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * Obtain the string at the specified key. If the value is not present,
     * uses the defaultValue instead.
     * @param key The key to use.
     * @param defaultValue The default value, if the value is not present.
     * @return A string.
     */
    public String getString(String key, String defaultValue) {
        return String.valueOf(validateKey(key, defaultValue));
    }

    /**
     * Obtain the string at the specified key. Default value is "".
     * @param key The key to use.
     * @return A string.
     */
    public String getString(String key) {
        return getString(key, "");
    }

    /**
     * Handy method for saving data if autosaving is enabled or if initializing default values
     * is enabled. (configured in the QuartzConfig provided)
     */
    private void save() {
        if (Quartz.getConfig().shouldAutoSave() || autoSave)
            Quartz.save(this);
    }

    /**
     * Handy method to validate a key exists. If the key does not exist,
     * writes the default option in memory, and if
     *
     * @param key The key to use
     * @param defaultOption The default option
     * @return A none-null result.
     */
    private @NotNull Object validateKey(String key, Object defaultOption) {
        Object result = get(key);

        if (result == null) {
            playerData.put(key, defaultOption);
            if (Quartz.getConfig().shouldPersistDefaults()) Quartz.save(this);

            return defaultOption;
        }

        return result;
    }

}
