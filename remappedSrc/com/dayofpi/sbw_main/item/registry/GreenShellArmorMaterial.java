package com.dayofpi.sbw_main.item.registry;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class GreenShellArmorMaterial implements ArmorMaterial {
    @Override
    public int getDurability(EquipmentSlot slot) {
        return 100;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return 2;
    }

    @Override
    public int getEnchantability() {
        return 1;
    }

    @Override
    public SoundEvent getEquipSound() {
        return null;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.SCUTE);
    }

    @Override
    public String getName() {
        return "green_shell";
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 0F;
    }
}
