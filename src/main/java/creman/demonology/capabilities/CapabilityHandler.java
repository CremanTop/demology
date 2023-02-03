package creman.demonology.capabilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static creman.demonology.Demonology.MOD_ID;

/**
 * Capability handler
 * This class is responsible for attaching our capabilities
 */
public class CapabilityHandler
{
    public static final ResourceLocation DEMON_CAP = new ResourceLocation(MOD_ID, "demon");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof EntityPlayer)) return;

        event.addCapability(DEMON_CAP, new CapProvider());
    }
}