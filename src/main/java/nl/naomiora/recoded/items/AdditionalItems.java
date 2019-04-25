package nl.naomiora.recoded.items;

import java.util.ArrayList;

import net.minecraft.item.Item;
import nl.naomiora.recoded.items.base.ItemBase;

public class AdditionalItems {

	public static final ArrayList<Item> items = new ArrayList<Item>();

	public static final Item scp012 = new ItemBase("scp_012", 1, false);

	public static Item getLoadedItem(String name) {
		for (Item item : items) {
			if (item.getRegistryName().toString().contains(name)) {
				return item;
			}
		}
		return null;
	}
}