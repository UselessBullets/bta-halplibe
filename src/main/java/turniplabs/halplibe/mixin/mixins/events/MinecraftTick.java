package turniplabs.halplibe.mixin.mixins.events;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.halplibe.events.MinecraftTickEvent;

@Mixin(value = Minecraft.class, remap = false)
public class MinecraftTick {
    @Inject(method = "runTick()V", at = @At("HEAD"))
    private void minecraftTickEventMethod(CallbackInfo ci){
        MinecraftTickEvent.getEventContainer().runMethods(new MinecraftTickEvent((Minecraft) (Object) this));
    }
}
