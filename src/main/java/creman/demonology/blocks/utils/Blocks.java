package creman.demonology.blocks.utils;

import creman.demonology.blocks.BlockCounter;
import creman.demonology.blocks.BlockDarknessTotem;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import java.util.ArrayList;

public abstract class Blocks
{
    public static Block DARKNESS_TOTEM = new BlockDarknessTotem("darkness_totem", null);
    public static Block BLOCK_COUNTER = new BlockCounter("block_counter", Material.ROCK, 10.0F, 10.0F, SoundType.STONE).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static ArrayList<Block> DemonologyBlocks = new ArrayList<>();

    public static void addBlocks() {
        DemonologyBlocks.add(DARKNESS_TOTEM);
        DemonologyBlocks.add(BLOCK_COUNTER);
    }
}
