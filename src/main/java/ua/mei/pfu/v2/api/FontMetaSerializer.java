package ua.mei.pfu.v2.api;

import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.MapCodec;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.util.Identifier;

public interface FontMetaSerializer<T extends FontMetaProcessor<?>> {
    Registry<FontMetaSerializer<?>> REGISTRY = new SimpleRegistry<>(RegistryKey.ofRegistry(Identifier.of("pfu:serializer_types")), Lifecycle.stable());

    MapCodec<T> codec();

    static <S extends FontMetaSerializer<T>, T extends FontMetaProcessor<?>> S register(Identifier id, S serializer) {
        return Registry.register(REGISTRY, id, serializer);
    }
}
