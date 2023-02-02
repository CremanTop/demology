package creman.demonology.items;

import creman.demonology.items.utils.BaseItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class VoodooDoll extends BaseItem
{
    public VoodooDoll()
    {
        super("voodoo_doll");
        setMaxStackSize(1);
        setMaxDamage(2);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        EntityPlayer target;
        if (!worldIn.isRemote)
        {
            if (itemstack.hasTagCompound())
            {
                if (itemstack.getTagCompound().hasKey("name"))
                {
                    target = worldIn.getPlayerEntityByName(itemstack.getTagCompound().getString("name"));
                    if (target == null)
                    {
                        playerIn.sendMessage(new TextComponentTranslation("demonology.message.voodoo.not_found_player", itemstack.getTagCompound().getString("name")));
                        return new ActionResult(EnumActionResult.SUCCESS, itemstack);
                    }
                }
                else target = null;
            }
            else target = null;

            if (target != null)
            {
                itemstack.damageItem(1, playerIn);
                if (itemstack.getItemDamage() > 0)
                {
                    target.attackEntityFrom(DamageSource.MAGIC, 9.0F);
                }
                else
                {
                    target.attackEntityFrom(DamageSource.MAGIC, Float.MAX_VALUE);
                }
                //worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ, Sounds.PIPE, SoundCategory.NEUTRAL, 0.5F, 1.0F);
            }
        }
        return new ActionResult(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (!stack.hasTagCompound()) return;
        NBTTagCompound nbt = stack.getTagCompound();
        if(!nbt.hasKey("name"))return;
        tooltip.add(TextFormatting.GRAY + I18n.format("demonology.information.voodoo", nbt.getString("name")));
    }
}