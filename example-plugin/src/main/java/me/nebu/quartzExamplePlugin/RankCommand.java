package me.nebu.quartzExamplePlugin;

import me.nebu.PlayerData;
import me.nebu.Quartz;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RankCommand implements CommandExecutor {

    // Our /rank command.
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        // Check if 2 arguments are provided
        if (args.length < 2) {
            sender.sendMessage(ChatFormatting.error("Invalid arguments: Must provide a player and rank."));
            return false;
        }

        String playerName = args[0];
        String rankName = args[1];

        Player target = Bukkit.getPlayer(playerName);

        // Validate the target player exists.
        if (target == null) {
            sender.sendMessage(ChatFormatting.error("The player provided does not exist or is not online."));
            return false;
        }

        // Check if the rank exists
        try {
            Rank rank = Rank.valueOf(rankName.toUpperCase());

            sender.sendMessage(ChatFormatting.normal("Set " + target.getName() + "'s rank to: " + rank.name()));

            // Fetch the PlayerData
            PlayerData data = Quartz.get(target.getUniqueId());
            // Since auto saving is enabled, we do not have to call
            // Quartz.save(data), as it is called automatically.
            data.set("rank", rank.name()); // Set our "rank" to the rank name.

            return true;
        } catch (Exception e) {
            sender.sendMessage(ChatFormatting.error("The rank provided does not exist."));
            return false;
        }
    }
}
