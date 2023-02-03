package creman.demonology.capabilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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

    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent e)
    {
        if (e.getEntity() instanceof EntityPlayer)
        {
            if (!e.getEntity().world.isRemote)
            {
                ICapabilityDemonology capability = CapabilityDemonology.get((EntityPlayer) e.getEntity());
                capability.sendToClient((EntityPlayerMP) e.getEntity());
            }
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event)
    {
        EntityPlayer player = event.getEntityPlayer();
        ICapabilityDemonology oldCapa = event.getOriginal().getCapability(CapProvider.DEMON_CAP, null);

        for(int i = 0; i <= 3; i++)
        {
            SettingInstaller.setFogParameter((EntityPlayerMP) player, i, oldCapa.getFogParameter(i));
        }
        SettingInstaller.setRitualActive((EntityPlayerMP) player, oldCapa.isRitualActive());
    }
}