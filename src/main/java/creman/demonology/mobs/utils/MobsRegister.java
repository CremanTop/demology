package creman.demonology.mobs.utils;

import creman.demonology.mobs.Messenger;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MobsRegister
{
    @SideOnly(Side.CLIENT)
    public static void entitiesRegister()
    {
        register(Messenger.class, new Messenger.ModelMessenger(), "demonology:textures/entity/messenger.png");
    }

    private static void register(Class <? extends Entity> MobClass, ModelBase MobModel, String texture)
    {
        RenderingRegistry.registerEntityRenderingHandler(MobClass, renderManager -> new RenderLiving(renderManager, MobModel, 0.0F)
        {
            protected ResourceLocation getEntityTexture(Entity entity)
            {
                return new ResourceLocation(texture);
            }
        });
    }
}
