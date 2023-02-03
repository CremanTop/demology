package creman.demonology;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import static creman.demonology.Demonology.MOD_ID;

public class Materials
{
    public static final Item.ToolMaterial CLOTH_MATERIAL = EnumHelper.addToolMaterial(MOD_ID + ":" + "cloth", 1, 10, 1.0F, 1.0F, 12).setRepairItem(new ItemStack(Items.STRING));
}
