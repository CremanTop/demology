package creman.demonology.mobs.utils;

import creman.demonology.mobs.Messenger;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Supplier;

import static creman.demonology.Demonology.MOD_ID;

public abstract class Entities
{
    public static final ArrayList<Supplier<EntityEntry>> entities = new ArrayList<>();

    public static void preInitEntity() {
        entities.add(() -> EntityEntryBuilder.create().entity(Messenger.class).id(new ResourceLocation(MOD_ID, "messenger"), 1).name("messenger").tracker(64, 3, false).egg(5457209, 8811878).build());
    }

    public static void initEntity(){
        Biome[] spawnBiomes = allBiomes(Biome.REGISTRY);
        EntityRegistry.addSpawn(Messenger.class, 20, 3, 30, EnumCreatureType.MONSTER, spawnBiomes);
    }

    private static Biome[] allBiomes(RegistryNamespaced<ResourceLocation, Biome> in) {
        Iterator<Biome> itr = in.iterator();
        ArrayList<Biome> ls = new ArrayList<>();
        while (itr.hasNext())
            ls.add(itr.next());
        return ls.<Biome>toArray(new Biome[ls.size()]);
    }
}
