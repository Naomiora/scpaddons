package nl.naomiora.recoded.server;

import net.minecraftforge.common.MinecraftForge;
import nl.naomiora.recoded.server.listeners.Liquids;
import nl.naomiora.recoded.server.listeners.PlayerLoggingListener;

public class ServerModule {
	public static ServerModule instance;
	
	public ServerModule() {
		instance = this;
		new PlayerLoggingListener();
		new Liquids();
	}
}
