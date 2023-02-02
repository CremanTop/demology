package creman.demonology.blocks;

import creman.demonology.EventHandler;
import creman.demonology.blocks.utils.BlockTileEntity;
import creman.demonology.blocks.utils.IOrientableBlock;
import creman.demonology.blocks.utils.ITransparentBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.minecraft.block.BlockSponge.WET;
import static net.minecraft.init.Blocks.SPONGE;
import static net.minecraft.init.Items.BUCKET;
import static net.minecraft.init.Items.WATER_BUCKET;
import static net.minecraftforge.items.ItemHandlerHelper.giveItemToPlayer;

public class BlockDarknessTotem extends BlockTileEntity<TileEntityDarknessTotem> implements IOrientableBlock, ITransparentBlock
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyBool DIRTY = PropertyBool.create("dirty");

    public BlockDarknessTotem(String name)
    {
        super(name, Material.WOOD);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(DIRTY, false));
        setHardness(25.0F);
    }

    @Override
    public Class<TileEntityDarknessTotem> getTileEntityClass()
    {
        return TileEntityDarknessTotem.class;
    }

    @Override
    public TileEntityDarknessTotem createTileEntity(World world, IBlockState blockState)
    {
        return new TileEntityDarknessTotem();
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).ordinal() | (state.getValue(DIRTY)? 8 : 0);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta & 7)).withProperty(DIRTY, (meta & 8) > 0);
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, DIRTY);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(DIRTY, false), 2);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote) return true;
        else
        {
            ItemStack itemStack = playerIn.getHeldItem(hand);
            Item item = itemStack.getItem();
            if(state.getValue(DIRTY))
            {
                if(item == WATER_BUCKET)
                {
                    playerIn.setHeldItem(hand, new ItemStack(BUCKET));
                    worldIn.setBlockState(pos, state.withProperty(DIRTY, false), 2);
                }
                else if(item == Item.getItemFromBlock(SPONGE))
                {
                    if(Block.getBlockFromItem(item).getBlockState().getBaseState().getValue(WET))
                    {
                        playerIn.setHeldItem(hand, new ItemStack(SPONGE, itemStack.getCount() - 1, 1));
                        giveItemToPlayer(playerIn, new ItemStack(SPONGE, 1, 0));
                        worldIn.setBlockState(pos, state.withProperty(DIRTY, false), 2);
                    }
                }
            }
            //Entity ent = (Entity) Entities.entities.get(0);
           //ent.setLocationAndAngles(pos.getX(), pos.getY() + 2, pos.getZ(), 0, 0.0F);
           // worldIn.spawnEntity(ent);
            //ItemStack itemStack = playerIn.getHeldItem(hand);
            //itemStack.setCount(itemStack.getMaxStackSize());
//            int dayTime = (int) worldIn.getWorldTime() % 24000;
//            if(dayTime < 13000 || dayTime > 23000)
//            {
                //playerIn.sendMessage(new TextComponentTranslation("demonology.message.totem_day_click"));
                if(EventHandler.PLAYERS_EVENT_TOTEM.containsKey(playerIn))
                {
                    EventHandler.PLAYERS_EVENT_TOTEM.remove(playerIn);
                }
//            }
            // playerIn.sendMessage(new TextComponentTranslation("demonology.message.interaction").appendText(" " + itemStack.getDisplayName()));
            return true;
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        super.breakBlock(worldIn, pos, state);
        for(EntityPlayer player : EventHandler.PLAYERS_EVENT_TOTEM.keySet())
        {
            EventHandler.PLAYERS_EVENT_TOTEM.remove(player);
        }
    }

}