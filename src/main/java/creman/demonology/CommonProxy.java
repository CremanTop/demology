package creman.demonology;

import creman.demonology.blocks.utils.Blocks;
import creman.demonology.blocks.utils.BlocksRegister;
import creman.demonology.capabilities.CapabilityHandler;
import creman.demonology.capabilities.EventHandler;
import creman.demonology.capabilities.ICapabilityDemonology;
import creman.demonology.capabilities.CapabilityDemonology;
import creman.demonology.capabilities.CapStorage;
import creman.demonology.events.EventFogHandler;
import creman.demonology.events.EventRitualHandler;
import creman.demonology.items.utils.Items;
import creman.demonology.items.utils.ItemsRegister;
import creman.demonology.mobs.utils.Entities;
import creman.demonology.mobs.utils.MobsRegister;
import creman.demonology.network.demon.Dispatcher;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod.EventBusSubscriber
public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent e)
    {
        Blocks.addBlocks();
        Items.addItems();
        BlocksRegister.register();
        BlocksRegister.registerRender();
        ItemsRegister.register();
        Entities.preInitEntity();
        MobsRegister.entitiesRegister();
        MinecraftForge.EVENT_BUS.register(new EventFogHandler());
        MinecraftForge.EVENT_BUS.register(new EventRitualHandler());
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        CapabilityManager.INSTANCE.register(ICapabilityDemonology.class, new CapStorage(), CapabilityDemonology.class);
        Dispatcher.register();
    }
    public void server(FMLServerStartingEvent e) {
        //e.registerServerCommand(new FogCommand());
    }
    public void init(FMLInitializationEvent e) {
        Entities.initEntity();
    }
    public void postInit(FMLPostInitializationEvent e) {
    }
}