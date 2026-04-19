package me.nebu.quartzExamplePlugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class ChatFormatting {

    /*
    * This is a handy class that allows us to easily format text and use them globally across
    * the project.
    */


    public static Component error(String text) {
        return Component.text(text).color(NamedTextColor.RED);
    }

    public static Component normal(String text) {
        return Component.text(text);
    }

}
