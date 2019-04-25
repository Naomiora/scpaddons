package nl.naomiora.recoded.client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;

public class SyncMaxHealth {
	public static SyncMaxHealth instance;

	public SyncMaxHealth() {
		instance = this;
	}

	public void setMaxHealth(double hp) {
		Minecraft.getMinecraft().player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(hp);
	}
}
