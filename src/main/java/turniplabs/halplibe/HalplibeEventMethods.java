package turniplabs.halplibe;

import net.minecraft.client.render.block.color.BlockColor;
import net.minecraft.client.render.block.model.BlockModel;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityDispatcher;
import net.minecraft.core.item.Item;
import net.minecraft.core.sound.BlockSound;
import net.minecraft.core.world.Dimension;
import org.lwjgl.input.Keyboard;
import turniplabs.halplibe.event.Event;
import turniplabs.halplibe.event.EventContainer;
import turniplabs.halplibe.event.annotations.EventListener;
import turniplabs.halplibe.events.MinecraftTickEvent;
import turniplabs.halplibe.events.RegisterDimensionsEvent;
import turniplabs.halplibe.events.block.RegisterBlockColorsEvent;
import turniplabs.halplibe.events.block.RegisterBlockModelsEvent;
import turniplabs.halplibe.events.block.RegisterBlockSoundsEvent;
import turniplabs.halplibe.events.block.RegisterBlocksEvent;
import turniplabs.halplibe.events.entity.RegisterEntityEvent;
import turniplabs.halplibe.events.entity.RegisterEntityRendererEvent;
import turniplabs.halplibe.events.item.RegisterItemModelEvent;
import turniplabs.halplibe.events.item.RegisterItemsEvent;
import turniplabs.halplibe.events.tileentity.RegisterTileEntityEvent;
import turniplabs.halplibe.events.tileentity.RegisterTileEntityRendererEvent;
import turniplabs.halplibe.mixin.accessors.TileEntityAccessor;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HalplibeEventMethods {
    private static boolean wasHeld = false;
    private static int maxStringLength = 0;
    private static final Map<Class<?>, Boolean> eventTestRun = new HashMap<>();
    static {
        // Add all test event classes to map
        Method[] methods = HalplibeEventMethods.class.getMethods();
        for (Method m : methods){
            if (m.isAnnotationPresent(EventListener.class)){
                Type[] types = m.getGenericParameterTypes();
                if (types.length != 1) throw new RuntimeException(String.format("Method '%s' in class '%s' has '%d' parameters, all event methods must have exactly 1 parameter!", m, HalplibeEventMethods.class.getName(), types.length));
                Class<?> event = (Class<?>) types[0];
                eventTestRun.put(event, false);
                if (event.getSimpleName().length() > maxStringLength){
                    maxStringLength = event.getSimpleName().length();
                }
            }
        }
    }
    private static void ranEvent(Event e){
        if (!eventTestRun.get(e.getClass())){
            System.out.printf("Ran event '%s'!\n", e.getClass().getSimpleName());
        }
        eventTestRun.put(e.getClass(), true);
    }
    @EventListener
    public void testDimensionEvent(RegisterDimensionsEvent dimensionsEvent){
        ranEvent(dimensionsEvent);
        for (Dimension d : Dimension.getDimensionList().values()){
            System.out.println("\t-\t" + d.languageKey);
        }
    }

    @EventListener
    public void testEntityEvent(RegisterEntityEvent entityEvent){
        ranEvent(entityEvent);
        for (String s : EntityDispatcher.classToKeyMap.values()){
            System.out.println("\t-\t" + s);
        }
    }
    @EventListener
    public void testEntityRendererEvent(RegisterEntityRendererEvent entityRendererEvent){
        ranEvent(entityRendererEvent);
    }
    @EventListener
    public void testItemsEvent(RegisterItemsEvent itemsEvent){
        ranEvent(itemsEvent);
        System.out.println(Arrays.toString(stripNulls(Item.itemsList)));
    }
    @EventListener
    public void testItemsModelsEvent(RegisterItemModelEvent itemModelEvent){
        ranEvent(itemModelEvent);
        for (Item i : Item.itemsList){
            if (i == null) continue;
            ItemModel model = itemModelEvent.itemModelDispatcher.getDispatch(i);
            if (model == null) continue;
            System.out.println("\t" + i.getKey() + "\t-\t" + model.getClass());
        }
    }
    @EventListener
    public void testBlocksEvent(RegisterBlocksEvent blocksEvent){
        ranEvent(blocksEvent);
        System.out.println(Arrays.toString(stripNulls(Block.blocksList)));
    }

    @EventListener
    public void testBlockModelsEvent(RegisterBlockModelsEvent blockModelsEvent){
        ranEvent(blockModelsEvent);
        for (Block i : Block.blocksList){
            if (i == null) continue;
            BlockModel model = blockModelsEvent.blockModelDispatcher.getDispatch(i);
            if (model == null) continue;
            System.out.println("\t" + i.getKey() + "\t-\t" + model.getClass());
        }
    }

    @EventListener
    public void testBlockModelsEvent(RegisterBlockColorsEvent blockColorsEvent){
        ranEvent(blockColorsEvent);
        for (Block i : Block.blocksList){
            if (i == null) continue;
            BlockColor color = blockColorsEvent.blockColorDispatcher.getDispatch(i);
            if (color == null) continue;
            System.out.println("\t" + i.getKey() + "\t-\t" + color.getClass());
        }
    }

    @EventListener
    public void testBlockSoundsEvent(RegisterBlockSoundsEvent blockSoundsEvent){
        ranEvent(blockSoundsEvent);
        for (Block i : Block.blocksList){
            if (i == null) continue;
            BlockSound sound = blockSoundsEvent.blockSoundDispatcher.getDispatch(i);
            if (sound == null) continue;
            System.out.println("\t" + i.getKey() + "\t-\t" + sound.getStepSoundName());
        }
    }

    @EventListener
    public void testTileEntityEvent(RegisterTileEntityEvent tileEntityEvent){
        ranEvent(tileEntityEvent);
        for (Object s : TileEntityAccessor.getClassToNameMap().values()){
            System.out.println("\t-\t" + s);
        }
    }

    @EventListener
    public void testTileEntityRendererEvent(RegisterTileEntityRendererEvent tileEntityRendererEvent){
        ranEvent(tileEntityRendererEvent);
    }

    @EventListener
    public void testMinecraftTickEvent(MinecraftTickEvent minecraftTickEvent){
        ranEvent(minecraftTickEvent);
        int eventsRun = 0;
        if (!wasHeld && Keyboard.isKeyDown(Keyboard.KEY_F8)){
            for (Map.Entry<Class<?>, Boolean> e : eventTestRun.entrySet()){
                System.out.printf("Class: %s \t - \t Ran: %s\n", makePadding(e.getKey().getSimpleName(), maxStringLength), e.getValue());
                if (e.getValue()){
                    eventsRun++;
                }
            }
            System.out.printf("%s out of %s events ran. Status: %s\n", eventsRun, EventContainer.totalContainers, (eventsRun == EventContainer.totalContainers) ? "Success" : "Failed");
        }
        wasHeld = Keyboard.isKeyDown(Keyboard.KEY_F8);
    }
    private String makePadding(String text, int size){
        int neededSpaces = size - text.length();
        StringBuilder builder = new StringBuilder(text);
        for (int i = 0; i < neededSpaces; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }
    private <T> T[] stripNulls(T[] array){
        Object[] tempArr = new Object[array.length];
        int currentIndex = 0;
        for (int i = 0; i < array.length; i++) {
            T obj = array[i];
            if (obj == null) continue;
            tempArr[currentIndex++] = obj;
        }
        Object[] outArr = new Object[currentIndex];
        for (int i = 0; i < outArr.length; i++) {
            outArr[i] = tempArr[i];
        }
        return (T[]) outArr;
    }
}
