package com.black_dog20.knowledgedrop.common.data;

import com.black_dog20.knowledgedrop.KnowledgeDrop;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KnowledgeDrop.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        if (event.includeServer())
            registerServerProviders(event.getGenerator());

        if (event.includeClient())
            registerClientProviders(event.getGenerator());
    }

    private static void registerServerProviders(DataGenerator generator) {
        generator.addProvider(true, new GeneratorRecipes(generator.getPackOutput()));
    }

    private static void registerClientProviders(DataGenerator generator) {
        generator.addProvider(true, new GeneratorLanguage(generator));
    }


}