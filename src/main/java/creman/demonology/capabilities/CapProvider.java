package creman.demonology.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * Mana provider
 *
 * This class is responsible for providing a capability. Other modders may
 * attach their own provider with implementation that returns another
 * implementation of ICapabilityDemonology to the target's (Entity, TE, ItemStack, etc.) disposal.
 */
public class CapProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(ICapabilityDemonology.class)
    public static final Capability<ICapabilityDemonology> DEMON_CAP = null;

    private ICapabilityDemonology instance = DEMON_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == DEMON_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == DEMON_CAP ? DEMON_CAP.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return DEMON_CAP.getStorage().writeNBT(DEMON_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        DEMON_CAP.getStorage().readNBT(DEMON_CAP, this.instance, null, nbt);
    }
}
