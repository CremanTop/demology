package creman.demonology.items.utils;

import creman.demonology.items.AncientFragment;
import creman.demonology.items.VoodooDoll;
import net.minecraft.item.Item;

import java.util.ArrayList;

public abstract class Items
{
    public static Item VOODOO_DOLL = new VoodooDoll();
    public static Item ANCIENT_FRAGMENT = new AncientFragment();

    public static ArrayList<Item> DemonologyItems = new ArrayList<>();

    public static void addItems() {
        DemonologyItems.add(VOODOO_DOLL);
        DemonologyItems.add(ANCIENT_FRAGMENT);
    }
}
