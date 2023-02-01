package creman.demonology.blocks.utils;

import creman.demonology.blocks.BlockCounter;
import creman.demonology.blocks.BlockDarknessTotem;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static creman.demonology.blocks.utils.Blocks.BLOCK_COUNTER;
import static creman.demonology.blocks.utils.Blocks.DARKNESS_TOTEM;

public class BlocksRegister {

    public static void register() {
        for(Block block : Blocks.DemonologyBlocks)
        {
            setRegister(block);
        }
        GameRegistry.registerTileEntity(((BlockCounter) BLOCK_COUNTER).getTileEntityClass(), BLOCK_COUNTER.getRegistryName().toString());
        GameRegistry.registerTileEntity(((BlockDarknessTotem) DARKNESS_TOTEM).getTileEntityClass(), DARKNESS_TOTEM.getRegistryName().toString());
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        for(Block block : Blocks.DemonologyBlocks)
        {
            setRender(block);
        }
    }

    private static void setRegister(Block block) {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    @SideOnly(Side.CLIENT)
    private static void setRender(Block block)
    {
        try
        {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
            // Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
        }
        catch (NullPointerException e)
        {
            System.out.println(block.getRegistryName());
        }
    }
}
