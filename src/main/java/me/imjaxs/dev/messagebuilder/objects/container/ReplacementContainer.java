package me.imjaxs.dev.messagebuilder.objects.container;

import me.imjaxs.dev.messagebuilder.objects.ChatMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ReplacementContainer {
    private final Map<String, String> replacements = new HashMap<>();

    /**
     * Add a replacement
     *
     * @param key string to replace
     * @param value replacement for the string
     * @return the {@link ReplacementContainer}
     */
    public ReplacementContainer replace(String key, String value) {
        replacements.put(key, value);
        return this;
    }

    /**
     * @return {@link Map} of all replacements
     */
    public Map<String, String> getReplacements() {
        return new HashMap<>(replacements);
    }

    /**
     * Formats a string
     *
     * @param message the {@link ChatMessage} to format
     * @return the formatted {@link ChatMessage}
     */
    public ChatMessage format(ChatMessage message) {
        if (message.getMessage() != null) {
            String formatted = message.getMessage();
            for (Map.Entry<String, String> entry : replacements.entrySet())
                formatted = formatted.replace(entry.getKey(), entry.getValue());
            return message.setMessage(formatted);
        } else if (message.getMessages() != null) {
            List<String> formatted = message.getMessages();
            for (Map.Entry<String, String> entry : replacements.entrySet())
                IntStream.range(0, formatted.size()).forEachOrdered(i -> formatted.set(i, formatted.get(i).replace(entry.getKey(), entry.getValue())));
            return message.setMessages(formatted);
        }
        return message;
    }
}
