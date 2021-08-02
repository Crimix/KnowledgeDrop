package com.black_dog20.knowledgedrop.common.data;

import com.black_dog20.bml.datagen.BaseRecipeProvider;
import com.black_dog20.bml.datagen.crafting.ShapedNBTRecipeBuilder;
import com.black_dog20.knowledgedrop.KnowledgeDrop;
import com.black_dog20.knowledgedrop.common.util.Utils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class GeneratorRecipes extends BaseRecipeProvider {

    public GeneratorRecipes(DataGenerator generator) {
        super(generator, KnowledgeDrop.MOD_ID);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedNBTRecipeBuilder.shapedNBTRecipe(Utils.getKnowLedgeDropBook(1))
                .define('b', Items.BOOK)
                .define('e', Items.ENCHANTED_BOOK)
                .define('s', Items.DIAMOND_SWORD)
                .define('g', Tags.Items.GEMS_EMERALD)
                .define('d', Tags.Items.GEMS_DIAMOND)
                .pattern("geg")
                .pattern("ebe")
                .pattern("dsd")
                .unlockedBy("has_emeralds", has(Tags.Items.GEMS_EMERALD))
                .unlockedBy("has_diamonds", has(Tags.Items.GEMS_DIAMOND))
                .setItemGroup(KnowledgeDrop.MOD_ID)
                .save(consumer, RL("knowledge_drop_enchanted_book"));
    }
}
