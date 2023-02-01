package creman.demonology.mobs.utils;

import creman.demonology.mobs.Messenger;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

import java.util.ArrayList;
import java.util.function.Supplier;

public abstract class Entities
{
    public static final ArrayList<Supplier<EntityEntry>> entities = new ArrayList<>();

    public static void initEntity() {
        entities.add(() -> EntityEntryBuilder.create().entity(Messenger.class).id(new ResourceLocation("demonology", "messenger"), 1).name("messenger").tracker(64, 3, false).egg(5457209, 8811878).build());
    }
}
