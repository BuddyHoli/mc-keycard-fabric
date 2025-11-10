package com.buddyholi.keycard.data;

import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.math.BlockPos;

/**
 * Represents a locked block or entity in the world.
 * 
 * Locked items can only be interacted with by players holding the correct keycard.
 * This includes blocks (doors, chests, etc.) and entities (item frames, armor stands, etc.).
 * 
 * @author BuddyHoli
 */
public class LockedItem {
    
    /**
     * Type of locked item
     */
    public enum ItemType {
        BLOCK,
        ENTITY
    }
    
    /**
     * Unique identifier for this locked item
     */
    private final UUID id;
    
    /**
     * Type of this locked item (block or entity)
     */
    private final ItemType type;
    
    /**
     * Dimension/world identifier (e.g., "minecraft:overworld")
     */
    private final String dimension;
    
    /**
     * Position of the locked block (only for blocks)
     */
    private BlockPos blockPos;
    
    /**
     * UUID of the locked entity (only for entities)
     */
    private UUID entityUuid;
    
    /**
     * Keycard ID required to access this locked item
     */
    private UUID requiredKeycardId;
    
    /**
     * Timestamp when this lock was created (Unix timestamp in milliseconds)
     */
    private final long createdAt;
    
    /**
     * Global registry of all locked blocks by their position
     */
    private static final Map<String, LockedItem> LOCKED_BLOCKS = new HashMap<>();
    
    /**
     * Global registry of all locked entities by their UUID
     */
    private static final Map<UUID, LockedItem> LOCKED_ENTITIES = new HashMap<>();
    
    /**
     * Creates a new locked block.
     * 
     * @param dimension The dimension identifier
     * @param pos The block position
     * @param keycardId The required keycard ID
     */
    public LockedItem(String dimension, BlockPos pos, UUID keycardId) {
        this.id = UUID.randomUUID();
        this.type = ItemType.BLOCK;
        this.dimension = dimension;
        this.blockPos = pos;
        this.requiredKeycardId = keycardId;
        this.createdAt = System.currentTimeMillis();
    }
    
    /**
     * Creates a new locked entity.
     * 
     * @param dimension The dimension identifier
     * @param entityUuid The entity UUID
     * @param keycardId The required keycard ID
     */
    public LockedItem(String dimension, UUID entityUuid, UUID keycardId) {
        this.id = UUID.randomUUID();
        this.type = ItemType.ENTITY;
        this.dimension = dimension;
        this.entityUuid = entityUuid;
        this.requiredKeycardId = keycardId;
        this.createdAt = System.currentTimeMillis();
    }
    
    /**
     * Creates a locked item with full parameters (used for loading from persistence).
     * 
     * @param id Unique identifier
     * @param type Item type
     * @param dimension Dimension identifier
     * @param blockPos Block position (for blocks)
     * @param entityUuid Entity UUID (for entities)
     * @param keycardId Required keycard ID
     * @param createdAt Creation timestamp
     */
    public LockedItem(UUID id, ItemType type, String dimension, BlockPos blockPos, UUID entityUuid, UUID keycardId, long createdAt) {
        this.id = id;
        this.type = type;
        this.dimension = dimension;
        this.blockPos = blockPos;
        this.entityUuid = entityUuid;
        this.requiredKeycardId = keycardId;
        this.createdAt = createdAt;
    }
    
    // Getters
    public UUID getId() { return id; }
    public ItemType getType() { return type; }
    public String getDimension() { return dimension; }
    public BlockPos getBlockPos() { return blockPos; }
    public UUID getEntityUuid() { return entityUuid; }
    public UUID getRequiredKeycardId() { return requiredKeycardId; }
    public long getCreatedAt() { return createdAt; }
    
    // Setters
    public void setRequiredKeycardId(UUID keycardId) { this.requiredKeycardId = keycardId; }
    
    /**
     * Register this locked item in the global registry.
     */
    public void register() {
        if (type == ItemType.BLOCK) {
            String key = makeBlockKey(dimension, blockPos);
            LOCKED_BLOCKS.put(key, this);
        } else if (type == ItemType.ENTITY) {
            LOCKED_ENTITIES.put(entityUuid, this);
        }
    }
    
    /**
     * Unregister this locked item from the global registry.
     */
    public void unregister() {
        if (type == ItemType.BLOCK) {
            String key = makeBlockKey(dimension, blockPos);
            LOCKED_BLOCKS.remove(key);
        } else if (type == ItemType.ENTITY) {
            LOCKED_ENTITIES.remove(entityUuid);
        }
    }
    
    /**
     * Create a unique key for a block position in a dimension.
     */
    private static String makeBlockKey(String dimension, BlockPos pos) {
        return String.format("%s:%d,%d,%d", dimension, pos.getX(), pos.getY(), pos.getZ());
    }
    
    /**
     * Get a locked block at the given position.
     * 
     * @param dimension The dimension identifier
     * @param pos The block position
     * @return The locked item, or null if not found
     */
    public static LockedItem getLockedBlock(String dimension, BlockPos pos) {
        String key = makeBlockKey(dimension, pos);
        return LOCKED_BLOCKS.get(key);
    }
    
    /**
     * Get a locked entity by its UUID.
     * 
     * @param entityUuid The entity UUID
     * @return The locked item, or null if not found
     */
    public static LockedItem getLockedEntity(UUID entityUuid) {
        return LOCKED_ENTITIES.get(entityUuid);
    }
    
    /**
     * Check if a block is locked.
     * 
     * @param dimension The dimension identifier
     * @param pos The block position
     * @return true if the block is locked, false otherwise
     */
    public static boolean isBlockLocked(String dimension, BlockPos pos) {
        return getLockedBlock(dimension, pos) != null;
    }
    
    /**
     * Check if an entity is locked.
     * 
     * @param entityUuid The entity UUID
     * @return true if the entity is locked, false otherwise
     */
    public static boolean isEntityLocked(UUID entityUuid) {
        return getLockedEntity(entityUuid) != null;
    }
    
    /**
     * Get all locked blocks.
     * 
     * @return Map of all locked blocks
     */
    public static Map<String, LockedItem> getAllLockedBlocks() {
        return new HashMap<>(LOCKED_BLOCKS);
    }
    
    /**
     * Get all locked entities.
     * 
     * @return Map of all locked entities
     */
    public static Map<UUID, LockedItem> getAllLockedEntities() {
        return new HashMap<>(LOCKED_ENTITIES);
    }
    
    /**
     * Clear all locked items from the registry (used for testing/reloading).
     */
    public static void clearAll() {
        LOCKED_BLOCKS.clear();
        LOCKED_ENTITIES.clear();
    }
    
    @Override
    public String toString() {
        if (type == ItemType.BLOCK) {
            return String.format("LockedItem{id=%s, type=BLOCK, dimension='%s', pos=%s, keycard=%s}", 
                id, dimension, blockPos, requiredKeycardId);
        } else {
            return String.format("LockedItem{id=%s, type=ENTITY, dimension='%s', entity=%s, keycard=%s}", 
                id, dimension, entityUuid, requiredKeycardId);
        }
    }
}
