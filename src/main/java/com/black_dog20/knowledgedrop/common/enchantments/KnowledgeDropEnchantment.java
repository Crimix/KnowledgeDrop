package com.black_dog20.knowledgedrop.common.enchantments;

import com.black_dog20.bml.utils.math.MathUtil;
import com.black_dog20.bml.utils.stream.StreamUtils;
import com.black_dog20.knowledgedrop.Config;
import com.black_dog20.knowledgedrop.KnowledgeDrop;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

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
                            event.getDrops().add(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), addRandomMinLevelEnchantment(new ItemStack(Items.BOOK), true)));
                        }
                    }
                }
            }
        }
    }

    private static ItemStack addRandomMinLevelEnchantment(ItemStack stack, boolean allowTreasure) {
        Optional<EnchantmentData> enchantmentData = getRandomEnchantmentData(stack, allowTreasure);

        boolean flag = stack.getItem() == Items.BOOK;
        if (flag) {
            stack = new ItemStack(Items.ENCHANTED_BOOK);
        }

        if(!enchantmentData.isPresent()) {
            return stack;
        }

        if (flag) {
            EnchantedBookItem.addEnchantment(stack, enchantmentData.get());
        } else {
            stack.addEnchantment(enchantmentData.get().enchantment, enchantmentData.get().enchantmentLevel);
        }
        return stack;
    }

    private static Optional<EnchantmentData> getRandomEnchantmentData(ItemStack stack, boolean allowTreasure) {

        return ForgeRegistries.ENCHANTMENTS.getValues().stream()
                .filter(getEnchantmentFilter(stack, allowTreasure))
                .map(e -> new EnchantmentData(e, e.getMinLevel()))
                .collect(StreamUtils.toShuffledStream())
                .limit(1)
                .findFirst();
    }

    private static Predicate<Enchantment> getEnchantmentFilter(ItemStack stack, boolean allowTreasure) {
        boolean flag = stack.getItem() == Items.BOOK;
        return enchantment -> (!enchantment.isTreasureEnchantment() || allowTreasure) && (enchantment.canApplyAtEnchantingTable(stack) || (flag && enchantment.isAllowedOnBooks()));
    }

}
