package me.nebu.quartzExamplePlugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RankCompleter implements TabCompleter {

    // Handy completer for our /rank command.
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList<>();

        // If the command has 1 argument (so in chat it's '/rank ')
        if (args.length == 1) {
            // Loop through all online players and add them to the result list.
            Bukkit.getOnlinePlayers().forEach(player -> result.add(player.getName()));
            return result;
        }

        // If the command has 2 arguments (so in chat it's '/rank <player> ')
        if (args.length == 2) {
            // Loop through all ranks, add the rank lowercase rank to the result list.
            Arrays.asList(Rank.values())
                    .forEach(rank -> result.add(rank.name().toLowerCase()));
        }

        return result;
    }
}
