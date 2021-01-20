package com.black_dog20.knowledgedrop.common.data;

import com.black_dog20.bml.datagen.BaseLanguageProvider;
import com.black_dog20.knowledgedrop.KnowledgeDrop;
import com.black_dog20.knowledgedrop.common.enchantments.ModEnchantments;
import net.minecraft.data.DataGenerator;

public class GeneratorLanguage extends BaseLanguageProvider {

    public GeneratorLanguage(DataGenerator gen) {
        super(gen, KnowledgeDrop.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addEnchantment(ModEnchantments.KNOWLEDGE_DROP, "Knowledge Drop", "Mobs will have a chance to drop random enchanted books when killed.");
    }
}
