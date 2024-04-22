package turniplabs.halplibe.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.Commands;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.ApiStatus;
import turniplabs.halplibe.HalpLibe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

abstract public class CommandHelper {
    @ApiStatus.Internal // used in CommandsCoreMixin
    private static final List<Command> coreCommands = new ArrayList<>();
    @ApiStatus.Internal // used in CommandsClientMixin
    private static final List<Function<AtomicReference<Minecraft>, Command>> clientCommands = new ArrayList<>();
    @ApiStatus.Internal // used in CommandsServerMixin
    private static final List<Function<AtomicReference<MinecraftServer>, Command>> serverCommands = new ArrayList<>();

    /**
     * @param command Command to be added to the commands list
     */
    public static void createCommand(Command command) {
        Core.createCommand(command);
    }

    /**
     * @param clientCommand Function that returns a client command when supplied with an AtomicReference<Minecraft>
     */
    public static void createClientCommand(Function<AtomicReference<Minecraft>, Command> clientCommand){
        Client.createCommand(clientCommand);
    }
    /**
     * @param serverCommand Function that returns a server command when supplied with an AtomicReference<Minecraft>
     */
    @SuppressWarnings("unused") // API function
    public static void createServerCommand(Function<AtomicReference<MinecraftServer>, Command> serverCommand){
        Server.createCommand(serverCommand);
    }
    /**
     * Functions to call from the client or server
     */
    @SuppressWarnings("unused") // API Class
    public static class Core {
        /**
         * @param command Command to be added to the commands list
         */
        @ApiStatus.Internal
        @SuppressWarnings("unused") // API function
        public static void createCommand(Command command) {
            coreCommands.add(command);
            if (!Commands.commands.isEmpty()){ // If commands already initialized add directly
                Commands.commands.add(command);
            }
        }
    }

    /**
     * Functions to call from the client
     */
    @SuppressWarnings("unused") // API Class
    public static class Client {
        /**
         * @param command Command to be added to the client commands list
         */
        @ApiStatus.Internal
        @SuppressWarnings("unused") // API function
        public static void createCommand(Command command) {
            if (!HalpLibe.isClient) return;
            coreCommands.add(command);
            if (!Commands.commands.isEmpty()){ // If commands already initialized add directly
                Commands.commands.add(command);
            }
        }

        /**
         * @param clientCommand Function that returns a client command when supplied with an AtomicReference<Minecraft>
         */
        @ApiStatus.Internal
        @SuppressWarnings("unused") // API function
        public static void createCommand(Function<AtomicReference<Minecraft>, Command> clientCommand){
            clientCommands.add(clientCommand);
            if (HalpLibe.isClient && !Commands.commands.isEmpty()){
                Commands.commands.add(clientCommand.apply(new AtomicReference<>(Minecraft.getMinecraft(Minecraft.class))));
            }
        }
    }

    /**
     * Functions to call from the server
     */
    @SuppressWarnings("unused") // API Class
    public static class Server {
        /**
         * @param command Command to be added to the server commands list
         */
        @ApiStatus.Internal
        @SuppressWarnings("unused") // API function
        public static void createCommand(Command command) {
            if (HalpLibe.isClient) return;
            coreCommands.add(command);
            if (!Commands.commands.isEmpty()){ // If commands already initialized add directly
                Commands.commands.add(command);
            }
        }

        /**
         * @param serverCommand Function that returns a server command when supplied with an AtomicReference<Minecraft>
         */
        @ApiStatus.Internal
        @SuppressWarnings("unused") // API function
        public static void createCommand(Function<AtomicReference<MinecraftServer>, Command> serverCommand){
            serverCommands.add(serverCommand);
            if (!HalpLibe.isClient && !Commands.commands.isEmpty()){
                Commands.commands.add(serverCommand.apply(new AtomicReference<>(MinecraftServer.getInstance())));
            }
        }
    }

}
