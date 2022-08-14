package com.black_dog20.knowledgedrop.common.enchantments;

import com.black_dog20.bml.utils.enchantment.EnchantmentUtil;
import com.black_dog20.bml.utils.math.MathUtil;
import com.black_dog20.knowledgedrop.Config;
import com.black_dog20.knowledgedrop.KnowledgeDrop;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

public class KnowledgeDropEnchantment extends Enchantment {

    public KnowledgeDropEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot... equipmentSlots) {
        super(rarity, type, equipmentSlots);
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return 15 + (enchantmentLevel - 1) * 9;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return super.getMinCost(enchantmentLevel) + 50;
    }

    @Mod.EventBusSubscriber(modid = KnowledgeDrop.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class KnowledgeDropEvent {

        private static final Random chance = new Random();

        @SubscribeEvent
        public static void onKill(LivingDropsEvent event) {
            Entity attacker = event.getSource().getEntity();
            if (attacker instanceof Player) {
                ItemStack weapon = ((Player) attacker).getMainHandItem();
                int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.KNOWLEDGE_DROP.get(), weapon);
                if (level > 0) {
                    Level world = event.getEntity().getCommandSenderWorld();
                    BlockPos pos = event.getEntity().blockPosition();
                    if (!world.isClientSide) {
                        double levelChance = MathUtil.clamp(Config.BASE_PERCENTAGE.get(), 0.01, 0.10) * level;
                        if (chance.nextDouble() <= levelChance) {
                            event.getDrops().add(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), EnchantmentUtil.addRandomEnchantment(new ItemStack(Items.BOOK), true, EnchantmentUtil.Level.RANDOM)));
                        }
                    }
                }
            }
        }
    }

}
