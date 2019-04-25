package nl.naomiora.recoded.ai.scp999;

import java.util.HashMap;
import java.util.UUID;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import nl.naomiora.recoded.entity.Entity999;

public class AIEatSugar extends EntityAIBase {
	private final Entity999 entity;
	private final HashMap<UUID, Integer> stage;
	private int wait;
	
	public AIEatSugar(Entity999 entityIn) {
		this.entity = entityIn;
		this.stage = new HashMap<UUID, Integer>();
		this.wait = 5;
	}
	
	@Override
	public boolean shouldExecute() {
		return this.entity.getSugar() != null;
	}
	
	@Override
	public void updateTask() {
		if(this.entity.getSugar() != null) {
			if(this.entity.getSugar().getDistanceSq(this.entity) < 1.5*1.5) {
				this.entity.setEating(true);
				if(!this.stage.containsKey(this.entity.getSugar().getUniqueID())) {
					this.stage.put(this.entity.getSugar().getUniqueID(), 0);
					this.entity.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1.0F, 1.0F);
				} else {
					if(this.wait-- <= 0) {
						this.stage.put(this.entity.getSugar().getUniqueID(), this.stage.get(this.entity.getSugar().getUniqueID()) + 1);
						if(this.stage.get(this.entity.getSugar().getUniqueID()) != 30) {
							this.entity.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1.0F, 1.0F);
							this.wait = 5;
						} else {
							this.entity.playSound(SoundEvents.ENTITY_PLAYER_BURP, 1.0F, 1.0F);
							this.wait = 5;
							this.entity.getSugar().setDead();
						}
					}
				}
			} else {
				this.entity.setEating(false);
			}
		}
	}
}
