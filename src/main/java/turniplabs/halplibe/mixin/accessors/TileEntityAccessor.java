package turniplabs.halplibe.mixin.accessors;

import net.minecraft.core.block.entity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Map;

@Mixin(value = TileEntity.class, remap = false)
public interface TileEntityAccessor {
    @Invoker("addMapping")
    static void callAddMapping(Class<?> clazz, String s) {
        throw new AssertionError();
    }

    @Accessor("nameToClassMap")
    static Map getNameToClassMap()  {
        throw new AssertionError();
    }
    @Accessor("classToNameMap")
    static Map getClassToNameMap()  {
        throw new AssertionError();
    }
}
