package creman.demonology.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;

public class EventFogHandler
{
    public static HashMap<EntityPlayer, HashMap<String, Float>> PLAYERS_EVENT_TOTEM = new HashMap<>();

    @SubscribeEvent
    public void fogColor(EntityViewRenderEvent.FogColors e)
    {
        if (PLAYERS_EVENT_TOTEM.containsKey(e.getEntity()) && e.getEntity() instanceof EntityPlayer)
        {
            e.setRed(PLAYERS_EVENT_TOTEM.get(e.getEntity()).get("red"));
            e.setGreen(0);
            e.setBlue(0);
        }
    }

    @SubscribeEvent
    public void fogDensity(EntityViewRenderEvent.FogDensity e)
    {
        if (PLAYERS_EVENT_TOTEM.containsKey(e.getEntity()) && e.getEntity() instanceof EntityPlayer)
        {
            e.setDensity(PLAYERS_EVENT_TOTEM.get(e.getEntity()).get("density"));
            e.setCanceled(true);
        }
    }
}
