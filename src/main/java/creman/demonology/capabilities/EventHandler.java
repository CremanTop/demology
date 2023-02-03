package creman.demonology.capabilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

//Старое, только для примера и тестов
public class EventHandler
{
    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent e)
    {
        if(e.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ICapabilityDemonology capability = CapabilityDemonology.get(player);

            String message = String.format("Hello there, you have §7%f§r mana left.", capability.getFogParameter(1));
            player.sendMessage(new TextComponentString(message));
        }
    }

    @SubscribeEvent
    public void onPlayerSleep(PlayerSleepInBedEvent event)
    {
        EntityPlayer player = event.getEntityPlayer();

        if (player.world.isRemote) return;

        ICapabilityDemonology capability = CapabilityDemonology.get(player);

        SettingInstaller.fillFogParameter((EntityPlayerMP) player, 1, 0.2F);

        String message = String.format("You refreshed yourself in the bed. You received 0.2 mana, you have §7%f§r mana left.", capability.getFogParameter(1));
        player.sendMessage(new TextComponentString(message));
    }

    @SubscribeEvent
    public void onPlayerFalls(LivingFallEvent event)
    {
        Entity entity = event.getEntity();

        if (entity.world.isRemote || !(entity instanceof EntityPlayerMP) || event.getDistance() < 3) return;

        EntityPlayer player = (EntityPlayer) entity;
        ICapabilityDemonology capability = CapabilityDemonology.get(player);

        float points = capability.getFogParameter(1);
        float cost = event.getDistance() * 0.1F;

        if (points > cost)
        {
            SettingInstaller.consumeFogParameter((EntityPlayerMP) player,1, cost);

            String message = String.format("You absorbed fall damage. It costed §7%f§r mana, you have §7%f§r mana left.", cost, capability.getFogParameter(1));
            player.sendMessage(new TextComponentString(message));

            event.setCanceled(true);
        }
    }

    /**
     * Copy data from dead player to the new player
     */
    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event)
    {
        EntityPlayer player = event.getEntityPlayer();
        ICapabilityDemonology oldMana = event.getOriginal().getCapability(CapProvider.DEMON_CAP, null);

        SettingInstaller.setFogParameter((EntityPlayerMP) player,1, oldMana.getFogParameter(1));
    }
}