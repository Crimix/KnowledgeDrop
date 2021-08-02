package com.black_dog20.knowledgedrop.common.enchantments;

import com.black_dog20.knowledgedrop.KnowledgeDrop;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEnchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, KnowledgeDrop.MOD_ID);

    public static final RegistryObject<Enchantment> KNOWLEDGE_DROP = ENCHANTMENTS.register("knowledge_drop", () -> new KnowledgeDropEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));
}
