package creman.demonology;

import creman.demonology.blocks.TileEntityDarknessTotem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;

import static creman.demonology.blocks.BlockDarknessTotem.DIRTY;

public class EventHandler
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

    @SubscribeEvent
    public void entityDeath(LivingDeathEvent e)
    {
        Entity victim = e.getEntity();
        World world = victim.getEntityWorld();
        BlockPos pos = new BlockPos(victim.posX, victim.posY - 1, victim.posZ);
        TileEntity tileEntity = world.getTileEntity(pos);
        EntityPlayer player = null;
        Vec3d playerLocation = e.getSource().getDamageLocation();
        if(playerLocation != null)
        {
            player = world.getClosestPlayer(playerLocation.x, playerLocation.y, playerLocation.z, 1, false);
        }

        if (tileEntity instanceof TileEntityDarknessTotem && player != null)
        {
            if(world.getBlockState(pos).getValue(DIRTY))
            {
                player.sendMessage(new TextComponentTranslation("demonology.message.dirty_totem_activated"));
                return;
            }
            world.setBlockState(pos, world.getBlockState(pos).withProperty(DIRTY, true), 2);

            int dayTime = (int) world.getWorldTime() % 24000;
            if(dayTime < 13000 || dayTime > 23000)
            {
                player.sendMessage(new TextComponentTranslation("demonology.message.totem_day_click"));
            }
            else
            {
                if(victim instanceof EntitySheep)
                {
                    if(((EntitySheep) victim).getFleeceColor() == EnumDyeColor.BLACK)
                    {
                        player.sendMessage(new TextComponentTranslation("demonology.message.good_victim"));
                    }
                    else
                    {
                        player.sendMessage(new TextComponentTranslation("demonology.message.bad_victim"));
                    }
                }
                else if(victim instanceof EntityVillager)
                {
                    player.sendMessage(new TextComponentTranslation("demonology.message.normal_victim"));
                }
                else if(victim instanceof EntityOcelot || victim instanceof EntityPlayer)
                {
                    world.addWeatherEffect(new EntityLightningBolt(world, player.posX, player.posY, player.posZ, false));
                    return;
                }
                else
                {
                    player.sendMessage(new TextComponentTranslation("demonology.message.bad_victim"));
                }

                if (!EventHandler.PLAYERS_EVENT_TOTEM.containsKey(player))
                {
                    ((TileEntityDarknessTotem) tileEntity).setTime(10 * 20);

                    player.sendMessage(new TextComponentTranslation("demonology.message.start_ritual"));

                    HashMap<String, Float> map = new HashMap<>();
                    map.put("red", 0F);
                    map.put("density", 0.9F);
                    EventHandler.PLAYERS_EVENT_TOTEM.put(player, map);
                }
            }
        }
    }
}
