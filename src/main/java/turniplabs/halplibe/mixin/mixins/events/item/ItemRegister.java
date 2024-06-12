package turniplabs.halplibe.mixin.mixins.events.item;

import net.minecraft.core.achievement.stat.StatList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.halplibe.events.item.RegisterItemsEvent;

@Mixin(value = StatList.class, remap = false) // It injects into the StatList class because for some reason it fully and completely rejected my inject attempts :'(
public class ItemRegister {
    @Inject(method = "onItemInit()V", at = @At(value = "HEAD"))
    private static void RegisterItemsEventMethod(CallbackInfo ci){
        RegisterItemsEvent.getEventContainer().runMethods(new RegisterItemsEvent());
    }
}
