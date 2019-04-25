package nl.naomiora.recoded.ai.scp682;

import javax.annotation.Nullable;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;
import nl.naomiora.recoded.entity.Entity682;

public class EntityWander extends EntityAIBase {
	protected final Entity682 entity;
	protected double x;
	protected double y;
	protected double z;
	protected int executionChance;
	protected boolean mustUpdate;

	public EntityWander(Entity682 creatureIn) {
		this(creatureIn, 20);
	}

	public EntityWander(Entity682 creatureIn, int chance) {
		this.entity = creatureIn;
		this.executionChance = chance;
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		if(this.entity.getAttackTarget() != null) {
			return true;
		}
		
		if (!this.mustUpdate) {
			if (this.entity.getRNG().nextInt(this.executionChance) != 0) {
				return false;
			}
		}

		Vec3d vec3d = this.getPosition();

		if (vec3d == null) {
			return false;
		} else {
			this.x = vec3d.x;
			this.y = vec3d.y;
			this.z = vec3d.z;
			this.mustUpdate = false;
			return true;
		}
	}

	@Nullable
	protected Vec3d getPosition() {
		return RandomPositionGenerator.findRandomTarget(this.entity, 52, 14);
	}

	@Override
	public boolean shouldContinueExecuting() {
		return !this.entity.getNavigator().noPath();
	}

	@Override
	public void updateTask() {
		if(this.entity.getAttackTarget() != null) {
			this.entity.getNavigator().tryMoveToEntityLiving(entity.getAttackTarget(), this.entity.getSpeed());
		} else {
			this.entity.getNavigator().tryMoveToXYZ(this.x, this.y, this.z, this.entity.getSpeed());
		}
	}
	
	@Override
	public void startExecuting() {
		if(this.entity.getAttackTarget() == null) {
			this.entity.getNavigator().tryMoveToXYZ(this.x, this.y, this.z, this.entity.getSpeed());
		} else {
			this.entity.getNavigator().tryMoveToEntityLiving(entity.getAttackTarget(), this.entity.getSpeed());
		}
	}

	public void makeUpdate() {
		this.mustUpdate = true;
	}

	public void setExecutionChance(int newchance) {
		this.executionChance = newchance;
	}
}
