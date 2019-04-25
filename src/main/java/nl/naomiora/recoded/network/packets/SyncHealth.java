package nl.naomiora.recoded.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import nl.naomiora.recoded.Main;
import nl.naomiora.recoded.client.SyncMaxHealth;

public class SyncHealth implements IMessage {
	private double health;
	private boolean maxHealth;

	public SyncHealth() {
	}

	public SyncHealth(double amount, boolean maxHp) {
		this.health = amount;
		this.maxHealth = maxHp;
	}

	public void fromBytes(ByteBuf buf) {
		this.health = buf.readDouble();
		this.maxHealth = buf.readBoolean();
	}

	public void toBytes(ByteBuf buf) {
		buf.writeDouble(this.health);
		buf.writeBoolean(this.maxHealth);
	}

	public static class Handler implements IMessageHandler<SyncHealth, IMessage> {
		public IMessage onMessage(final SyncHealth message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(new Runnable() {

				@Override
				public void run() {
					if(message.maxHealth) {
						SyncMaxHealth.instance.setMaxHealth(message.health);
					} else {
						Main.instance.setHealth(message.health);
					}
				}
			});
			return null;
		}
	}
}