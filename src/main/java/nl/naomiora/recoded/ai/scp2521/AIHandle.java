package nl.naomiora.recoded.ai.scp2521;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import nl.naomiora.recoded.TempStorage.TempStorage;
import nl.naomiora.recoded.entity.Entity2521;
import nl.naomiora.recoded.network.NetworkHandler;
import nl.naomiora.recoded.network.packets.CutScene;

public class AIHandle extends EntityAIBase {
	private final Entity2521 entity;
	private int kill;

	public AIHandle(Entity2521 entity) {
		this.entity = entity;
		this.kill = 300;
		this.setMutexBits(2);
	}

	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = this.entity.getAttackTarget();

		if (entitylivingbase == null) {
			this.entity.despawn();
			return false;
		} else if (!entitylivingbase.isEntityAlive()) {
			this.entity.despawn();
			return false;
		} else {
			if (entitylivingbase instanceof EntityPlayer
					&& ((EntityPlayer) entitylivingbase).capabilities.disableDamage) {
				this.entity.despawn();
			}
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
			this.entity.despawn();
			return false;
		} else if (!entitylivingbase.isEntityAlive()) {
			this.entity.despawn();
			return false;
		} else if (entitylivingbase instanceof EntityPlayer
				&& ((EntityPlayer) entitylivingbase).capabilities.disableDamage) {
			this.entity.despawn();
			return false;
		} else {
			return true;
		}
	}

	public void updateTask() {
		if (this.entity.getAttackTarget() != null) {
			this.entity.faceEntity(this.entity.getAttackTarget(), 10.0F, 10.0F);
			((Entity2521.MoveHelperSCP) this.entity.getMoveHelper()).setDirection(this.entity.rotationYaw);
			if (kill-- <= 0) {
				TempStorage.instance.removeFromSaidIt(this.entity.getAttackTarget().getUniqueID());
				NetworkHandler.INSTANCE.sendTo(new CutScene(false), (EntityPlayerMP) this.entity.getAttackTarget());
				this.entity.despawn();
				this.entity.getAttackTarget().setPositionAndUpdate(this.entity.getAttackTarget().posX, -2,
						this.entity.getAttackTarget().posZ);
			}
		}
	}
}