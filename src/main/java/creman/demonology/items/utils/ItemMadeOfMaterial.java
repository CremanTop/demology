package creman.demonology.items.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class ItemMadeOfMaterial extends BaseItem
{
    private final Item.ToolMaterial material;

    public ItemMadeOfMaterial(String name, ToolMaterial material)
    {
        super(name);
        this.material = material;
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        ItemStack mat = this.material.getRepairItemStack();
        if (!mat.isEmpty() && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false)) return true;
        return super.getIsRepairable(toRepair, repair);
    }

    public int getItemEnchantability()
    {
        return this.material.getEnchantability();
    }

    public String getToolMaterialName()
    {
        return this.material.toString();
    }

    public Item.ToolMaterial getToolMaterial()
    {
        return this.material;
    }
}
