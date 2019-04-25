package nl.naomiora.recoded.ai.scp682;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import nl.naomiora.recoded.entity.Entity682;

public class AIFaceTarget extends EntityAIBase {
	private final Entity682 entity;

	public AIFaceTarget(Entity682 entity) {
		this.entity = entity;
		this.setMutexBits(2);
	}

	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = this.entity.getAttackTarget();

		if (entitylivingbase == null) {
			return false;
		} else if (!entitylivingbase.isEntityAlive()) {
			return false;
		} else {
			return !(entitylivingbase instanceof EntityPlayer)
					|| !((EntityPlayer) entitylivingbase).capabilities.disableDamage;
		}
	}

	public void startExecuting() {
		super.startExecuting();
	}

	public boolean shouldContinueExecuting() {
		EntityLivingBase entitylivingbase = this.entity.getAttackTarget();

		if (entitylivingbase == null) {
			return false;
		} else if (!entitylivingbase.isEntityAlive()) {
			return false;
		} else if (entitylivingbase instanceof EntityPlayer
				&& ((EntityPlayer) entitylivingbase).capabilities.disableDamage) {
			return false;
		} else {
			return true;
		}

	}

	public void updateTask() {
		if (this.entity.getAttackTarget() != null) {
		this.entity.getLookHelper().setLookPosition(this.entity.getAttackTarget().posX,
				this.entity.getAttackTarget().posY + (double) this.entity.getAttackTarget().getEyeHeight(), this.entity.getAttackTarget().posZ,
				(float) this.entity.getHorizontalFaceSpeed(), (float) this.entity.getVerticalFaceSpeed());
		}
	}
}