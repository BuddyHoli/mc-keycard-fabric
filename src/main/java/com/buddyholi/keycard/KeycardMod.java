package com.buddyholi.keycard;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buddyholi.keycard.command.KeycardCommands;

/**
 * Main entry point for the mc-keycard-fabric mod.
 * 
 * This mod provides a keycard system for Minecraft, allowing players to:
 * - Create and manage keycards
 * - Define protected areas
 * - Lock blocks and entities with keycards
 * - Manage access permissions
 * 
 * @author BuddyHoli
 * @version 1.0.0
 */
public class KeycardMod implements ModInitializer {
    
    /**
     * Mod ID used for identification in fabric.mod.json and throughout the mod
     */
    public static final String MOD_ID = "mc-keycard-fabric";
    
    /**
     * Logger instance for this mod
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    /**
     * Mod initialization method called by Fabric Loader.
     * This is where we register commands, event handlers, and initialize data structures.
     */
    @Override
    public void onInitialize() {
        LOGGER.info("Initializing mc-keycard-fabric mod...");
        
        // Register commands
        KeycardCommands.register();
        LOGGER.info("Registered keycard commands");
        
        // TODO: Register event handlers
        // - PlayerInteractBlockEvent (check if player has keycard to access locked blocks)
        // - PlayerInteractEntityEvent (check if player has keycard to access locked entities)
        // - BlockBreakEvent (prevent breaking locked blocks without proper keycard)
        // - EntityDamageEvent (prevent damaging locked entities without proper keycard)
        
        // TODO: Load configuration from config/mc-keycard.toml
        // - Default keycard behavior
        // - Area protection settings
        // - Persistence settings
        
        // TODO: Initialize data persistence
        // - Load existing keycards from file
        // - Load existing areas from file
        // - Load existing locked items from file
        
        LOGGER.info("mc-keycard-fabric mod initialized successfully!");
    }
}
