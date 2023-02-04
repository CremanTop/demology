package creman.demonology.mobs;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

import static creman.demonology.Demonology.MOD_ID;

public class EntitySmallHerobrine extends EntityMob
{
    //Наш доп. урон(ниже о нём)
    public static int ADD_DAMAGE = 15;

    /*Конструктор*/
    public EntitySmallHerobrine(World world)
    {
        super(world);
        setSize(0.6F, 1.98F);//Размер моба
    }

    /*Конструктор с установкой позиции*/
    public EntitySmallHerobrine(World world, double x, double y, double z)
    {
        super(world);
        setSize(0.6F, 1.98F);//Размер моба
        setPositionAndUpdate(x, y, z);
    }

    @Override
    protected void applyEntityAttributes()
    {
        /*Строчка ниже нужна для регистрации атрибутов(Макс. ХП, скорость, сила атаки, броня, скорость полёта и тд.)*/
        super.applyEntityAttributes();
        //Устанавливаем атрибуты
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);//Радиус слежки = 35 блоков
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);//Скорость моба(для примера: скорось Зомби = 0.23D)
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);//Урон существа = 1.5 сердечка(у сердечко = 2D)
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);//Броня моба(от неё зависит урон по мобу)
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(2.0D);//Отбрасивание моба(больше число меньше отбрасование)
    }

    @Override
    protected void initEntityAI()
    {
        /*Делаем интелект мобу
         * 1 параметр - приоритет
         * 2 параметр - дочерный класс от EntityAIBase
         */
        this.tasks.addTask(0, new EntityAISwimming(this));//Плавает ли моб
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, false));//Атака ближнего боя
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));//Моб путешествует
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));//Смотрит на EntityPlayer(игрок)
        this.tasks.addTask(4, new EntityAILookIdle(this));//Грубо говоря - ленивое поварачивание головы
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityZombie.class, EntityPlayer.class}));//Охота за такими мобами: EntityZombie, EntityPlayer
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        /*Что происходит при атаке*/
        if (super.attackEntityAsMob(entityIn))
        {//Проверка на атаку
            if (entityIn instanceof EntityLivingBase)
            {//Если это моб
                ((EntityLivingBase) entityIn).attackEntityAsMob(this);//Делаем последним ударившим нашего моба
                entityIn.attackEntityFrom(((EntityLivingBase) entityIn).getLastDamageSource(), rand.nextInt(ADD_DAMAGE));//Наносим доп. урон
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        /*Сколько спавнится в чанке*/
        return 25;
    }
}

