package me.imjaxs.dev.messagebuilder.objects.map;

import me.imjaxs.dev.messagebuilder.interfacing.MessageContainer;
import me.imjaxs.dev.messagebuilder.objects.ChatMessage;
import me.imjaxs.dev.messagebuilder.objects.container.ReplacementContainer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MessageMap implements MessageContainer {
    private final Map<String, ChatMessage> messages = new HashMap<>();

    public MessageMap(Map<String, ChatMessage> messages) {
        this.messages.putAll(messages);
    }

    @Override
    public ChatMessage getMessage(String key) {
        return messages.get(key);
    }

    @Override
    public ChatMessage getMessage(String key, ReplacementContainer container) {
        return messages.containsKey(key) ? container.format(messages.get(key)) : null;
    }

    @Override
    public Set<ChatMessage> getMessages() {
        return new HashSet<>(messages.values());
    }

    @Override
    public Set<String> getMessageKeys() {
        return new HashSet<>(messages.keySet());
    }
}
