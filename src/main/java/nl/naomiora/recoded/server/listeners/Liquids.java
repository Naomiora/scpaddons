package nl.naomiora.recoded.server.listeners;

import java.util.HashMap;
import java.util.UUID;

import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nl.naomiora.recoded.Main;
import nl.naomiora.recoded.registration.FluidsRegistration;

public class Liquids {
	public HashMap<UUID, Integer> delay;
	
	public Liquids() {
		this.delay = new HashMap<UUID, Integer>();
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		BlockPos pos = event.getEntityLiving().getPosition();
		if (!event.getEntityLiving().world.getBlockState(pos).getBlock()
				.isAir(event.getEntityLiving().world.getBlockState(pos), event.getEntityLiving().world, pos)) {
			if(event.getEntityLiving().world.getBlockState(pos).getBlock().equals(FluidsRegistration.ACID.getBlock())) {
				if(this.delay.containsKey(event.getEntityLiving().getUniqueID())) {
					if(this.delay.get(event.getEntityLiving().getUniqueID()) <= 0) {
						event.getEntityLiving().attackEntityFrom(DamageSource.MAGIC, 1000F);
						event.getEntityLiving().addPotionEffect(new PotionEffect(Main.instance.getPotionById(20), 120, 1));
						this.delay.put(event.getEntityLiving().getUniqueID(), 40);
					} else {
						this.delay.put(event.getEntityLiving().getUniqueID(), this.delay.get(event.getEntityLiving().getUniqueID())-1);
					}
				} else {
					this.delay.put(event.getEntityLiving().getUniqueID(), 40);
					event.getEntityLiving().attackEntityFrom(DamageSource.MAGIC, 1000F);
					event.getEntityLiving().addPotionEffect(new PotionEffect(Main.instance.getPotionById(20), 120, 1));
				}
			}
		}
	}
}
