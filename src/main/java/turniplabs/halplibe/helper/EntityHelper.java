package turniplabs.halplibe.helper;

import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.tileentity.TileEntityRenderer;
import net.minecraft.core.Global;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityDispatcher;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.mixin.accessors.RenderManagerAccessor;
import turniplabs.halplibe.mixin.accessors.TileEntityAccessor;
import turniplabs.halplibe.mixin.accessors.TileEntityRendererAccessor;

import java.util.Map;
import java.util.function.Supplier;

abstract public class EntityHelper {

    public static void createEntity(Class<? extends Entity> clazz, @Nullable Supplier<EntityRenderer<?>> rendererSupplier, int id, String entityId) {
        if (HalpLibe.isClient && rendererSupplier != null) Client.assignEntityRenderer(clazz, rendererSupplier.get());
        Core.createEntity(clazz, id, entityId);
    }


    public static void createTileEntity(Class<? extends TileEntity> clazz, @Nullable Supplier<TileEntityRenderer<?>> rendererSupplier, String entityId) {
        if (HalpLibe.isClient && rendererSupplier != null) Client.assignTileEntityRenderer(clazz, rendererSupplier.get());
        Core.createTileEntity(clazz, entityId);
    }

    /**
     * Functions to call from the client or server
     */
    public static class Core {
        @ApiStatus.Internal
        public static void createEntity(Class<? extends Entity> clazz, int id, String name) {
            EntityDispatcher.addMapping(clazz, name, id);
        }
        @ApiStatus.Internal
        public static void createTileEntity(Class<? extends TileEntity> clazz, String name) {
            TileEntityAccessor.callAddMapping(clazz, name);
        }

        /**
         * @deprecated Function is being split into {@link Core#createTileEntity(Class, String)} and {@link Client#assignTileEntityRenderer(Class, TileEntityRenderer)}
         */
        @Deprecated
        public static void createSpecialTileEntity(Class<? extends TileEntity> clazz, TileEntityRenderer<?> renderer, String name) {
            Map<Class<? extends TileEntity>, TileEntityRenderer<?>> specialRendererMap = ((TileEntityRendererAccessor) TileEntityRenderDispatcher.instance).getSpecialRendererMap();
            specialRendererMap.put(clazz, renderer);
            renderer.setRenderDispatcher(TileEntityRenderDispatcher.instance);

            TileEntityAccessor.callAddMapping(clazz, name);
        }
    }

    /**
     * Functions to only call from the client
     */
    public static class Client {
        @ApiStatus.Internal
        public static void assignEntityRenderer(Class<? extends Entity> clazz, EntityRenderer<?> renderer){
            Map<Class<? extends Entity>, EntityRenderer<?>> entityRenderMap = ((RenderManagerAccessor) EntityRenderDispatcher.instance).getEntityRenderMap();
            entityRenderMap.put(clazz, renderer);
            renderer.setRenderDispatcher(EntityRenderDispatcher.instance);
        }
        @SuppressWarnings("unused") // API function
        @ApiStatus.Internal
        public static void assignTileEntityRenderer(Class<? extends TileEntity> clazz, TileEntityRenderer<?> renderer){
            Map<Class<? extends TileEntity>, TileEntityRenderer<?>> specialRendererMap = ((TileEntityRendererAccessor) TileEntityRenderDispatcher.instance).getSpecialRendererMap();
            specialRendererMap.put(clazz, renderer);
            renderer.setRenderDispatcher(TileEntityRenderDispatcher.instance);
        }
    }
}
