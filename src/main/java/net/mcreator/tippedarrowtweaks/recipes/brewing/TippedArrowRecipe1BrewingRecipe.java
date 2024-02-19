
package net.mcreator.tippedarrowtweaks.recipes.brewing;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.Ingredient;

import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TippedArrowRecipe1BrewingRecipe implements IBrewingRecipe {
	@NotNull private final Ingredient input;
	@NotNull private final Ingredient ingredient;
    @NotNull private final ItemStack output;
    
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> BrewingRecipeRegistry.addRecipe(new TippedArrowRecipe1BrewingRecipe()));
	}

	public TippedArrowRecipe1BrewingRecipe() {
		this.input = Ingredient.of(Items.ARROW);
		this.ingredient = Ingredient.of(Items.POTION);
		this.output = new ItemStack(Items.TIPPED_ARROW);
	}

	@Override
	public boolean isInput(ItemStack input) {
		return this.input.test(input);
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		return this.ingredient.test(ingredient);
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		if (isInput(input) && isIngredient(ingredient)) {
			ItemStack newArrow =  output.copy();
			PotionUtils.setPotion(newArrow, PotionUtils.getPotion(ingredient));
			return newArrow;
		}
		return ItemStack.EMPTY;
	}
}
