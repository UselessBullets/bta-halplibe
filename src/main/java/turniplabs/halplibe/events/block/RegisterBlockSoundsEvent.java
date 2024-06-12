package turniplabs.halplibe.events.block;

import net.minecraft.core.sound.BlockSoundDispatcher;
import org.jetbrains.annotations.NotNull;
import turniplabs.halplibe.event.Event;
import turniplabs.halplibe.event.EventContainer;

import java.util.Objects;

public class RegisterBlockSoundsEvent extends Event {
    @NotNull
    public final BlockSoundDispatcher blockSoundDispatcher;
    public RegisterBlockSoundsEvent(final @NotNull BlockSoundDispatcher blockSoundDispatcher){
        this.blockSoundDispatcher = Objects.requireNonNull(blockSoundDispatcher);
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
