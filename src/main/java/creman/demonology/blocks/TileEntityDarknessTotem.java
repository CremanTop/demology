package creman.demonology.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Set;

import static creman.demonology.EventHandler.PLAYERS_EVENT_TOTEM;
import static creman.demonology.items.utils.Items.VOODOO_DOLL;

public class TileEntityDarknessTotem extends TileEntity implements ITickable
{
    private int time = -1;
    private float changeOverTime;

    @Override
    public void update()
    {
        if(this.getTime() > -1)
        {
            if(this.getTime() == 0)
            {
                //Таймер вышел, делаем что-нибудь
                for(EntityPlayer player : PLAYERS_EVENT_TOTEM.keySet())
                {
                    player.sendMessage(new TextComponentTranslation("demonology.message.demon_spawn"));
                    ItemStack voodoo = generateVoodooDoll("Creman");
                    InventoryHelper.spawnItemStack(getWorld(), getPos().getX(), getPos().getY() + 3, getPos().getZ(), voodoo);
                }
            }

            for(EntityPlayer player : PLAYERS_EVENT_TOTEM.keySet())
            {
                PLAYERS_EVENT_TOTEM.get(player).put("red", PLAYERS_EVENT_TOTEM.get(player).get("red") + changeOverTime);
            }

            decrementTime();
        }
    }

    public int getTime()
    {
        return this.time;
    }

    public void setTime(int time)
    {
        this.time = time;
        this.changeOverTime = (float) (10000 / time) / 10000;
        this.markDirty();
    }

    private void decrementTime()
    {
        this.time--;
        this.markDirty();
    }

    private ItemStack generateVoodooDoll(String name)
    {
        ItemStack voodoo = new ItemStack(VOODOO_DOLL, 1);

        voodoo.setTagCompound(new NBTTagCompound());
        NBTTagCompound nbt = voodoo.getTagCompound();
        nbt.setString("name", name);
        voodoo.setTagCompound(nbt);

        return voodoo;
    }
    
    // NBT Staff
    
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldBlockState, IBlockState newBlockState)
    {
        return oldBlockState.getBlock() != newBlockState.getBlock();
    }
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound)
    {
        tagCompound.setInteger("time", this.time);
        return super.writeToNBT(tagCompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        super.readFromNBT(tagCompound);
        this.time = tagCompound.getInteger("time");
    }
}
