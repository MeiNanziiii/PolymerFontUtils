package ua.mei.pfu;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;
import ua.mei.pfu.font.FontResourceManager;
import ua.mei.pfu.font.provider.BitmapFontProvider;
import ua.mei.pfu.ui.ManagerUI;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class Commands {
    // TODO: improve command
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(literal("pfu")
                .requires(source -> source.hasPermissionLevel(4))
                .then(argument("identifier", IdentifierArgumentType.identifier())
                        .suggests((context, builder) -> {
                            for (FontResourceManager manager : FontResourceManager.managers) {
                                if (manager.providers.stream().anyMatch(provider -> provider instanceof BitmapFontProvider)) {
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
                                        if (manager.providers.stream().anyMatch(provider -> provider instanceof BitmapFontProvider)) {
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
