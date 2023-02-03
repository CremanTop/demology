package creman.demonology.blocks.utils;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import static creman.demonology.Demonology.MOD_ID;
import static creman.demonology.Demonology.ModTab;

public class BaseBlock extends Block
{

    public BaseBlock(String name, Material material, float hardness, float resistanse, SoundType soundType)
    {
        super(material);

        this.setRegistryName(MOD_ID, name);
        this.setTranslationKey(MOD_ID + "." + name);
        this.setCreativeTab(ModTab);
        this.setHardness(hardness);
        this.setResistance(resistanse);
        this.setSoundType(soundType);
    }

    public BaseBlock(String name, Material material)
    {
        super(material);

        this.setRegistryName(MOD_ID, name);
        this.setTranslationKey(MOD_ID + "." + name);
        this.setCreativeTab(ModTab);
    }
}