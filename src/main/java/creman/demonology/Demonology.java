package creman.demonology;

import creman.demonology.blocks.utils.Blocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(
        modid = Demonology.MOD_ID,
        name = Demonology.MOD_NAME,
        version = Demonology.VERSION
)
public class Demonology
{

    public static final String MOD_ID = "demonology";
    public static final String MOD_NAME = "Demonology";
    public static final String VERSION = "1.0";

    @Mod.Instance(MOD_ID)
    public static Demonology INSTANCE;

    @SidedProxy(clientSide = "creman.demonology.ClientProxy", serverSide = "creman.demonology.CommonProxy")
    public static CommonProxy proxy;

//    File currentClass = new File(URLDecoder.decode(ClientProxy.class
//            .getProtectionDomain()
//            .getCodeSource()
//            .getLocation()
//            .getPath(), "UTF-8"));
//    String classDirectory = currentClass.getParent();


    public static final CreativeTabs ModTab = new CreativeTabs("Demonology")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack createIcon(){
            return new ItemStack(Item.getItemFromBlock(Blocks.DARKNESS_TOTEM));
        }
    };

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
        //System.out.println(classDirectory);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }

    /* EXAMPLE ITEM AND BLOCK - you probably want these in separate files
    public static class MySpecialItem extends Item {

    }

    public static class MySpecialBlock extends Block {

    }
    */
}
