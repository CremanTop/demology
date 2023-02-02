package creman.demonology.blocks.utils;

import creman.demonology.blocks.BlockDarknessTotem;
import net.minecraft.block.Block;

import java.util.ArrayList;

public abstract class Blocks
{
    public static Block DARKNESS_TOTEM = new BlockDarknessTotem("darkness_totem");
    public static ArrayList<Block> DemonologyBlocks = new ArrayList<>();

    public static void addBlocks() {
        DemonologyBlocks.add(DARKNESS_TOTEM);
    }
}
