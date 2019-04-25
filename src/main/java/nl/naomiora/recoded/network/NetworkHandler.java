package nl.naomiora.recoded.network;

import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import nl.naomiora.recoded.network.packets.CutScene;
import nl.naomiora.recoded.network.packets.Freeeezeeeee;
import nl.naomiora.recoded.network.packets.LookAtIt;
import nl.naomiora.recoded.network.packets.MovementSync;
import nl.naomiora.recoded.network.packets.PlaySound;
import nl.naomiora.recoded.network.packets.SaidDeadlyWord;
import nl.naomiora.recoded.network.packets.SyncHealth;
import nl.naomiora.recoded.network.packets.SyncPotions;

public class NetworkHandler {
	public static SimpleNetworkWrapper INSTANCE;
	
	public NetworkHandler() {
		INSTANCE = new SimpleNetworkWrapper("scpadgrace");
		
		INSTANCE.registerMessage(PlaySound.Handler.class, PlaySound.class, 0, Side.CLIENT);
		INSTANCE.registerMessage(LookAtIt.Handler.class, LookAtIt.class, 1, Side.CLIENT);
		INSTANCE.registerMessage(MovementSync.Handler.class, MovementSync.class, 2, Side.CLIENT);
		INSTANCE.registerMessage(Freeeezeeeee.Handler.class, Freeeezeeeee.class, 3, Side.CLIENT);
		INSTANCE.registerMessage(SyncPotions.Handler.class, SyncPotions.class, 4, Side.CLIENT);
		INSTANCE.registerMessage(SyncHealth.Handler.class, SyncHealth.class, 5, Side.CLIENT);
		INSTANCE.registerMessage(SaidDeadlyWord.Handler.class, SaidDeadlyWord.class, 6, Side.CLIENT);
		INSTANCE.registerMessage(CutScene.Handler.class, CutScene.class, 7, Side.CLIENT);
	}
}
