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

public class EventHandler
{
    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent e)
    {
        if(e.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);

            String message = String.format("Hello there, you have §7%d§r mana left.", (int) mana.getMana());
            player.sendMessage(new TextComponentString(message));
        }
    }

    @SubscribeEvent
    public void onPlayerSleep(PlayerSleepInBedEvent event)
    {
        EntityPlayer player = event.getEntityPlayer();

        if (player.world.isRemote) return;

        IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);

        mana.fill(50);

        String message = String.format("You refreshed yourself in the bed. You received 50 mana, you have §7%d§r mana left.", (int) mana.getMana());
        player.sendMessage(new TextComponentString(message));
    }

    @SubscribeEvent
    public void onPlayerFalls(LivingFallEvent event)
    {
        Entity entity = event.getEntity();

        if (entity.world.isRemote || !(entity instanceof EntityPlayerMP) || event.getDistance() < 3) return;

        EntityPlayer player = (EntityPlayer) entity;
        IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);

        float points = mana.getMana();
        float cost = event.getDistance() * 2;

        if (points > cost)
        {
            mana.consume(cost);

            String message = String.format("You absorbed fall damage. It costed §7%d§r mana, you have §7%d§r mana left.", (int) cost, (int) mana.getMana());
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
        IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);
        IMana oldMana = event.getOriginal().getCapability(ManaProvider.MANA_CAP, null);

        mana.set(oldMana.getMana());
    }
}