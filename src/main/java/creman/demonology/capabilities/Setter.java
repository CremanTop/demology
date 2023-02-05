package creman.demonology.capabilities;

import net.minecraft.entity.player.EntityPlayerMP;

//Рекомендуется использовать это
public class Setter
{
    public static void setFogParameter(EntityPlayerMP player, int parameter, float value)
    {
        ICapabilityDemonology capability = CapabilityDemonology.get(player);
        capability.setFogParameter(parameter, value);
        sending(player, capability);
    }

    public static void fillFogParameter(EntityPlayerMP player, int parameter, float value)
    {
        ICapabilityDemonology capability = CapabilityDemonology.get(player);
        capability.fillFogParameter(parameter, value);
        sending(player, capability);
    }

    public static void consumeFogParameter(EntityPlayerMP player, int parameter, float value)
    {
        ICapabilityDemonology capability = CapabilityDemonology.get(player);
        capability.consumeFogParameter(parameter, value);
        sending(player, capability);
    }

    public static void setRitualActive(EntityPlayerMP player, boolean ritualActive)
    {
        ICapabilityDemonology capability = CapabilityDemonology.get(player);
        capability.setRitualActive(ritualActive);
        sending(player, capability);
    }

    private static void sending(EntityPlayerMP player, ICapabilityDemonology capability)
    {
        if (!player.world.isRemote)
        {
            capability.sendToClient(player);
        }
    }
}
