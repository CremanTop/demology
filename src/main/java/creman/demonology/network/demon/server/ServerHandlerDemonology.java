package creman.demonology.network.demon.server;

import creman.demonology.capabilities.CapabilityDemonology;
import creman.demonology.capabilities.ICapabilityDemonology;
import creman.demonology.network.ServerMessageHandler;
import creman.demonology.network.demon.common.PacketDemonology;
import net.minecraft.entity.player.EntityPlayerMP;

public class ServerHandlerDemonology extends ServerMessageHandler<PacketDemonology>
{
    @Override
    public void run(EntityPlayerMP player, PacketDemonology message)
    {
        ICapabilityDemonology capability = CapabilityDemonology.get(player);

        capability.setFogParameter(0, message.fogDensity);
        capability.setFogParameter(1, message.fogRed);
        capability.setFogParameter(2, message.fogBlue);
        capability.setFogParameter(3, message.fogGreen);
        capability.setRitualActive(message.isRitualActive);
    }
}

