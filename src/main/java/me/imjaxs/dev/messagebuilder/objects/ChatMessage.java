package me.imjaxs.dev.messagebuilder.objects;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class ChatMessage {
    private String message;
    private List<String> messages;

    /**
     * Create a new message
     *
     * @param message content of the message
     */
    public ChatMessage(String message) {
        this.message = message;
        this.messages = null;
    }

    /**
     * Create a new message
     *
     * @param messages content of the message
     */
    public ChatMessage(List<String> messages) {
        this.message = null;
        this.messages = messages;
    }

    /**
     * Create a new message
     *
     * @param messages content of the message
     */
    public ChatMessage(String[] messages) {
        this(Arrays.asList(messages));
    }

    /**
     * @return the message content {@link String}
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the message content {@link List}
     */
    public List<String> getMessages() {
        return messages;
    }

    /**
     * Set the message
     *
     * @param message content of the message
     * @return the {@link ChatMessage}
     */
    public ChatMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Set the message
     *
     * @param messages content of the message
     * @return the {@link ChatMessage}
     */
    public ChatMessage setMessages(List<String> messages) {
        this.messages = messages;
        return this;
    }

    /**
     * Set the message
     *
     * @param messages content of the message
     * @return the {@link ChatMessage}
     */
    public ChatMessage setMessages(String[] messages) {
        return setMessages(Arrays.asList(messages));
    }

    /**
     * Send the message
     *
     * @param sender instance of {@link org.bukkit.command.ConsoleCommandSender}
     */
    public void send(CommandSender sender) {
        if (message != null)
            sender.sendMessage(message);

        if (messages != null)
            messages.forEach(sender::sendMessage);
    }

    /**
     * Send the message in broadcast
     */
    public void broadcast() {
        if (message != null)
            Bukkit.broadcastMessage(message);

        if (messages != null)
            messages.forEach(Bukkit::broadcastMessage);
    }

    /**
     * Broadcasts the message with permission
     *
     * @param permission the permission
     */
    public void broadcast(String permission) {
        if (message != null)
            Bukkit.broadcast(message, permission);

        if (messages != null)
            messages.forEach(message -> Bukkit.broadcast(message, permission));
    }
}
