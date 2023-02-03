package creman.demonology.events;

import creman.demonology.blocks.TileEntityDarknessTotem;
import creman.demonology.capabilities.CapabilityDemonology;
import creman.demonology.capabilities.ICapabilityDemonology;
import creman.demonology.capabilities.SettingInstaller;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static creman.demonology.blocks.BlockDarknessTotem.DIRTY;

public class EventRitualHandler
{
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
            if(victim instanceof EntityGolem)
            {
                player.sendMessage(new TextComponentTranslation("demonology.message.golem_victim"));
                return;
            }

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

                ICapabilityDemonology capability = CapabilityDemonology.get(player);
                if (!capability.isRitualActive())
                {
                    ((TileEntityDarknessTotem) tileEntity).setTime(10 * 20);
                    ((TileEntityDarknessTotem) tileEntity).setPlayerName(player.getName());

                    player.sendMessage(new TextComponentTranslation("demonology.message.start_ritual"));

                    SettingInstaller.setRitualActive((EntityPlayerMP) player, true);
                    SettingInstaller.setFogParameter((EntityPlayerMP) player,0, 0.9F);
                    for(int i = 1; i <= 3; i++)
                    {
                        SettingInstaller.setFogParameter((EntityPlayerMP) player, i, 0.0F);
                    }
                }
            }
        }
    }
}
