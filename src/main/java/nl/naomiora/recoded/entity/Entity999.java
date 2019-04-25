package nl.naomiora.recoded.entity;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import nl.naomiora.recoded.ai.scp999.AIEatSugar;
import nl.naomiora.recoded.ai.scp999.AIFaceRandom;
import nl.naomiora.recoded.ai.scp999.AIFaceTarget;
import nl.naomiora.recoded.ai.scp999.AIHop;
import nl.naomiora.recoded.ai.scp999.AIPositive;

public class Entity999 extends EntitySlime implements IMob {
	private static final DataParameter<Integer> SLIME_SIZE = EntityDataManager.<Integer>createKey(EntitySlime.class,
			DataSerializers.VARINT);
	private boolean wasOnGround;
	private boolean isBadReptileBoi;
	private boolean isHealing;
	private boolean isEating;
	private float yaw;

	public Entity999(World worldIn) {
		super(worldIn);
		this.setSlimeSize(1, true);
		this.moveHelper = new Entity999.MoveHelperSCP(this);
		this.isBadReptileBoi = false;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new AIEatSugar(this));
		this.tasks.addTask(2, new AIPositive(this));
		this.tasks.addTask(3, new AIFaceTarget(this));
		this.tasks.addTask(4, new AIFaceRandom(this));
		this.tasks.addTask(5, new AIHop(this));

