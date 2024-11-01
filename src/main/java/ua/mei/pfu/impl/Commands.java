package ua.mei.pfu.impl;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;
import ua.mei.pfu.api.font.FontResourceManager;
import ua.mei.pfu.impl.ui.ManagerUI;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

@ApiStatus.Internal
public class Commands {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(literal("pfu")
                .requires(source -> source.hasPermissionLevel(4))
                .then(argument("identifier", IdentifierArgumentType.identifier())
                        .suggests((context, builder) -> {
                            for (FontResourceManager manager : FontResourceManager.managers) {
                                if (!manager.glyphs.isEmpty()) {
                                    builder.suggest(manager.identifier.toString());
                                }
                            }
                            return builder.buildFuture();
                        })
                        .executes(context -> {
                            Identifier identifier = IdentifierArgumentType.getIdentifier(context, "identifier");

                            if (context.getSource().isExecutedByPlayer()) {
                                for (FontResourceManager manager : FontResourceManager.managers) {
                                    if (manager.identifier.equals(identifier)) {
                                        if (!manager.glyphs.isEmpty()) {
                                            new ManagerUI(manager).open(context.getSource().getPlayer());
                                            return 1;
                                        }
                                    }
                                }
                            }
                            return 0;
                        })
                )
        );
    }
}
