package com.black_dog20.knowledgedrop.common.util;

import com.black_dog20.knowledgedrop.common.enchantments.ModEnchantments;
import com.google.common.collect.ImmutableMap;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class Utils {

    public static ItemStack getKnowLedgeDropBook(int level) {
        ItemStack book = Items.ENCHANTED_BOOK.getDefaultInstance();
        EnchantmentHelper.setEnchantments(ImmutableMap.of(ModEnchantments.KNOWLEDGE_DROP.get(), level), book);
        return book;
    }
}
