package nl.naomiora.recoded.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import nl.naomiora.recoded.Main;

public class Freeeezeeeee implements IMessage {
	private boolean freeze;

	public Freeeezeeeee() {
	}

	public Freeeezeeeee(boolean freeze) {
		this.freeze = freeze;
	}

	public void fromBytes(ByteBuf buf) {
		this.freeze = buf.readBoolean();
	}

	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(this.freeze);
	}

	public static class Handler implements IMessageHandler<Freeeezeeeee, IMessage> {
		public IMessage onMessage(final Freeeezeeeee message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(new Runnable() {

				@Override
				public void run() {
					Main.instance.freeze(message.freeze);
				}
			});
			return null;
		}
	}
}