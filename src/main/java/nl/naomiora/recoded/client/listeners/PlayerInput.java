package nl.naomiora.recoded.client.listeners;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.settings.KeyBindingMap;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nl.naomiora.recoded.TempStorage.TempStorage;

public class PlayerInput {

	@SubscribeEvent
	public void movement(InputUpdateEvent event) {
		if(Minecraft.getMinecraft().player == null) {
			return;
		}
		EntityPlayer player = Minecraft.getMinecraft().player;
		
		if (!player.isCreative()) {
			if(TempStorage.instance.cutSceneEnabled()) {
				if(Minecraft.getMinecraft().gameSettings.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindTogglePerspective)) {
					Minecraft.getMinecraft().gameSettings.thirdPersonView = 2;
				}
			}
		}
		
		if(TempStorage.instance.getFreezeKeyBoard()) {
			event.getMovementInput().moveForward = 0;
			event.getMovementInput().moveStrafe = 0;
			event.getMovementInput().backKeyDown = false;
			event.getMovementInput().forwardKeyDown = false;
			event.getMovementInput().jump = false;
			event.getMovementInput().leftKeyDown = false;
			event.getMovementInput().rightKeyDown = false;
		}
	}
}
