package com.black_dog20.knowledgedrop.common.integrations.jei;

import com.black_dog20.knowledgedrop.KnowledgeDrop;
import com.black_dog20.knowledgedrop.common.enchantments.ModEnchantments;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(Items.ENCHANTED_BOOK, bookInterpreter);
    }

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(KnowledgeDrop.MOD_ID);
    }

    private ISubtypeInterpreter bookInterpreter = itemStack -> {
        List<Enchantment> enchantments = new ArrayList<>(EnchantmentHelper.getEnchantments(itemStack).keySet());

        if (enchantments.size() == 1) {
            Enchantment enchantment = enchantments.get(0);
            if (ModEnchantments.KNOWLEDGE_DROP.get() == enchantment) {
                return enchantment.getRegistryName().toString();
            }
        }

        return ISubtypeInterpreter.NONE;
    };

}
