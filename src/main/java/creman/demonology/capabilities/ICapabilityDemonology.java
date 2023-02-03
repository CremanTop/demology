package creman.demonology.capabilities;

import net.minecraft.entity.player.EntityPlayerMP;

public interface ICapabilityDemonology
{
    public void setFogParameter(int parameter, float value);
    public float getFogParameter(int parameter);
    public void fillFogParameter(int parameter, float value);
    public void consumeFogParameter(int parameter, float value);

    public boolean isRitualActive();
    public void setRitualActive(boolean ritualActive);
    public void sendToClient(EntityPlayerMP playerMP);
}
