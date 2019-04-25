package nl.naomiora.recoded.server.listeners;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import nl.naomiora.recoded.Main;
import nl.naomiora.recoded.TempStorage.TempStorage;
import nl.naomiora.recoded.network.NetworkHandler;
import nl.naomiora.recoded.network.packets.SyncPotions;
import nl.naomiora.recoded.potions.AdditionalPotions;

public class PlayerLoggingListener {

	public PlayerLoggingListener() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void join(PlayerLoggedInEvent event) {
		TempStorage.instance.setSaidIt(event.player.getUniqueID(), false);
		if(event.player.isPotionActive(AdditionalPotions.FEAR))	{
			event.player.removeActivePotionEffect(AdditionalPotions.FEAR);
			NetworkHandler.INSTANCE.sendTo(new SyncPotions(Main.instance.getIdByPotion(AdditionalPotions.FEAR), 0, 0, false), (EntityPlayerMP) event.player);
		}
	}
	
	@SubscribeEvent
	public void leave(PlayerLoggedOutEvent event) {
		TempStorage.instance.removeFromSaidIt(event.player.getUniqueID());
	}
}
