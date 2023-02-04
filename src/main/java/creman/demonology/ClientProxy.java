package creman.demonology;

import creman.demonology.mobs.EntityRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent e)
    {
        super.preInit(e);
        //ClientCommandHandler.instance.registerCommand(new FogCommand());
    }
    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        EntityRegistry.initModels(); ////////////Это типо из тестового
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
}

