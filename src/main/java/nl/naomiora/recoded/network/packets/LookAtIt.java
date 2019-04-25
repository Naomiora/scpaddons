package nl.naomiora.recoded.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class LookAtIt implements IMessage {
	private float yaw;
	private float pitch;

	public LookAtIt() {
	}

	public LookAtIt(float yaw, float pitch) {
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public void fromBytes(ByteBuf buf) {
		this.yaw = buf.readFloat();
		this.pitch = buf.readFloat();
	}

	public void toBytes(ByteBuf buf) {
		buf.writeFloat(this.yaw);
		buf.writeFloat(this.pitch);
	}

	public static class Handler implements IMessageHandler<LookAtIt, IMessage> {
		public IMessage onMessage(final LookAtIt message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(new Runnable() {

				@Override
				public void run() {
					EntityPlayerSP player = Minecraft.getMinecraft().player;
					player.rotationYaw = message.yaw;
					player.rotationPitch = message.pitch;
				}
			});
			return null;
		}
	}
}