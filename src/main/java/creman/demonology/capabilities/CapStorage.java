package creman.demonology.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * This class is responsible for saving and reading mana data from or to server
 */
public class CapStorage implements Capability.IStorage<ICapabilityDemonology>
{
    @Override
    public NBTBase writeNBT(Capability<ICapabilityDemonology> capability, ICapabilityDemonology instance, EnumFacing side)
    {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setFloat("density", instance.getFogParameter(0));
        tag.setFloat("red", instance.getFogParameter(1));
        tag.setFloat("green", instance.getFogParameter(2));
        tag.setFloat("blue", instance.getFogParameter(3));
        tag.setBoolean("ritualActive", instance.isRitualActive());

        return tag;
    }

    @Override
    public void readNBT(Capability<ICapabilityDemonology> capability, ICapabilityDemonology instance, EnumFacing side, NBTBase nbt)
    {
        NBTTagCompound compound = (NBTTagCompound) nbt;

        instance.setFogParameter(0, compound.getFloat("density"));
        instance.setFogParameter(1, compound.getFloat("red"));
        instance.setFogParameter(2, compound.getFloat("green"));
        instance.setFogParameter(3, compound.getFloat("blue"));
        instance.setRitualActive(compound.getBoolean("ritualActive"));
    }
}
