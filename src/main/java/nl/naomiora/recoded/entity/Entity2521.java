package nl.naomiora.recoded.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import nl.naomiora.recoded.ai.scp2521.AIHandle;

public class Entity2521 extends EntityCreature implements IMob {
	private EntityPlayer target;

	public Entity2521(World worldIn) {
		super(worldIn);
		this.setSize(1.5F, 7.0F);
		this.moveHelper = new Entity2521.MoveHelperSCP(this);
	}

	public void despawn() {
		this.setDead();
	}

	public void setTarget(EntityPlayer player) {
		this.target = player;
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) {
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new AIHandle(this));
	}

	@Override
	public EntityLivingBase getAttackTarget() {
		return this.target;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.1D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1000000D);
	}

	@Override
	protected void damageEntity(DamageSource damageSrc, float damageAmount) {
		super.damageEntity(damageSrc, damageSrc.getTrueSource() == null ? damageAmount : 0.0F);
	}

	@Override
	public boolean canPickUpLoot() {
		return false;
	}

	@Override
	public boolean canBeLeashedTo(EntityPlayer player) {
		return false;
	}

	@Override
	protected boolean canDropLoot() {
		return false;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	public static class MoveHelperSCP extends EntityMoveHelper {
		private float yRot;
		private final Entity2521 slime;

		public MoveHelperSCP(Entity2521 slimeIn) {
			super(slimeIn);
			this.slime = slimeIn;
			this.yRot = 180.0F * slimeIn.rotationYaw / (float) Math.PI;
		}

		public void setDirection(float p_179920_1_) {
			this.yRot = p_179920_1_;
		}

		public void onUpdateMoveHelper() {
			this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, this.yRot, 90.0F);
			this.entity.rotationYawHead = this.entity.rotationYaw;
			this.entity.renderYawOffset = this.entity.rotationYaw;
		}
	}
}