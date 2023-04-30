package be.moondevelopment.moonapi.framework.utils;


import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Collections;

public class ChatUtil {
    /**
     * Good size bar for scoreboards
     */
    public static final String SB_BAR = ChatColor.STRIKETHROUGH + "----------------------";
    /**
     * Good size bar for guis
     */
    public static final String MENU_BAR = ChatColor.STRIKETHROUGH + "------------------------";
    /**
     * Good size bar for information messages
     */
    public static final String CHAT_BAR = ChatColor.STRIKETHROUGH + "------------------------------------------------";
    /**
     * No text in the message, adds a bit of space between a message
     */
    public static final String BLANK_MESSAGE = String.join("", Collections.nCopies(150, " \n"));

    public static void sendFormattedText(Player target, String text, ClickEvent clickEvent, HoverEvent hoverEvent) {
        TextComponent message = new TextComponent(text);
        message.setClickEvent(clickEvent);
        message.setHoverEvent(hoverEvent);
        target.spigot().sendMessage(message);
    }

    public static void sendFormattedText(Player target, String text, ClickEvent clickEvent) {
        TextComponent message = new TextComponent(text);
        message.setClickEvent(clickEvent);
        target.spigot().sendMessage(message);
    }

    public static void sendFormattedText(Player target, String text, HoverEvent hoverEvent) {
        TextComponent message = new TextComponent(text);
        message.setHoverEvent(hoverEvent);
        target.spigot().sendMessage(message);
    }

    public static void sendFormattedText(Player target, String text) {
        TextComponent message = new TextComponent(text);
        target.spigot().sendMessage();
    }

    /**
     * A useful enum with a bunch of symbols you may want to access
     */
    public enum Symbols {
        /**
         * Returns "❤" symbol
         */
        HEALTH(StringEscapeUtils.unescapeJava("\u2764")),
        /**
         * Returns "«" symbol
         */
        ARROW_LEFT(StringEscapeUtils.unescapeJava("\u00AB")),
        /**
         * Returns "»" symbol
         */
        ARROW_RIGHT(StringEscapeUtils.unescapeJava("\u00BB")),
        /**
         * Returns "✖" symbol
         */
        CROSS(StringEscapeUtils.unescapeJava("\u2716")),
        /**
         * Returns "✓" symbol
         */
        CHECKMARK(StringEscapeUtils.unescapeJava("\u2713")),
        /**
         * Returns "⚠" symbol
         */
        WARNING(StringEscapeUtils.unescapeJava("\u26A0"));

        private final String symbol;

        /**
         * The constructor for the symbol enum
         * @param symbol the symbol unicode
         */
        Symbols(String symbol) {
            this.symbol = symbol;
        }

        /**
         * A method to get the unicode symbol
         * @return the symbol
         */
        public String getSymbol() {
            return symbol;
        }
    }

}
