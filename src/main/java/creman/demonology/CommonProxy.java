package creman.demonology;

import creman.demonology.blocks.utils.Blocks;
import creman.demonology.blocks.utils.BlocksRegister;
import creman.demonology.items.utils.Items;
import creman.demonology.items.utils.ItemsRegister;
import creman.demonology.mobs.utils.Entities;
import creman.demonology.mobs.utils.MobsRegister;
import net.minecraftforge.common.MinecraftForge;
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
        Entities.initEntity();
        MobsRegister.entitiesRegister();
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }
    public void server(FMLServerStartingEvent e) {
        //e.registerServerCommand(new FogCommand());
    }
    public void init(FMLInitializationEvent e) {
    }
    public void postInit(FMLPostInitializationEvent e) {
    }
}


