package creman.demonology.items.utils;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemsRegister
{
    public static void register()
    {
        for(Item item : Items.DemonologyItems)
        {
            ForgeRegistries.ITEMS.register(item);
            initModel(item);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void initModel(Item item)
    {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
