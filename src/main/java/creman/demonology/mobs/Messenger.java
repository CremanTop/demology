package creman.demonology.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import static net.minecraft.init.Items.WATER_BUCKET;

public class Messenger extends EntityFlying
{
    public Messenger(World worldIn)
    {
        super(worldIn);
        this.setSize(1.0F, 1.0F);
        this.setEntityInvulnerable(true);
    }

    @Override
    protected void initEntityAI()
    {
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
    }

    public SoundCategory getSoundCategory()
    {
        return SoundCategory.HOSTILE;
    }

    //ДОБАВИТЬ ЗВУКИ
//    protected SoundEvent getAmbientSound()
//    {
//        return SoundEvents.ENTITY_GHAST_AMBIENT;
//    }
//
//    @Nullable
//    protected ResourceLocation getLootTable()
//    {
//        return LootTableList.ENTITIES_GHAST;
//    }

    protected float getSoundVolume()
    {
        return 10.0F;
    }

    public float getEyeHeight()
    {
        return 0.8F;
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemStack = player.getHeldItem(hand);
        if(itemStack.getItem() == WATER_BUCKET)
        {
            player.sendMessage(new TextComponentString("Да, это ведро"));
            return true;
        }
        return false;
    }

    public void onUpdate()
    {
        this.setHealth(this.getMaxHealth());
    }

    public static class ModelMessenger extends ModelBase
    {
        private final ModelRenderer bone;

        public ModelMessenger() {
            this.textureWidth = 64;
            this.textureHeight = 64;
            this.bone = new ModelRenderer(this);
            this.bone.setRotationPoint(0.0F, 24.0F, 0.0F);
            this.bone.cubeList.add(new ModelBox(this.bone, 0, 0, -5.0F, -14.0F, -5.0F, 10, 14, 10, 0.0F, false));
        }

        public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
            this.bone.render(f5);
        }

        public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
            modelRenderer.rotateAngleX = x;
            modelRenderer.rotateAngleY = y;
            modelRenderer.rotateAngleZ = z;
        }

        public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
            super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        }
    }
}
