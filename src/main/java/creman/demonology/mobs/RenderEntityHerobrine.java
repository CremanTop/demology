package creman.demonology.mobs;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

import static creman.demonology.Demonology.MOD_ID;

public class RenderEntityHerobrine extends RenderLiving<EntitySmallHerobrine>
{
    /*Расположение текстуры моба(создаём новую папку в текстурах, имя её entity, туда и кладём наши текстуры)*/
    private final ResourceLocation mobTexture = new ResourceLocation(MOD_ID + ":textures/entity/herobrin.png");

    /*
    Конструктор рендера,
    теперь о super:
        1 параметр - наш RenderManager,
        2 параметр - наша модель,
        3 параметр - размер тени(стандартно 0.5F)
    */
    public RenderEntityHerobrine(RenderManager manager)
    {
        super(manager, new ModelBiped(), 0.5F);
    }

    public static Factory FACTORY = new Factory();

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntitySmallHerobrine entity)
    {
        /*Возвращаем текстуру моба*/
        return mobTexture;
    }

    @Override
    public boolean shouldRender(EntitySmallHerobrine livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return true;
    }

    /*--------->НАШ РЕНДЕР ФЭКТОРИ <---------*/
    public static class Factory implements IRenderFactory<EntitySmallHerobrine>
    {
        @Override
        public Render<? super EntitySmallHerobrine> createRenderFor(RenderManager manager)
        {
            /*И наконец-то из всего этого создаём рендер*/
            return new RenderEntityHerobrine(manager);
        }
    }
}
