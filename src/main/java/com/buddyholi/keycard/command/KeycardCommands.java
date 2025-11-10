package com.buddyholi.keycard.command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

import com.buddyholi.keycard.KeycardMod;

/**
 * Handles registration and implementation of all keycard-related commands.
 * 
 * Available commands (as per specification):
 * - /keycard create <name> [color] - Create a new keycard
 * - /keycard give <player> <keycard> - Give a keycard to a player
 * - /keycard list - List all available keycards
 * - /keycard delete <keycard> - Delete a keycard
 * - /area create <name> <pos1> <pos2> - Create a protected area
 * - /area addkey <area> <keycard> - Add keycard access to an area
 * - /area removekey <area> <keycard> - Remove keycard access from an area
 * - /area list - List all protected areas
 * - /area delete <area> - Delete a protected area
 * - /lock <keycard> - Lock the targeted block/entity with a keycard
 * - /unlock - Remove lock from the targeted block/entity
 * 
 * @author BuddyHoli
 */
public class KeycardCommands {
    
    /**
     * Register all keycard commands with Fabric's command system.
     * Called during mod initialization.
     */
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            // Register /keycard command with subcommands
            dispatcher.register(CommandManager.literal("keycard")
                .requires(source -> source.hasPermissionLevel(2)) // Require OP level 2
                .then(CommandManager.literal("create")
                    .executes(context -> {
                        // TODO: Implement keycard creation
                        context.getSource().sendFeedback(() -> 
                            Text.literal("§c[KeyCard] TODO: Implement keycard create command"), false);
                        return 1;
                    }))
                .then(CommandManager.literal("give")
                    .executes(context -> {
                        // TODO: Implement giving keycard to player
                        context.getSource().sendFeedback(() -> 
                            Text.literal("§c[KeyCard] TODO: Implement keycard give command"), false);
                        return 1;
                    }))
                .then(CommandManager.literal("list")
                    .executes(context -> {
                        // TODO: Implement listing all keycards
                        context.getSource().sendFeedback(() -> 
                            Text.literal("§c[KeyCard] TODO: Implement keycard list command"), false);
                        return 1;
                    }))
                .then(CommandManager.literal("delete")
                    .executes(context -> {
                        // TODO: Implement deleting a keycard
                        context.getSource().sendFeedback(() -> 
                            Text.literal("§c[KeyCard] TODO: Implement keycard delete command"), false);
                        return 1;
                    }))
            );
            
            // Register /area command with subcommands
            dispatcher.register(CommandManager.literal("area")
                .requires(source -> source.hasPermissionLevel(2)) // Require OP level 2
                .then(CommandManager.literal("create")
                    .executes(context -> {
                        // TODO: Implement area creation
                        context.getSource().sendFeedback(() -> 
                            Text.literal("§c[KeyCard] TODO: Implement area create command"), false);
                        return 1;
                    }))
                .then(CommandManager.literal("addkey")
                    .executes(context -> {
                        // TODO: Implement adding keycard to area
                        context.getSource().sendFeedback(() -> 
                            Text.literal("§c[KeyCard] TODO: Implement area addkey command"), false);
                        return 1;
                    }))
                .then(CommandManager.literal("removekey")
                    .executes(context -> {
                        // TODO: Implement removing keycard from area
                        context.getSource().sendFeedback(() -> 
                            Text.literal("§c[KeyCard] TODO: Implement area removekey command"), false);
                        return 1;
                    }))
                .then(CommandManager.literal("list")
                    .executes(context -> {
                        // TODO: Implement listing all areas
                        context.getSource().sendFeedback(() -> 
                            Text.literal("§c[KeyCard] TODO: Implement area list command"), false);
                        return 1;
                    }))
                .then(CommandManager.literal("delete")
                    .executes(context -> {
                        // TODO: Implement deleting an area
                        context.getSource().sendFeedback(() -> 
                            Text.literal("§c[KeyCard] TODO: Implement area delete command"), false);
                        return 1;
                    }))
            );
            
            // Register /lock command
            dispatcher.register(CommandManager.literal("lock")
                .requires(source -> source.hasPermissionLevel(2)) // Require OP level 2
                .executes(context -> {
                    // TODO: Implement locking a block/entity
                    context.getSource().sendFeedback(() -> 
                        Text.literal("§c[KeyCard] TODO: Implement lock command"), false);
                    return 1;
                })
            );
            
            // Register /unlock command
            dispatcher.register(CommandManager.literal("unlock")
                .requires(source -> source.hasPermissionLevel(2)) // Require OP level 2
                .executes(context -> {
                    // TODO: Implement unlocking a block/entity
                    context.getSource().sendFeedback(() -> 
                        Text.literal("§c[KeyCard] TODO: Implement unlock command"), false);
                    return 1;
                })
            );
            
            KeycardMod.LOGGER.info("Registered all keycard commands");
        });
    }
}
