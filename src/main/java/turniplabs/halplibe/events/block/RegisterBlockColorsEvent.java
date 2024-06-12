package turniplabs.halplibe.events.block;

import net.minecraft.client.render.block.color.BlockColorDispatcher;
import org.jetbrains.annotations.NotNull;
import turniplabs.halplibe.event.Event;
import turniplabs.halplibe.event.EventContainer;

import java.util.Objects;

public class RegisterBlockColorsEvent extends Event {
    @NotNull
    public final BlockColorDispatcher blockColorDispatcher;
    public RegisterBlockColorsEvent(final @NotNull BlockColorDispatcher blockColorDispatcher){
        this.blockColorDispatcher = Objects.requireNonNull(blockColorDispatcher);
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
