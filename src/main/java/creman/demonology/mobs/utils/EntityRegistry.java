package creman.demonology.mobs.utils;

import creman.demonology.mobs.EntityMessenger;
import creman.demonology.mobs.EntitySmallHerobrine;
import creman.demonology.mobs.renders.RenderEntityHerobrine;
import creman.demonology.mobs.renders.RenderMessenger;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static creman.demonology.Demonology.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class EntityRegistry {

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntitySmallHerobrine.class, RenderEntityHerobrine.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityMessenger.class, RenderMessenger.FACTORY);
    }

    private static int ID = 0;//Для айди

    public static EntityEntry SMALL_HEROBRINE = EntityEntryBuilder
            .create()//Создаём новый EntityEntry
            .entity(EntitySmallHerobrine.class)//Какой моб в EntityEntry
            .name("Small Herobrine")//Имя
            .id("small_herobrine", ID++)//Айди и имя регистрации
            .egg(0xff4040, 0xd891ef)//Цвет яйца, первое значени фон, второе "точки"
            .tracker(160, 2, false)//Трекер моба
            .build();//Устанавливаем параметры
    public static EntityEntry MESSENGER = EntityEntryBuilder
            .create()//Создаём новый EntityEntry
            .entity(EntityMessenger.class)//Какой моб в EntityEntry
            .name("Messenger")//Имя
            .id("messenger", ID++)//Айди и имя регистрации
            .egg(0xff4040, 0xd891ef)//Цвет яйца, первое значени фон, второе "точки"
            .tracker(160, 2, false)//Трекер моба
            .build();//Устанавливаем параметры

    @SubscribeEvent
    public static void registryEntity(RegistryEvent.Register<EntityEntry> event) {
        event.getRegistry().registerAll(
                SMALL_HEROBRINE,
                MESSENGER
        );
    }
}
