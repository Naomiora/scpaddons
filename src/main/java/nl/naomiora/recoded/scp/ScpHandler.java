package nl.naomiora.recoded.scp;

import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import nl.naomiora.recoded.TempStorage.TempStorage;
import nl.naomiora.recoded.potions.AdditionalPotions;
import nl.naomiora.recoded.scp.listener.SCPListener;
import nl.naomiora.recoded.scp.scp012.SCP012;

public class ScpHandler {
	private SCP012 scp012 = new SCP012();
	
	public ScpHandler() {
		this.scp012 = new SCP012();
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new SCPListener());
	}
	
	@SubscribeEvent
	public void serverTickEvent(TickEvent event) {
		if(event instanceof ServerTickEvent) {
			scp012.check();
			
		}
	}
}
