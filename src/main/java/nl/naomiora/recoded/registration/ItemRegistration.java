package nl.naomiora.recoded.registration;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nl.naomiora.recoded.items.AdditionalItems;
import nl.naomiora.recoded.registration.interfaces.Items;

public class ItemRegistration {

	@SubscribeEvent
	public void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(AdditionalItems.items.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public void onModelRegister(ModelRegistryEvent event) {
		for(Item item : AdditionalItems.items) {
			if(item instanceof Items) {
				((Items) item).registerItem();
			}
		}
	}
}