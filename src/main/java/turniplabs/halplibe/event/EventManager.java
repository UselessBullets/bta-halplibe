package turniplabs.halplibe.event;

import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;
import turniplabs.halplibe.event.annotations.EventListener;
import turniplabs.halplibe.util.entrypoints.Entrypoints;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

public class EventManager {
    public static void addAllEventBuses(){
        List<Object> entrypoints = FabricLoader.getInstance().getEntrypoints(Entrypoints.EVENT_BUS, Object.class);
        for (Object c : entrypoints){
            registerAnnotatedClass(c);
        }
    }
    /**
     * Registers all the {@link EventListener} annotated methods in the provided {@link Class} into {@link turniplabs.halplibe.HalpLibe Halplibe}.
     *
     * @param listener Listener class to register
     *
     * @author Useless
     * @since beta.1
     */
    public static void registerAnnotatedClass(@NotNull Object listener){
        Method[] methods = listener.getClass().getMethods();
        for (Method m : methods){
            if (m.isAnnotationPresent(EventListener.class)){
                EventListener anno = m.getAnnotation(EventListener.class);
                Type[] types = m.getGenericParameterTypes();
                if (types.length != 1) throw new RuntimeException(String.format("Method '%s' in class '%s' has '%d' parameters, all event methods must have exactly 1 parameter!", m, listener.getClass().getName(), types.length));
                Class<?> event = (Class<?>) types[0];
                try {
                    EventContainer eventContainer = (EventContainer) event.getMethod("getEventContainer", null).invoke(null);
                    eventContainer.addEvent(listener, m, anno);
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(String.format("Type: %s, Error: %s", event, e));
                }
            }
        }
    }
}
