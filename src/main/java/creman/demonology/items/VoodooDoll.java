package creman.demonology.items;

import creman.demonology.items.utils.ItemMadeOfMaterial;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

import static creman.demonology.Materials.CLOTH_MATERIAL;

public class VoodooDoll extends ItemMadeOfMaterial
{
    public VoodooDoll()
    {
        super("voodoo_doll", CLOTH_MATERIAL);
        setMaxStackSize(1);
        setMaxDamage(getToolMaterial().getMaxUses());
        setHasSubtypes(true);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        EntityPlayer target;
        itemstack.getTagCompound().setString("name", playerIn.getName());
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
                if(!itemstack.getTagCompound().getBoolean("isInverted"))
                {
                    if (itemstack.getItemDamage() > 0)
                    {
                        target.attackEntityFrom(DamageSource.MAGIC, 4.0F);
                    }
                    else
                    {
                        target.attackEntityFrom(DamageSource.MAGIC, Float.MAX_VALUE);
                    }
                }
                else
                {
                    if (itemstack.getItemDamage() > 0)
                    {
                        target.setHealth(target.getHealth() + 6.0F);
                    }
                    else
                    {
                        target.setHealth(target.getMaxHealth());
                    }
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

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return super.hasEffect(stack) || stack.getTagCompound().getBoolean("isInverted");
        //return false;
    }

    public EnumRarity getRarity(ItemStack stack)
    {
        if(stack.hasTagCompound())
        {
            if(stack.getTagCompound().hasKey("isInverted"))
            {
                return stack.getTagCompound().getBoolean("isInverted") ? EnumRarity.EPIC : EnumRarity.RARE;
            }
            else return EnumRarity.RARE;
        }
        else return EnumRarity.RARE;
    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            ItemStack base = new ItemStack(this), epic = new ItemStack(this);
            NBTTagCompound tagTrue = new NBTTagCompound(), tagFalse = new NBTTagCompound();
            tagTrue.setBoolean("isInverted", true);
            tagFalse.setBoolean("isInverted", false);

            base.setTagCompound(tagFalse);
            epic.setTagCompound(tagTrue);

            items.add(base);
            items.add(epic);
        }
    }
}