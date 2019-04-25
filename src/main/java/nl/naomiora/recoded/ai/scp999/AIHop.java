package nl.naomiora.recoded.ai.scp999;

import net.minecraft.entity.ai.EntityAIBase;
import nl.naomiora.recoded.entity.Entity999;

public class AIHop extends EntityAIBase {
	private final Entity999 slime;

	public AIHop(Entity999 slimeIn) {
		this.slime = slimeIn;
		this.setMutexBits(5);
	}

	public boolean shouldExecute() {
		return this.slime.hop();
	}

	public void updateTask() {
		((Entity999.MoveHelperSCP) this.slime.getMoveHelper()).setSpeed(1.0D);
	}
}