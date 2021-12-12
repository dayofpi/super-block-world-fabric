package com.dayofpi.sbw_main.common.item.types;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GoldenMushroomItem extends Item {
    public GoldenMushroomItem(Settings settings) {
        super(settings);
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add( new TranslatableText("tooltip.super_block_world.super_mushroom").formatted(Formatting.BLUE));
        tooltip.add( new TranslatableText("tooltip.super_block_world.golden_mushroom").formatted(Formatting.BLUE));
    }
}
