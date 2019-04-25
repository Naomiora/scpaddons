package nl.naomiora.recoded.registration;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import nl.naomiora.recoded.potions.AdditionalPotions;

public class PotionRegistration {
	public static PotionRegistration instance;
	
	public PotionRegistration() {
		instance = this;
		this.registerPotion(AdditionalPotions.CONTROLLEDTYPE, AdditionalPotions.CONTROLLED);
		this.registerPotion(AdditionalPotions.FEARTYPE, AdditionalPotions.FEAR);
		this.registerPotion(AdditionalPotions.HAPPINESSTYPE, AdditionalPotions.HAPPINESS);
	}
	
	public void registerPotion(PotionType type, Potion effect) {
		ForgeRegistries.POTIONS.register(effect);
		ForgeRegistries.POTION_TYPES.register(type);
	}
}