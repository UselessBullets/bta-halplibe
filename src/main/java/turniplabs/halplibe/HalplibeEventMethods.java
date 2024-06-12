package turniplabs.halplibe;

import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityDispatcher;
import net.minecraft.core.item.Item;
import net.minecraft.core.world.Dimension;
import turniplabs.halplibe.event.annotations.EventListener;
import turniplabs.halplibe.events.RegisterDimensionsEvent;
import turniplabs.halplibe.events.RegisterEntityEvent;
import turniplabs.halplibe.events.block.RegisterBlocksEvent;
import turniplabs.halplibe.events.item.RegisterItemsEvent;

import java.util.Arrays;

public class HalplibeEventMethods {

    @EventListener
    public void testDimensionEvent(RegisterDimensionsEvent dimensionsEvent){
        System.out.println("Ran dimension event!");
        for (Dimension d : Dimension.getDimensionList().values()){
            System.out.println("\t-\t" + d.languageKey);
        }
    }

    @EventListener
    public void testEntityEvent(RegisterEntityEvent entityEvent){
        System.out.println("Ran entity event!");
        for (String s : EntityDispatcher.classToKeyMap.values()){
            System.out.println("\t-\t" + s);
        }
    }
    @EventListener
    public void testItemsEvent(RegisterItemsEvent itemsEvent){
        System.out.println("Ran item event!");
        System.out.println(Arrays.toString(stripNulls(Item.itemsList)));
    }
    @EventListener
    public void testBlocksEvent(RegisterBlocksEvent blocksEvent){
        System.out.println("Ran block event!");
        System.out.println(Arrays.toString(stripNulls(Block.blocksList)));
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
