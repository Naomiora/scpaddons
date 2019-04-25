package nl.naomiora.recoded.TempStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import nl.naomiora.recoded.client.listeners.PlayerInput;
import nl.naomiora.recoded.client.listeners.PlayerRenderTick;

public class TempStorage {
	public static TempStorage instance;
	private boolean freezeKeyboard;
	private boolean cutScene;
	private Map<UUID, Boolean> saidIt; //For server

	public TempStorage(Side side) {
		instance = this;

		saidIt = new HashMap<UUID, Boolean>();
		if (side.equals(Side.CLIENT)) {
			MinecraftForge.EVENT_BUS.register(new PlayerInput());
			new PlayerRenderTick();
			this.freezeKeyboard = false;
		}
	}

	public void saidIt() {
		Minecraft.getMinecraft().player
				.sendMessage(new TextComponentString("You've said something you shouldn't have..."));
		this.setSaidIt(Minecraft.getMinecraft().player.getUniqueID(), true);
	}

	public void setSaidIt(UUID uuid, boolean bool) {
		this.saidIt.put(uuid, bool);
	}

	public boolean saidItAlready(UUID uuid) {
		try {
			if (this.saidIt.isEmpty()) {
				return false;
			}
			if (this.saidIt.containsKey(uuid)) {
				return this.saidIt.get(uuid);
			}
		} catch (NullPointerException e) {
		}
		return false;
	}

	public void setHealth(double hp) {
		Minecraft.getMinecraft().player.setHealth((float) hp);
	}

	public boolean getFreezeKeyBoard() {
		return this.freezeKeyboard;
	}

	public void setFreezeKeyboard(boolean bool) {
		this.freezeKeyboard = bool;
	}

	public void removeFromSaidIt(UUID uuid) {
		this.saidIt.remove(uuid);
	}

	public void setCutScene(boolean start) {
		PlayerRenderTick.instance.getCamera().disable();
		this.cutScene = start;
	}
	
	public boolean cutSceneEnabled() {
		return this.cutScene;
	}
}
