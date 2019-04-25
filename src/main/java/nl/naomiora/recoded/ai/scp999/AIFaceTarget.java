package nl.naomiora.recoded.ai.scp999;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import nl.naomiora.recoded.entity.Entity999;

public class AIFaceTarget extends EntityAIBase {
	private final Entity999 entity;

	public AIFaceTarget(Entity999 entity) {
		this.entity = entity;
		this.setMutexBits(2);
	}

	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = this.entity.getAttackTarget();
		EntityItem item = this.entity.getSugar();
		
		if(item == null) {
			if (entitylivingbase == null) {
				return false;
			} else if (!entitylivingbase.isEntityAlive()) {
				return false;
			} else {
				return !(entitylivingbase instanceof EntityPlayer)
						|| !((EntityPlayer) entitylivingbase).capabilities.disableDamage;
			}
		} else {
			return true;
		}
	}

	public void startExecuting() {
		super.startExecuting();
	}

	public boolean shouldContinueExecuting() {
		EntityLivingBase entitylivingbase = this.entity.getAttackTarget();
		EntityItem item = this.entity.getSugar();
		
		if(item == null) {
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
		} else {
			return true;
		}
	}

	public void updateTask() {
		if (this.entity.getAttackTarget() != null) {
			this.entity.faceEntity(this.entity.getAttackTarget(), 10.0F, 10.0F);
			((Entity999.MoveHelperSCP) this.entity.getMoveHelper()).setDirection(this.entity.rotationYaw);
		} else {
			if(this.entity.getSugar() != null) {
				this.entity.faceEntity(this.entity.getSugar(), 10.0F, 10.0F);
				((Entity999.MoveHelperSCP) this.entity.getMoveHelper()).setDirection(this.entity.rotationYaw);
			}
		}
		//this.entity.getLookHelper().setLookPosition(this.closestEntity.posX, this.closestEntity.posY + (double)this.closestEntity.getEyeHeight(), this.closestEntity.posZ, (float)this.entity.getHorizontalFaceSpeed(), (float)this.entity.getVerticalFaceSpeed());
	}
}