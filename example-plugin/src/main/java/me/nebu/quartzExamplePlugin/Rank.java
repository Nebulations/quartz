package me.nebu.quartzExamplePlugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public enum Rank {

    // Define new ranks here.
    ADMIN(Component.text("[ADMIN]").color(NamedTextColor.RED)),
    LEGEND(Component.text("[LEGEND]").color(NamedTextColor.GOLD)),
    VIP(Component.text("[VIP]").color(NamedTextColor.GREEN)),
    OWNER(Component.text("[OWNER]").color(NamedTextColor.LIGHT_PURPLE));

    private final Component prefix;

    Rank(Component prefix) {
        this.prefix = prefix;
    }

    public Component getPrefix() {
        return prefix;
    }

}
