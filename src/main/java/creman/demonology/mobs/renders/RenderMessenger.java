package creman.demonology.mobs.renders;

import creman.demonology.mobs.EntityMessenger;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

import static creman.demonology.Demonology.MOD_ID;

public class RenderMessenger extends RenderLiving<EntityMessenger>
{
    private final ResourceLocation mobTexture = new ResourceLocation(MOD_ID + ":textures/entity/messenger.png");

    /*
    Конструктор рендера,
    теперь о super:
        1 параметр - наш RenderManager,
        2 параметр - наша модель,
        3 параметр - размер тени(стандартно 0.5F)
    */
    public RenderMessenger(RenderManager manager)
    {
        super(manager, new EntityMessenger.ModelMessenger(), 0.0F);
    }

    public static RenderMessenger.Factory FACTORY = new RenderMessenger.Factory();

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityMessenger entity)
    {
        return mobTexture;
    }

    @Override
    public boolean shouldRender(EntityMessenger livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return true;
    }

    public static class Factory implements IRenderFactory<EntityMessenger>
    {
        @Override
        public Render<? super EntityMessenger> createRenderFor(RenderManager manager)
        {
            return new RenderMessenger(manager);
        }
    }
}
