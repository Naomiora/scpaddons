package nl.naomiora.recoded.client;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import nl.naomiora.recoded.Main;
import nl.naomiora.recoded.potions.AdditionalPotions;

public class SyncPotions {
	public static SyncPotions instance;
	
	public SyncPotions() {
		instance = this;
	}

	public void addPotion(int id, int duration, int amp) {
		Minecraft.getMinecraft().player.addPotionEffect(new PotionEffect(Main.instance.getPotionById(id), duration, amp));
	}

	public void removePotion(int id) {
		Minecraft.getMinecraft().player.removePotionEffect(Main.instance.getPotionById(id));
	}
}