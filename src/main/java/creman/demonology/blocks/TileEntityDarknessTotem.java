package creman.demonology.blocks;

import creman.demonology.capabilities.Setter;
import creman.demonology.mobs.EntityMessenger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import static creman.demonology.blocks.BlockDarknessTotem.DIRTY;
import static creman.demonology.items.utils.Items.VOODOO_DOLL;

public class TileEntityDarknessTotem extends TileEntity implements ITickable
{
    private int time = -1;
    private float changeOverTime;
    private String playerEvent = "Creman T";

    @Override
    public void update()
    {
        if(this.getTime() > -1)
        {
            EntityPlayer player = world.getPlayerEntityByName(playerEvent);
            if(this.getTime() == 0)
            {
                //Таймер вышел, делаем что-нибудь
                if(player != null)
                {
                    EntityMessenger demon = new EntityMessenger(this.world);
                    demon.setLocationAndAngles(getPos().getX() + 0.5F, getPos().getY() + 3, getPos().getZ() + 0.5F, 0.0F, 0.0F);
                    demon.setTime(5 * 20);
                    demon.setPlayerName(this.playerEvent);
                    this.world.spawnEntity(demon);
                    player.sendMessage(new TextComponentTranslation("demonology.message.demon_spawn"));
                    //ItemStack voodoo = generateVoodooDoll("Creman");
                    //InventoryHelper.spawnItemStack(getWorld(), getPos().getX(), getPos().getY() + 3, getPos().getZ(), voodoo);
                }
            }

            if(player != null)
            {
                Setter.fillFogParameter((EntityPlayerMP) player, 1, changeOverTime);
            }

            decrementTime();
        }
        if(world.isRainingAt(pos.up()) && world.getBlockState(pos).getValue(DIRTY))
        {
            world.setBlockState(pos, world.getBlockState(pos).withProperty(DIRTY, false), 2);
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

    public String getPlayerName()
    {
        return this.playerEvent;
    }

    public void setPlayerName(String playerName)
    {
        this.playerEvent = playerName;
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

        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("name", name);
        nbt.setBoolean("isInverted", false);
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
        tagCompound.setFloat("changeOverTime", this.changeOverTime);
        tagCompound.setString("playerEvent", this.playerEvent);
        return super.writeToNBT(tagCompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        super.readFromNBT(tagCompound);
        this.time = tagCompound.getInteger("time");
        this.changeOverTime = tagCompound.getFloat("changeOverTime");
        this.playerEvent = tagCompound.getString("playerEvent");
    }
}