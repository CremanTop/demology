package creman.demonology.network.demon.common;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketDemonology implements IMessage
{
    public float fogRed = 0.0f;
    public float fogGreen = 0.0f;
    public float fogBlue = 0.0f;
    public float fogDensity = 0.0f;
    public boolean isRitualActive = false;

    public PacketDemonology()
    {
        super();
    }

    public PacketDemonology(float red, float green, float blue, float density, boolean isRitualActive)
    {
        this.fogRed = red;
        this.fogGreen = green;
        this.fogBlue = blue;
        this.fogDensity = density;
        this.isRitualActive = isRitualActive;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.fogRed = buf.readFloat();
        this.fogGreen = buf.readFloat();
        this.fogBlue = buf.readFloat();
        this.fogDensity = buf.readFloat();
        this.isRitualActive = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeFloat(fogRed);
        buf.writeFloat(fogGreen);
        buf.writeFloat(fogBlue);
        buf.writeFloat(fogDensity);
        buf.writeBoolean(isRitualActive);
    }
}
