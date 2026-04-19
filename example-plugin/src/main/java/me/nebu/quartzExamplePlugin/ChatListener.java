package me.nebu.quartzExamplePlugin;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.nebu.PlayerData;
import me.nebu.Quartz;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        // Fetch the player data using Quartz
        PlayerData data = Quartz.get(event.getPlayer().getUniqueId());
        String rankName = data.getString("rank");

        // Parse the rank so we can obtain the prefix.
        Rank rank = Rank.valueOf(rankName);

        // This is not the way to do chat formatting, but I am too lazy to learn
        // how to use the renderer feature on paper soooooo. ¯\_("/)_/¯
        event.setCancelled(true);

        // "Format" chat.
        Bukkit.broadcast(
                rank.getPrefix()
                        .append(Component.text(" "))
                        .append(Component.text(event.getPlayer().getName()))
                        .append(Component.text(": "))
                        .append(event.message().color(NamedTextColor.WHITE))
        );
    }

}
