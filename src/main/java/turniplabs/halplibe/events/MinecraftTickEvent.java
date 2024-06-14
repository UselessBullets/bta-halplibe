package turniplabs.halplibe.events;

import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;
import turniplabs.halplibe.event.Event;
import turniplabs.halplibe.event.EventContainer;

public class MinecraftTickEvent extends Event {
    @NotNull
    public final Minecraft minecraft;
    public MinecraftTickEvent(final @NotNull Minecraft minecraft){
        this.minecraft = minecraft;
    }
    private static final EventContainer eventContainer = new EventContainer();
    @NotNull
    public EventContainer getEvents() {
        return eventContainer;
    }

    @NotNull
    public static EventContainer getEventContainer() {
        return eventContainer;
    }
}
