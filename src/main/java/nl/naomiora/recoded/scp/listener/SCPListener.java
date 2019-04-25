package nl.naomiora.recoded.scp.listener;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nl.naomiora.recoded.items.AdditionalItems;

public class SCPListener {
	@SubscribeEvent
	public void playerPickupItemEvent(EntityItemPickupEvent event) {
		if (event.getItem().getItem().getItem().equals(AdditionalItems.getLoadedItem("scp_012"))) {
			if (event.getEntityPlayer() != null) {
				EntityPlayer player = event.getEntityPlayer();
				if(event.getItem().world.getLightFromNeighbors(event.getItem().getPosition()) > 11) {
					if(!player.isCreative()) {
						event.setCanceled(true);
					}
				}
			} else {
				event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void itemDropEvent(ItemTossEvent event) {
		if(AdditionalItems.getLoadedItem("scp_012") != null
				&& event.getEntityItem().getItem().getItem().equals(AdditionalItems.getLoadedItem("scp_012"))) {
			if(event.getPlayer() != null) {
				if(event.getPlayer().world.getLightFromNeighbors(event.getPlayer().getPosition()) > 11) {
					if(!event.getPlayer().isCreative()) {
						event.setCanceled(true);
						event.getPlayer().addItemStackToInventory(event.getEntityItem().getItem());
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void itemExpireEvent(ItemExpireEvent event) {
		if (AdditionalItems.getLoadedItem("scp_012") != null
				&& event.getEntityItem().getItem().getItem().equals(AdditionalItems.getLoadedItem("scp_012"))) {
			event.setCanceled(true);
		}
	}
}
