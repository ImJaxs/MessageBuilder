package me.imjaxs.dev.messagebuilder.builder;

import me.imjaxs.dev.messagebuilder.interfacing.MessageContainer;
import me.imjaxs.dev.messagebuilder.objects.ChatMessage;
import me.imjaxs.dev.messagebuilder.objects.map.MessageMap;
import org.bukkit.configuration.ConfigurationSection;

import java.util.*;
import java.util.stream.Collectors;

public class MessageBuilder {
    private final Map<String, ChatMessage> messages = new HashMap<>();

    /**
     * Adds a message
     *
     * @param key message key
     * @param message content of the message
     * @param translate if alternate color codes (&) should get translated
     * @return the {@link MessageBuilder}
     */
    public MessageBuilder withMessage(String key, String message, boolean translate) {
        messages.put(key, new ChatMessage(color(message, translate)));
        return this;
    }

    /**
     * Adds a message
     *
     * @param key message key
     * @param message content of the message
     * @param translate if alternate color codes (&) should get translated
     * @return the {@link MessageBuilder}
     */
    public MessageBuilder withMessage(String key, List<String> message, boolean translate) {
        messages.put(key, new ChatMessage(color(message, translate)));
        return this;
    }

    /**
     * Adds a message
     *
     * @param key message key
     * @param message content of the message
     * @param translate if alternate color codes (&) should get translated
     * @return the {@link MessageBuilder}
     */
    public MessageBuilder withMessage(String key, String[] message, boolean translate) {
        messages.put(key, new ChatMessage(color(message, translate)));
        return this;
    }

    /**
     * Adds multiple messages
     *
     * @param messages {@link Map} of messages to add
     * @return the {@link MessageBuilder}
     */
    public MessageBuilder withMessages(Map<String, ChatMessage> messages) {
        this.messages.putAll(messages);
        return this;
    }

    /**
     * Loads the messages from a configuration section
     *
     * @param section section of the configuration containing the messages
     * @param translate if alternate color codes (&) should get translated
     * @return the {@link MessageBuilder}
     */
    public MessageBuilder withConfig(ConfigurationSection section, boolean translate) {
        for (String key : section.getKeys(true)) {
            if (section.isString(key)) {
                withMessage(key, section.getString(key), translate);
            } else if (section.isList(key)) {
                withMessage(key, section.getStringList(key), translate);
            }
        }
        return this;
    }

    /**
     * Builds the messages
     *
     * @return the new {@link MessageContainer}
     */
    public MessageContainer build() {
        return new MessageMap(messages);
    }

    protected String color(String string, boolean translate) {
        return translate ? org.bukkit.ChatColor.translateAlternateColorCodes('&', string) : string;
    }

    protected List<String> color(List<String> strings, boolean translate) {
        return translate ? strings.stream().map(string -> color(string, true)).collect(Collectors.toList()) : strings;
    }

    protected String[] color(String[] strings, boolean translate) {
        return translate ? Arrays.stream(strings).map(string -> color(string, true)).toArray(String[]::new) : strings;
    }
}
