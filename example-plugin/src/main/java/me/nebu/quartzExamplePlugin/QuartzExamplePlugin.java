package me.nebu.quartzExamplePlugin;

import me.nebu.Quartz;
import me.nebu.QuartzConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class QuartzExamplePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // This plugin is an example of how Quartz can be used in building plugins.
        // In this example, we'll be making a simple rank management system.

        // For easy usage, we set persisting defaults to true while configuring Quartz so
        // we do not have to worry about losing default data.
        // Also, set auto saving to true so we do not have to call Quartz.save() over and over.
        // This is not a recommended approach, as the performance impact is high.
        Quartz.configure(new QuartzConfig()
                .setPluginInstance(this)
                .setPersistDefaults(true)
                .setAutoSave(true)
        );

        // Define the /rank command + the /rank completions.
        Objects.requireNonNull(getCommand("rank")).setExecutor(new RankCommand());
        Objects.requireNonNull(getCommand("rank")).setTabCompleter(new RankCompleter());

        // Register a chat listener so chatting can be intercepted.
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }

    @Override
    public void onDisable() {
        /* --------------------[ VERY IMPORTANT PLEASE READ ]--------------------
        *
        * It is EXTREMELY IMPORTANT to call this method at the onDisable in your plugin.
        * This allows Quartz to go through ALL CACHED DATA in memory and store them on
        * disk. Even if you have auto saving enabled, it is imperative that Quartz can
        * store all data on disk before shutting down.
        */
        Quartz.quit();
    }
}
