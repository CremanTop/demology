package creman.demonology.items.utils;

import net.minecraft.item.Item;

import static creman.demonology.Demonology.MOD_ID;
import static creman.demonology.Demonology.ModTab;

public class BaseItem extends Item
{
    public BaseItem(String name)
    {
        setRegistryName(name);
        setTranslationKey(MOD_ID + "." + name);
        setCreativeTab(ModTab);
    }
}
