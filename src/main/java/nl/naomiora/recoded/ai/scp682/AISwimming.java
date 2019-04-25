package nl.naomiora.recoded.ai.scp682;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigateGround;
import nl.naomiora.recoded.entity.Entity682;

public class AISwimming extends EntityAIBase {
	private final Entity682 entity;

	public AISwimming(Entity682 entityIn) {
		this.entity = entityIn;
		this.setMutexBits(4);

		if (entityIn.getNavigator() instanceof PathNavigateGround) {
			((PathNavigateGround) entityIn.getNavigator()).setCanSwim(true);
		}
	}

	public boolean shouldExecute() {
		return this.entity.isInWater() || this.entity.isInLava();
	}

	public void updateTask() {
		if (this.entity.getRNG().nextFloat() < 0.8F) {
			this.entity.getJumpHelper().setJumping();
			this.entity.setAIMoveSpeed(this.entity.getSpeed()*1.3F);
		}
	}
}