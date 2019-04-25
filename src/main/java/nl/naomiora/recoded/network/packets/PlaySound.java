package nl.naomiora.recoded.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PlaySound implements IMessage {
	private BlockPos pos;
	private SoundEvent sound;

	public PlaySound() {
	}

	public PlaySound(BlockPos blockPos, SoundEvent soundEvent) {
		this.pos = blockPos;
		this.sound = soundEvent;
	}

	public void fromBytes(ByteBuf buf) {
		this.sound = (SoundEvent) SoundEvent.REGISTRY.getObjectById(buf.readInt());
		this.pos = BlockPos.fromLong((long) buf.readLong());
	}

	public void toBytes(ByteBuf buf) {
		buf.writeInt(SoundEvent.REGISTRY.getIDForObject((SoundEvent) this.sound));
		buf.writeLong(this.pos.toLong());
	}

	public static class Handler implements IMessageHandler<PlaySound, IMessage> {
		public IMessage onMessage(final PlaySound message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(new Runnable() {

				@Override
				public void run() {
					playSound((World) Minecraft.getMinecraft().world, message.pos, message.sound, 1.0f, 1.0f);
				}
			});
			return null;
		}

	}

	public static void playSound(World world, BlockPos blockPos, SoundEvent sound, float volume, float pitch) {
		if (world != null) {
			world.playSound((double) blockPos.getX(), (double) blockPos.getY(), (double) blockPos.getZ(), sound, null,
					volume, pitch, false);
		}
	}
}