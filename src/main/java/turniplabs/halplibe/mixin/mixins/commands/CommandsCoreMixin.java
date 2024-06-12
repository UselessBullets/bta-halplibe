package turniplabs.halplibe.mixin.mixins.commands;

import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.Commands;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.halplibe.helper.CommandHelper;

import java.lang.reflect.Field;
import java.util.List;

@Mixin(value = Commands.class, remap = false)
public class CommandsCoreMixin {
    @Shadow public static List<Command> commands;
    @Inject(method = "initCommands()V", at = @At("TAIL"))
    private static void addCoreCommands(CallbackInfo ci) {
        commands.addAll(CommandHelper.coreCommands);
    }
}
