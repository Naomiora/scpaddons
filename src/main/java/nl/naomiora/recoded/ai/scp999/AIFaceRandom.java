package nl.naomiora.recoded.ai.scp999;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.MobEffects;
import nl.naomiora.recoded.entity.Entity999;

public class AIFaceRandom extends EntityAIBase {
	private final EntityLiving entity;
	private float chosenDegrees;
	private int nextRandomizeTime;

	public AIFaceRandom(EntityLiving slimeIn) {
		this.entity = slimeIn;
		this.setMutexBits(2);
	}

	@Override
	public boolean shouldExecute() {
		return this.entity.getAttackTarget() == null && (this.entity.onGround || this.entity.isInWater()
				|| this.entity.isInLava() || this.entity.isPotionActive(MobEffects.LEVITATION));
	}

	@Override
	public void updateTask() {
		if (--this.nextRandomizeTime <= 0) {
			this.nextRandomizeTime = 40 + this.entity.getRNG().nextInt(90);
			this.chosenDegrees = (float) this.entity.getRNG().nextInt(360);
		}

		((Entity999.MoveHelperSCP) this.entity.getMoveHelper()).setDirection(chosenDegrees);
	}
}