//package creman.demonology.mobs.renders;
//
//import creman.demonology.mobs.EntitySmallHerobrine;
//import net.minecraft.client.model.ModelBase;
//import net.minecraft.client.model.ModelBiped;
//import net.minecraft.client.renderer.culling.ICamera;
//import net.minecraft.client.renderer.entity.Render;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.entity.EntityLiving;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.client.registry.IRenderFactory;
//
//import static creman.demonology.Demonology.MOD_ID;
//
//public class RenderEntity<T extends EntityLiving> extends RenderLiving<T>
//{
//    private final ResourceLocation mobTexture;
//
//    public RenderEntity(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn, String skin)
//    {
//        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
//        this.mobTexture = new ResourceLocation(MOD_ID + ":textures/entity/" + skin + ".png");
//    }
//
//    @Override
//    protected ResourceLocation getEntityTexture(EntityLiving entity)
//    {
//        return this.mobTexture;
//    }
//
//    @Override
//    public boolean shouldRender(T livingEntity, ICamera camera, double camX, double camY, double camZ)
//    {
//        return true;
//    }
//
//    public Factory getFactory()
//    {
//        return new Factory();
//    }
//
//    public class Factory implements IRenderFactory<T>
//    {
//        @Override
//        public Render<? super T> createRenderFor(RenderManager manager)
//        {
//            ModelBase model;
//            float shadowSize;
//            String skin;
//            if(manager.pointedEntity instanceof EntitySmallHerobrine)
//            {
//                model = new ModelBiped();
//                shadowSize = 0.0F;
//                skin = "herobrin";
//            }
//            else
//            {
//                model = new ModelBiped();
//                shadowSize = 0.0F;
//                skin = null;
//            }
//            return new RenderEntity<T>(manager, model, shadowSize, skin);
//        }
//    }
//}
