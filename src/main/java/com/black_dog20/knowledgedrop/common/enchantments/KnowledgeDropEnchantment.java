package com.black_dog20.knowledgedrop.common.enchantments;

import com.black_dog20.bml.utils.enchantment.EnchantmentUtil;
import com.black_dog20.bml.utils.math.MathUtil;
import com.black_dog20.knowledgedrop.Config;
import com.black_dog20.knowledgedrop.KnowledgeDrop;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

public class KnowledgeDropEnchantment extends Enchantment {

    public KnowledgeDropEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... equipmentSlots) {
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
    public int getMinEnchantability(int enchantmentLevel) {
        return 15 + (enchantmentLevel - 1) * 9;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 50;
    }

    @Mod.EventBusSubscriber(modid = KnowledgeDrop.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class KnowledgeDropEvent {

        private static final Random chance = new Random();

        @SubscribeEvent
        public static void onKill(LivingDropsEvent event) {
            Entity attacker = event.getSource().getTrueSource();
            if (attacker instanceof PlayerEntity) {
                ItemStack weapon = ((PlayerEntity) attacker).getHeldItemMainhand();
                int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KNOWLEDGE_DROP.get(), weapon);
                if (level > 0) {
                    World world = event.getEntityLiving().getEntityWorld();
                    BlockPos pos = event.getEntityLiving().getPosition();
                    if (!world.isRemote) {
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
