package nl.naomiora.recoded.potions.base;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import nl.naomiora.recoded.potions.AdditionalPotions;
import nl.naomiora.recoded.registration.PotionRegistration;
import nl.naomiora.recoded.registration.interfaces.Potions;

public class PotionBase extends Potion implements Potions{
	private String pathName;
	private PotionType type;
	
	public PotionBase(String name, boolean isBadPotion, int colour, int iconIndexX, int iconIndexY, String pathName) {
		super(isBadPotion, colour);
		this.setPotionName("effect." + name);
		this.setIconIndex(iconIndexX, iconIndexY);
		this.setRegistryName(new ResourceLocation("scpadgrace:" + name));
		this.pathName = pathName;
	}
	
	public void setType(PotionType type) {
		this.type = type;
	}

	@Override
	public boolean hasStatusIcon() {
		return false;
	}
	
	@Override
	public void registerPotion() {
		PotionRegistration.instance.registerPotion(type, this);
	}
}