package nl.naomiora.recoded.potions;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import nl.naomiora.recoded.potions.base.PotionBase;

public class AdditionalPotions {
	public static final Potion CONTROLLED = new PotionBase("controlled", true, 5242967, 0, 0, "012effect");
	public static final PotionType CONTROLLEDTYPE = new PotionType("controlled",
			new PotionEffect[] { new PotionEffect(CONTROLLED, 20) }).setRegistryName("controlled");
	public static final Potion FEAR = new PotionBase("fear", true, 0, 0, 0, "fear");
	public static final PotionType FEARTYPE = new PotionType("fear", new PotionEffect[] { new PotionEffect(FEAR, 20) })
			.setRegistryName("fear");
	public static final Potion HAPPINESS = new PotionBase("happiness", false, 16724572, 0, 0, "happiness");
	public static final PotionType HAPPINESSTYPE = new PotionType("happiness",
			new PotionEffect[] { new PotionEffect(HAPPINESS, 20) }).setRegistryName("happiness");
	public static Map<Integer, Potion> customIds = new HashMap<Integer, Potion>();

	public AdditionalPotions() {
		((PotionBase) CONTROLLED).setType(CONTROLLEDTYPE);
		((PotionBase) FEAR).setType(FEARTYPE);
		((PotionBase) HAPPINESS).setType(HAPPINESSTYPE);
		int i = 125;
		customIds.put(i, CONTROLLED);
		i = i + 1;
		customIds.put(i, FEAR);
		i = i + 1;
		customIds.put(i, HAPPINESS);
		
		for(Potion potion : ForgeRegistries.POTIONS) {
			if(potion.getIdFromPotion(potion) == -1) {
				i = i + 1;
				customIds.put(i, potion);
			}
		}
	}
}