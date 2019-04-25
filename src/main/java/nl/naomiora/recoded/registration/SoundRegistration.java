package nl.naomiora.recoded.registration;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class SoundRegistration {
	public static SoundEvent SCP682_JAW_SNAP;

	private static SoundEvent createSoundEvent(String soundName) {
		ResourceLocation soundID = new ResourceLocation("scpadgrace", soundName);
		return new SoundEvent(soundID).setRegistryName(soundID);
	}

	public SoundRegistration() {
		MinecraftForge.EVENT_BUS.register(this);
		SCP682_JAW_SNAP = this.createSoundEvent("scp682.jaw_snap");
	}
	
	@SubscribeEvent
	public void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
		event.getRegistry().registerAll(new SoundEvent[] { this.SCP682_JAW_SNAP });
	}
}