		this.targetTasks.addTask(1, new EntityAIFindEntityNearest(this, Entity682.class));
		this.targetTasks.addTask(2, new EntityAIFindEntityNearestPlayer(this));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(SLIME_SIZE, Integer.valueOf(1));
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F * (float) this.getSlimeSize();
	}

	public boolean hop() {
		if (this.getAttackTarget() != null) {
			return !this.isHealing;
		} else {
			if (this.getSugar() != null) {
				return !this.isEating;
			} else {
				return true;
			}
		}
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}
	
	@Override
	public void setAttackTarget(EntityLivingBase entitylivingbaseIn) {
		if(entitylivingbaseIn instanceof Entity682) {
			this.isBadReptileBoi = true;
			super.setAttackTarget(entitylivingbaseIn);
			return;
		}
		
		this.isBadReptileBoi = false;
		if(entitylivingbaseIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entitylivingbaseIn;
			if(this.hasBadEffect(player)) {
				super.setAttackTarget(player);
			} else if (player.getHealth() < player.getMaxHealth()) {
				super.setAttackTarget(player);
			}
		}
	}

	public EntityItem getSugar() {
		try {
			for (Iterator<Entity> iter = this.world.getEntitiesInAABBexcluding(this, new AxisAlignedBB(this.posX + 10,
					this.posY + 2, this.posZ + 10, this.posX - 10, this.posY - 2, this.posZ - 10), null)
					.iterator(); iter.hasNext();) {
				Entity entityCheck = iter.next();
				if (entityCheck instanceof EntityItem) {
					EntityItem item = (EntityItem) entityCheck;
					if (item.getItem().getItem().equals(Items.SUGAR)) {
						return item;
					}
				}
			}
		} catch (ConcurrentModificationException e) {
		}
		return null;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
		this.prevSquishFactor = this.squishFactor;

		this.onGround = !this.world.isAirBlock(this.getPosition().add(0, -0.1, 0));

		if (this.onGround && !this.wasOnGround) {
			int i = this.getSlimeSize();
			if (spawnCustomParticles()) {
				i = 0;
			}
			for (int j = 0; j < i * 8; ++j) {
				float f = this.rand.nextFloat() * ((float) Math.PI * 2F);
				float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
				float f2 = MathHelper.sin(f) * (float) i * 0.5F * f1;
				float f3 = MathHelper.cos(f) * (float) i * 0.5F * f1;
				World world = this.world;
				EnumParticleTypes enumparticletypes = this.getParticleType();
				double d0 = this.posX + (double) f2;
				double d1 = this.posZ + (double) f3;
				world.spawnParticle(enumparticletypes, d0, this.getEntityBoundingBox().minY - 0.3, d1, 0.0D, 0.0D,
						0.0D);
			}

			this.playSound(this.getSquishSound(), this.getSoundVolume(),
					((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
			this.squishAmount = -0.5F;
		} else if (!this.onGround && this.wasOnGround) {
			this.squishAmount = 1.0F;
		}

		this.wasOnGround = onGround;
		this.alterSquishAmount();
	}

	@Override
	protected int getJumpDelay() {
		return this.isBadReptileBoi ? this.rand.nextInt(20) + 10 : this.rand.nextInt(20) + 30;
	}

	public boolean hasBadEffect(EntityPlayer player) {
		try {
			if (player.getActivePotionEffects().isEmpty()) {
				return false;
			}
			for (PotionEffect effect : player.getActivePotionEffects()) {
				if (effect.getPotion().isBadEffect()) {
					return true;
				}
			}
		} catch (ConcurrentModificationException e) {
		}
		return false;
	}

	@Override
	protected boolean canDamagePlayer() {
		return false;
	}

	@Override
	protected void setSlimeSize(int size, boolean resetHealth) {
		this.dataManager.set(SLIME_SIZE, Integer.valueOf(size));
		this.setSize(0.51000005F * (float) size, 0.51000005F * (float) size);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
		this.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 100000, 4));

		if (resetHealth) {
			this.setHealth(this.getMaxHealth());
		}

		this.experienceValue = size;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("Size", this.getSlimeSize() - 1);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		int i = compound.getInteger("Size");

		if (i < 0) {
			i = 0;
		}

		this.setSlimeSize(i + 1, false);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_SMALL_SLIME_HURT;
	}

	@Override
	public SoundEvent getJumpSound() {
		return SoundEvents.ENTITY_SMALL_SLIME_JUMP;
	}

	@Override
	public int getSlimeSize() {
		return 1;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	protected EnumParticleTypes getParticleType() {
		return EnumParticleTypes.DRIP_LAVA;
	}

	@Override
	protected boolean canDropLoot() {
		return false;
	}

	@Override
	public void addPotionEffect(PotionEffect potionEffect) {
		if (!potionEffect.getPotion().isBadEffect()) {
			super.addPotionEffect(potionEffect);
		}
	}

	public boolean isHealingTarget() {
		return this.isHealing;
	}

	public void setHealingTarget(boolean bool) {
		this.isHealing = bool;
	}

	public boolean isBadReptibleBoi() {
		return this.isBadReptileBoi;
	}
	
	public boolean isEating() {
		return this.isEating;
	}
	
	public void setEating(boolean bool) {
		this.isEating = bool;
	}
	
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public static class MoveHelperSCP extends EntityMoveHelper {
		private float yRot;
		private int jumpDelay;
		private final Entity999 slime;

		public MoveHelperSCP(Entity999 slimeIn) {
			super(slimeIn);
			this.slime = slimeIn;
			this.yRot = 180.0F * slimeIn.rotationYaw / (float) Math.PI;
		}

		public void setDirection(float p_179920_1_) {
			this.yRot = p_179920_1_;
		}

		public void setSpeed(double speedIn) {
			this.speed = speedIn;
			this.action = EntityMoveHelper.Action.MOVE_TO;
		}

		public void onUpdateMoveHelper() {
			this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, this.yRot, 90.0F);
			this.entity.rotationYawHead = this.entity.rotationYaw;
			this.entity.renderYawOffset = this.entity.rotationYaw;

			if (this.action != EntityMoveHelper.Action.MOVE_TO) {
				this.entity.setMoveForward(0.0F);
			} else {
				this.action = EntityMoveHelper.Action.WAIT;

				if (this.entity.onGround) {
					this.entity.setAIMoveSpeed((float) (slime.isBadReptibleBoi() ? this.speed*1.2 : this.speed * this.entity
							.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));

					if (this.jumpDelay-- <= 0) {
						this.jumpDelay = this.slime.getJumpDelay();

						this.slime.getJumpHelper().setJumping();

						if (this.slime.makesSoundOnJump()) {
							this.slime.playSound(this.slime.getJumpSound(), this.slime.getSoundVolume(),
									((this.slime.getRNG().nextFloat() - this.slime.getRNG().nextFloat()) * 0.2F + 1.0F)
											* 0.8F);
						}
					} else {
						this.slime.moveStrafing = 0.0F;
						this.slime.moveForward = 0.0F;
						this.entity.setAIMoveSpeed(0.0F);
					}
				} else {
					this.entity.setAIMoveSpeed((float) (slime.isBadReptibleBoi() ? this.speed*1.2 : this.speed * this.entity
							.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
				}
			}
		}
	}
}