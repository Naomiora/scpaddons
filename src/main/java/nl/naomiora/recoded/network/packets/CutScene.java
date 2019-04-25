package nl.naomiora.recoded.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import nl.naomiora.recoded.TempStorage.TempStorage;

public class CutScene implements IMessage {
	private boolean start;

	public CutScene() {
	}

	public CutScene(boolean start) {
		this.start = start;
	}

	public void fromBytes(ByteBuf buf) {
		this.start = buf.readBoolean();
	}

	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(this.start);
	}

	public static class Handler implements IMessageHandler<CutScene, IMessage> {
		public IMessage onMessage(final CutScene message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(new Runnable() {

				@Override
				public void run() {
					EntityPlayerSP player = Minecraft.getMinecraft().player;
					TempStorage.instance.setCutScene(message.start);
				}
			});
			return null;
		}
	}
}