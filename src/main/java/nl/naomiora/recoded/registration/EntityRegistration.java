package nl.naomiora.recoded.registration;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import nl.naomiora.recoded.Main;
import nl.naomiora.recoded.entity.Entity2521;
import nl.naomiora.recoded.entity.Entity682;
import nl.naomiora.recoded.entity.Entity999;

public class EntityRegistration {
	public static EntityRegistration instance;
	
	public EntityRegistration() {
		instance = this;
		this.registerEntities("scp-999", Entity999.class, 999, 50, 14324992, 14324992);
		this.registerEntities("scp-2521", Entity2521.class, 2521, 50, 0, 0);
		this.registerEntities("scp-682", Entity682.class, 682, 50, 19456, 8519680);
	}
	
	public void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int primaryColor, int secondaryColor) {
		this.registerEntities(name, entity, id, range, primaryColor, secondaryColor);
	}
	
	private void registerEntities(String name, Class<? extends Entity> entity, int id, int range, int primaryColor, int secondaryColor) {
		EntityRegistry.registerModEntity(new ResourceLocation("scpadgrace:" + name), entity, name, id, Main.instance, range, 1, true, primaryColor, secondaryColor);
	}
}