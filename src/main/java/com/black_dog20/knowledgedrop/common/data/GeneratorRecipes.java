package com.black_dog20.knowledgedrop.common.data;

import com.black_dog20.bml.datagen.BaseRecipeProvider;
import com.black_dog20.bml.datagen.crafting.ShapedNBTRecipeBuilder;
import com.black_dog20.knowledgedrop.KnowledgeDrop;
import com.black_dog20.knowledgedrop.common.enchantments.ModEnchantments;
import com.google.common.collect.ImmutableMap;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class GeneratorRecipes extends BaseRecipeProvider {

    public GeneratorRecipes(DataGenerator generator) {
        super(generator, KnowledgeDrop.MOD_ID);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ItemStack book = Items.ENCHANTED_BOOK.getDefaultInstance();
        EnchantmentHelper.setEnchantments(ImmutableMap.of(ModEnchantments.KNOWLEDGE_DROP.get(), 1), book);
        ShapedNBTRecipeBuilder.shapedNBTRecipe(book)
                .key('b', Items.BOOK)
                .key('e', Items.ENCHANTED_BOOK)
                .key('s', Items.DIAMOND_SWORD)
                .key('g', Tags.Items.GEMS_EMERALD)
                .key('d', Tags.Items.GEMS_DIAMOND)
                .patternLine("geg")
                .patternLine("ebe")
                .patternLine("dsd")
                .addCriterion("has_emeralds", hasItem(Tags.Items.GEMS_EMERALD))
                .addCriterion("has_diamonds", hasItem(Tags.Items.GEMS_DIAMOND))
                .setItemGroup(KnowledgeDrop.MOD_ID)
                .build(consumer, RL("knowledge_drop_enchanted_book"));
    }
}
