package creman.demonology.network.demon;

import creman.demonology.network.AbstractDispatcher;
import creman.demonology.network.demon.client.ClientHandlerDemonology;
import creman.demonology.network.demon.common.PacketDemonology;
import creman.demonology.network.demon.server.ServerHandlerDemonology;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;

import static creman.demonology.Demonology.MOD_ID;

public class Dispatcher
{
    public static final AbstractDispatcher DISPATCHER = new AbstractDispatcher(MOD_ID)
    {
        @Override
        public void register()
        {
            register(PacketDemonology.class, ServerHandlerDemonology.class, Side.SERVER);
            register(PacketDemonology.class, ClientHandlerDemonology.class, Side.CLIENT);
        }
    };

    /**
     * Send message to players who are tracking given entity
     */
    public static void sendToTracked(Entity entity, IMessage message)
    {
        DISPATCHER.sendToTracked(entity, message);
    }

    /**
     * Send message to given player
     */
    public static void sendTo(IMessage message, EntityPlayerMP player)
    {
        DISPATCHER.sendTo(message, player);
    }

    /**
     * Send message to the server
     */
    public static void sendToServer(IMessage message)
    {
        DISPATCHER.sendToServer(message);
    }

    /**
     * Register all the networking messages and message handlers
     */
    public static void register()
    {
        DISPATCHER.register();
    }
}
