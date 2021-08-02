package com.black_dog20.knowledgedrop.common.util;

import com.black_dog20.knowledgedrop.common.enchantments.ModEnchantments;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class Utils {

    public static ItemStack getKnowLedgeDropBook(int level) {
        ItemStack book = Items.ENCHANTED_BOOK.getDefaultInstance();
        EnchantmentHelper.setEnchantments(ImmutableMap.of(ModEnchantments.KNOWLEDGE_DROP.get(), level), book);
        return book;
    }
}
