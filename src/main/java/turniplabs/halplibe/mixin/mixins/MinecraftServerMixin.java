package turniplabs.halplibe.mixin.mixins;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Global;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;
import turniplabs.halplibe.util.entrypoints.Entrypoints;

@Mixin(value = MinecraftServer.class, remap = false)
public class MinecraftServerMixin {
    @Shadow private static MinecraftServer instance;

    @Inject(method = "startServer", at = @At(value = "INVOKE",target = "Lnet/minecraft/core/data/DataLoader;loadRecipesFromFile(Ljava/lang/String;)V", ordinal = 3, shift = At.Shift.AFTER))
    public void recipeEntrypoint(CallbackInfoReturnable<Boolean> cir){
        FabricLoader.getInstance().getEntrypoints(Entrypoints.RECIPES_READY, RecipeEntrypoint.class).forEach(RecipeEntrypoint::initNamespaces);
        FabricLoader.getInstance().getEntrypoints(Entrypoints.RECIPES_READY, RecipeEntrypoint.class).forEach(RecipeEntrypoint::onRecipesReady);
    }
    @Inject(method = "startServer", at = @At("HEAD"))
    public void beforeGameStartEntrypoint(CallbackInfoReturnable<Boolean> cir){
        instance = (MinecraftServer)(Object)this;
        Global.isServer = true;
        FabricLoader.getInstance().getEntrypoints(Entrypoints.BEFORE_GAME_START, GameStartEntrypoint.class).forEach(GameStartEntrypoint::beforeGameStart);
    }

    @Inject(method = "startServer", at = @At("TAIL"))
    public void afterGameStartEntrypoint(CallbackInfoReturnable<Boolean> cir){
        FabricLoader.getInstance().getEntrypoints(Entrypoints.AFTER_GAME_START, GameStartEntrypoint.class).forEach(GameStartEntrypoint::afterGameStart);
        if (HalpLibe.exportRecipes){
            RecipeBuilder.exportRecipes();
        }
    }
}
