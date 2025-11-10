package com.buddyholi.keycard.data;

import java.util.UUID;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.math.BlockPos;

/**
 * Represents a protected area in the world.
 * 
 * Areas define 3D regions that can only be accessed by players holding specific keycards.
 * Each area is defined by two corner positions (min and max) and has a set of allowed keycards.
 * 
 * @author BuddyHoli
 */
public class Area {
    
    /**
     * Unique identifier for this area
     */
    private final UUID id;
    
    /**
     * Display name of the area
     */
    private String name;
    
    /**
     * Dimension/world identifier (e.g., "minecraft:overworld", "minecraft:the_nether")
     */
    private String dimension;
    
    /**
     * Minimum corner of the area bounding box
     */
    private BlockPos minPos;
    
    /**
     * Maximum corner of the area bounding box
     */
    private BlockPos maxPos;
    
    /**
     * Set of keycard IDs that grant access to this area
     */
    private final Set<UUID> allowedKeycards;
    
    /**
     * Timestamp when this area was created (Unix timestamp in milliseconds)
     */
    private final long createdAt;
    
    /**
     * Global registry of all areas by their ID
     */
    private static final Map<UUID, Area> AREA_REGISTRY = new HashMap<>();
    
    /**
     * Global registry of all areas by their name
     */
    private static final Map<String, Area> AREA_BY_NAME = new HashMap<>();
    
    /**
     * Creates a new area with the given name and positions.
     * 
     * @param name The display name for this area
     * @param dimension The dimension/world identifier
     * @param pos1 First corner position
     * @param pos2 Second corner position
     */
    public Area(String name, String dimension, BlockPos pos1, BlockPos pos2) {
        this(UUID.randomUUID(), name, dimension, pos1, pos2, System.currentTimeMillis());
    }
    
    /**
     * Creates an area with full parameters (used for loading from persistence).
     * 
     * @param id Unique identifier
     * @param name Display name
     * @param dimension Dimension identifier
     * @param pos1 First corner position
     * @param pos2 Second corner position
     * @param createdAt Creation timestamp
     */
    public Area(UUID id, String name, String dimension, BlockPos pos1, BlockPos pos2, long createdAt) {
        this.id = id;
        this.name = name;
        this.dimension = dimension;
        this.createdAt = createdAt;
        this.allowedKeycards = new HashSet<>();
        
        // Calculate min and max positions
        int minX = Math.min(pos1.getX(), pos2.getX());
        int minY = Math.min(pos1.getY(), pos2.getY());
        int minZ = Math.min(pos1.getZ(), pos2.getZ());
        int maxX = Math.max(pos1.getX(), pos2.getX());
        int maxY = Math.max(pos1.getY(), pos2.getY());
        int maxZ = Math.max(pos1.getZ(), pos2.getZ());
        
        this.minPos = new BlockPos(minX, minY, minZ);
        this.maxPos = new BlockPos(maxX, maxY, maxZ);
    }
    
    // Getters
    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getDimension() { return dimension; }
    public BlockPos getMinPos() { return minPos; }
    public BlockPos getMaxPos() { return maxPos; }
    public Set<UUID> getAllowedKeycards() { return new HashSet<>(allowedKeycards); }
    public long getCreatedAt() { return createdAt; }
    
    // Setters
    public void setName(String name) { this.name = name; }
    
    /**
     * Add a keycard to the list of allowed keycards for this area.
     * 
     * @param keycardId The keycard ID to add
     */
    public void addKeycard(UUID keycardId) {
        allowedKeycards.add(keycardId);
    }
    
    /**
     * Remove a keycard from the list of allowed keycards for this area.
     * 
     * @param keycardId The keycard ID to remove
     */
    public void removeKeycard(UUID keycardId) {
        allowedKeycards.remove(keycardId);
    }
    
    /**
     * Check if a keycard is allowed to access this area.
     * 
     * @param keycardId The keycard ID to check
     * @return true if the keycard is allowed, false otherwise
     */
    public boolean isKeycardAllowed(UUID keycardId) {
        return allowedKeycards.contains(keycardId);
    }
    
    /**
     * Check if a position is inside this area.
     * 
     * @param pos The position to check
     * @param dimensionId The dimension identifier
     * @return true if the position is inside this area, false otherwise
     */
    public boolean contains(BlockPos pos, String dimensionId) {
        if (!this.dimension.equals(dimensionId)) {
            return false;
        }
        
        return pos.getX() >= minPos.getX() && pos.getX() <= maxPos.getX() &&
               pos.getY() >= minPos.getY() && pos.getY() <= maxPos.getY() &&
               pos.getZ() >= minPos.getZ() && pos.getZ() <= maxPos.getZ();
    }
    
    /**
     * Register this area in the global registry.
     */
    public void register() {
        AREA_REGISTRY.put(id, this);
        AREA_BY_NAME.put(name.toLowerCase(), this);
    }
    
    /**
     * Unregister this area from the global registry.
     */
    public void unregister() {
        AREA_REGISTRY.remove(id);
        AREA_BY_NAME.remove(name.toLowerCase());
    }
    
    /**
     * Get an area by its unique ID.
     * 
     * @param id The area ID
     * @return The area, or null if not found
     */
    public static Area getById(UUID id) {
        return AREA_REGISTRY.get(id);
    }
    
    /**
     * Get an area by its name (case-insensitive).
     * 
     * @param name The area name
     * @return The area, or null if not found
     */
    public static Area getByName(String name) {
        return AREA_BY_NAME.get(name.toLowerCase());
    }
    
    /**
     * Get all areas that contain the given position.
     * 
     * @param pos The position to check
     * @param dimensionId The dimension identifier
     * @return Set of areas containing the position
     */
    public static Set<Area> getAreasAt(BlockPos pos, String dimensionId) {
        Set<Area> areas = new HashSet<>();
        for (Area area : AREA_REGISTRY.values()) {
            if (area.contains(pos, dimensionId)) {
                areas.add(area);
            }
        }
        return areas;
    }
    
    /**
     * Get all registered areas.
     * 
     * @return Map of all areas by ID
     */
    public static Map<UUID, Area> getAllAreas() {
        return new HashMap<>(AREA_REGISTRY);
    }
    
    /**
     * Clear all areas from the registry (used for testing/reloading).
     */
    public static void clearAll() {
        AREA_REGISTRY.clear();
        AREA_BY_NAME.clear();
    }
    
    @Override
    public String toString() {
        return String.format("Area{id=%s, name='%s', dimension='%s', min=%s, max=%s, keycards=%d}", 
            id, name, dimension, minPos, maxPos, allowedKeycards.size());
    }
}
