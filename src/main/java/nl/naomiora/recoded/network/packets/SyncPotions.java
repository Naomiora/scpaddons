package nl.naomiora.recoded.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import nl.naomiora.recoded.Main;

public class SyncPotions implements IMessage {
	private boolean add;
	private int id;
	private int duration;
	private int amp;

	public SyncPotions() {
	}

	public SyncPotions(int id, int duration, int amp, boolean add) {
		this.add = add;
		this.id = id;
		this.duration = duration;
		this.amp = amp;
	}

	public void fromBytes(ByteBuf buf) {
		this.add = buf.readBoolean();
		this.id = buf.readInt();
		this.duration = buf.readInt();
		this.amp = buf.readInt();
	}

	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(this.add);
		buf.writeInt(this.id);
		buf.writeInt(this.duration);
		buf.writeInt(this.amp);
	}

	public static class Handler implements IMessageHandler<SyncPotions, IMessage> {
		public IMessage onMessage(final SyncPotions message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					Main.instance.syncPotion(message.add, message.id, message.duration, message.amp);
				}
			});
			return null;
		}
	}
}