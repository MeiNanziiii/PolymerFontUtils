package ua.mei.pfu.impl;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;
import ua.mei.pfu.api.FontResource;
import ua.mei.pfu.api.provider.BitmapFontProvider;
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
                            FontResource.resources.stream()
                                    .filter(resource -> resource.providers.stream().anyMatch(provider -> provider instanceof BitmapFontProvider))
                                    .map(resource -> resource.identifier.toString())
                                    .forEach(builder::suggest);

                            return builder.buildFuture();
                        })
                        .executes(context -> {
                            Identifier identifier = IdentifierArgumentType.getIdentifier(context, "identifier");

                            if (context.getSource().isExecutedByPlayer()) {
                                FontResource.resources.stream()
                                        .filter(resource -> resource.identifier.equals(identifier) && resource.providers.stream().anyMatch(provider -> provider instanceof BitmapFontProvider))
                                        .findFirst()
                                        .ifPresent(resource -> {
                                            new ManagerUI(resource).open(context.getSource().getPlayer());
                                        });
                            }
                            return 0;
                        })
                )
        );
    }
}
