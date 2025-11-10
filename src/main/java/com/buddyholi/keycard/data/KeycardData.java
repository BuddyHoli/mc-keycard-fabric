package com.buddyholi.keycard.data;

import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a keycard in the system.
 * 
 * A keycard is an item that can be used to access protected areas and locked blocks/entities.
 * Each keycard has a unique identifier, name, and optional color/customization.
 * 
 * @author BuddyHoli
 */
public class KeycardData {
    
    /**
     * Unique identifier for this keycard
     */
    private final UUID id;
    
    /**
     * Display name of the keycard
     */
    private String name;
    
    /**
     * Color code for the keycard (in hex format, e.g., "#FF0000" for red)
     */
    private String color;
    
    /**
     * Timestamp when this keycard was created (Unix timestamp in milliseconds)
     */
    private final long createdAt;
    
    /**
     * Global registry of all keycards by their ID
     */
    private static final Map<UUID, KeycardData> KEYCARD_REGISTRY = new HashMap<>();
    
    /**
     * Global registry of all keycards by their name
     */
    private static final Map<String, KeycardData> KEYCARD_BY_NAME = new HashMap<>();
    
    /**
     * Creates a new keycard with the given name.
     * 
     * @param name The display name for this keycard
     */
    public KeycardData(String name) {
        this(UUID.randomUUID(), name, "#FFFFFF", System.currentTimeMillis());
    }
    
    /**
     * Creates a new keycard with the given name and color.
     * 
     * @param name The display name for this keycard
     * @param color The color code for this keycard (hex format)
     */
    public KeycardData(String name, String color) {
        this(UUID.randomUUID(), name, color, System.currentTimeMillis());
    }
    
    /**
     * Creates a keycard with full parameters (used for loading from persistence).
     * 
     * @param id Unique identifier
     * @param name Display name
     * @param color Color code
     * @param createdAt Creation timestamp
     */
    public KeycardData(UUID id, String name, String color, long createdAt) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.createdAt = createdAt;
    }
    
    // Getters
    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getColor() { return color; }
    public long getCreatedAt() { return createdAt; }
    
    // Setters
    public void setName(String name) { this.name = name; }
    public void setColor(String color) { this.color = color; }
    
    /**
     * Register this keycard in the global registry.
     */
    public void register() {
        KEYCARD_REGISTRY.put(id, this);
        KEYCARD_BY_NAME.put(name.toLowerCase(), this);
    }
    
    /**
     * Unregister this keycard from the global registry.
     */
    public void unregister() {
        KEYCARD_REGISTRY.remove(id);
        KEYCARD_BY_NAME.remove(name.toLowerCase());
    }
    
    /**
     * Get a keycard by its unique ID.
     * 
     * @param id The keycard ID
     * @return The keycard, or null if not found
     */
    public static KeycardData getById(UUID id) {
        return KEYCARD_REGISTRY.get(id);
    }
    
    /**
     * Get a keycard by its name (case-insensitive).
     * 
     * @param name The keycard name
     * @return The keycard, or null if not found
     */
    public static KeycardData getByName(String name) {
        return KEYCARD_BY_NAME.get(name.toLowerCase());
    }
    
    /**
     * Get all registered keycards.
     * 
     * @return Map of all keycards by ID
     */
    public static Map<UUID, KeycardData> getAllKeycards() {
        return new HashMap<>(KEYCARD_REGISTRY);
    }
    
    /**
     * Clear all keycards from the registry (used for testing/reloading).
     */
    public static void clearAll() {
        KEYCARD_REGISTRY.clear();
        KEYCARD_BY_NAME.clear();
    }
    
    @Override
    public String toString() {
        return String.format("Keycard{id=%s, name='%s', color='%s'}", id, name, color);
    }
}
