package creman.demonology.network.demon.client;

import creman.demonology.capabilities.CapabilityDemonology;
import creman.demonology.capabilities.ICapabilityDemonology;
import creman.demonology.network.ClientMessageHandler;
import creman.demonology.network.demon.common.PacketDemonology;
import net.minecraft.client.entity.EntityPlayerSP;

public class ClientHandlerDemonology extends ClientMessageHandler<PacketDemonology>
{
    @Override
    public void run(EntityPlayerSP player, PacketDemonology message)
    {
        ICapabilityDemonology capability = CapabilityDemonology.get(player);

        capability.setFogParameter(0, message.fogDensity);
        capability.setFogParameter(1, message.fogRed);
        capability.setFogParameter(2, message.fogBlue);
        capability.setFogParameter(3, message.fogGreen);
        capability.setRitualActive(message.isRitualActive);
    }
}

