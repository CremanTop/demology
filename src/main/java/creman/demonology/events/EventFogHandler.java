package creman.demonology.events;

import creman.demonology.capabilities.CapabilityDemonology;
import creman.demonology.capabilities.ICapabilityDemonology;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventFogHandler
{
    @SubscribeEvent
    public void fogColor(EntityViewRenderEvent.FogColors e)
    {
        if(e.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ICapabilityDemonology capability = CapabilityDemonology.get(player);
            if (capability.isRitualActive())
            {
                e.setRed(capability.getFogParameter(1));
                e.setGreen(capability.getFogParameter(2));
                e.setBlue(capability.getFogParameter(3));
            }
        }
    }

    @SubscribeEvent
    public void fogDensity(EntityViewRenderEvent.FogDensity e)
    {
        if(e.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ICapabilityDemonology capability = CapabilityDemonology.get(player);
            if (capability.isRitualActive())
            {
                e.setDensity(capability.getFogParameter(0));
                e.setCanceled(true);
            }
        }
    }
}
