package creman.demonology.items;

import creman.demonology.items.utils.BaseItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class AncientFragment extends BaseItem
{
    public AncientFragment()
    {
        super("ancient_fragment");
        setMaxStackSize(16);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.GRAY + I18n.format("demonology.information.ancient_fragment"));
    }
}
