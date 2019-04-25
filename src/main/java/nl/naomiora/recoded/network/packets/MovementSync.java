package nl.naomiora.recoded.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MovementSync implements IMessage {
	private double motionZ;
	private double motionX;

	public MovementSync() {
	}

	public MovementSync(double d, double e) {
		this.motionX = d;
		this.motionZ = e;
	}

	public void fromBytes(ByteBuf buf) {
		this.motionX = buf.readDouble();
		this.motionZ = buf.readDouble();
	}

	public void toBytes(ByteBuf buf) {
		buf.writeDouble(this.motionX);
		buf.writeDouble(this.motionZ);
	}

	public static class Handler implements IMessageHandler<MovementSync, IMessage> {
		public IMessage onMessage(final MovementSync message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(new Runnable() {

				@Override
				public void run() {
					EntityPlayerSP player = Minecraft.getMinecraft().player;
					player.motionX = message.motionX;
					player.motionZ = message.motionZ;
				}
			});
			return null;
		}
	}
}