package nl.naomiora.recoded.scp.scp2521;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nl.naomiora.recoded.Main;
import nl.naomiora.recoded.TempStorage.TempStorage;
import nl.naomiora.recoded.entity.Entity2521;
import nl.naomiora.recoded.network.NetworkHandler;
import nl.naomiora.recoded.network.packets.CutScene;
import nl.naomiora.recoded.network.packets.Freeeezeeeee;
import nl.naomiora.recoded.network.packets.SaidDeadlyWord;
import nl.naomiora.recoded.network.packets.SyncPotions;
import nl.naomiora.recoded.potions.AdditionalPotions;

public class PlayerChat {

	@SubscribeEvent
	public void chat(ServerChatEvent event) {
		if (event.getPlayer().isCreative()) {
			return;
		}

		if (event.getMessage().trim().replace(" ", "").contains("2521")) {
			if (!TempStorage.instance.saidItAlready(event.getPlayer().getUniqueID())) {
				NetworkHandler.INSTANCE.sendTo(new SaidDeadlyWord(), event.getPlayer());
				NetworkHandler.INSTANCE.sendTo(new Freeeezeeeee(true), event.getPlayer());
				this.spawnSCP(event.getPlayer());
			}
		}
	}

	public void spawnSCP(EntityPlayer player) {
		Vec3d vec = player.getLookVec();
		double dx = player.posX - (vec.x);
		double dy = player.posY;
		double dz = player.posZ - (vec.z);
		Entity2521 entity = new Entity2521(player.world);
		entity.setPositionAndUpdate(dx, dy, dz);
		entity.setTarget(player);
		player.world.spawnEntity(entity);
		NetworkHandler.INSTANCE.sendTo(new CutScene(true), (EntityPlayerMP) player);
	}
}