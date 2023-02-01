package creman.demonology.blocks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityCounter extends TileEntity implements ITickable
{
    private int count;

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound)
    {
        tagCompound.setInteger("count", this.count);
        return super.writeToNBT(tagCompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        this.count = tagCompound.getInteger("count");
        super.readFromNBT(tagCompound);
    }

    public int getCount()
    {
        return this.count;
    }

    public void incrementCount()
    {
        this.count++;
        this.markDirty();
    }

    public void decrementCount()
    {
        this.count--;
        this.markDirty();
    }

    public void update()
    {
        incrementCount();
    }
}