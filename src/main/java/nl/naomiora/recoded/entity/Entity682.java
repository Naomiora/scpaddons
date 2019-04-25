package nl.naomiora.recoded.entity;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import nl.naomiora.recoded.ai.scp682.AIFaceTarget;
import nl.naomiora.recoded.ai.scp682.AISwimming;
import nl.naomiora.recoded.ai.scp682.EntityAINearestAttackableTarget;
import nl.naomiora.recoded.ai.scp682.EntityWander;
import nl.naomiora.recoded.network.NetworkHandler;
import nl.naomiora.recoded.network.packets.PlaySound;
import nl.naomiora.recoded.potions.AdditionalPotions;
import nl.naomiora.recoded.registration.SoundRegistration;

public class Entity682 extends EntityCreature implements IMob {
	private int heal;
	public float biteProgress;
	public int delay;
	private static final DataParameter<Boolean> IS_BITING = EntityDataManager.createKey(Entity682.class,
			(DataSerializer) DataSerializers.BOOLEAN);

	public Entity682(World worldIn) {
		super(worldIn);
		this.setSize(1.5F, 2.4F);
		this.heal = 40;
		this.delay = 0;
		this.isImmuneToFire = true;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new AISwimming(this));
		this.tasks.addTask(1, new AIFaceTarget(this));
		this.tasks.addTask(3, new EntityWander(this));
		this.targetTasks.addTask(1,
				new EntityAINearestAttackableTarget(this, EntityLivingBase.class, true, false, null));
	}
	
	@Override
	public void setAttackTarget(EntityLivingBase entitylivingbaseIn) {
		if (this.getActivePotionEffect(AdditionalPotions.HAPPINESS) != null) {
			super.setAttackTarget(null);
			return;
		}
		if (((this.getHealth() / this.getMaxHealth()) * 100) <= 5) {
			super.setAttackTarget(null);
			return;
		}
		super.setAttackTarget(entitylivingbaseIn);
	}

	@Override
	public EntityLivingBase getAttackTarget() {
		if (this.getActivePotionEffect(AdditionalPotions.HAPPINESS) != null) {
			return null;
		}
		if (((this.getHealth() / this.getMaxHealth()) * 100) <= 5) {
			return null;
		}
		return super.getAttackTarget();
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(IS_BITING, false);
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}
	
	@Override
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {
		super.knockBack(entityIn, this.getStrength(strength), xRatio, zRatio);
	}

	@Override
	public boolean canBeLeashedTo(EntityPlayer player) {
		return ((this.getHealth() / this.getMaxHealth()) * 100) <= 5;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.getActivePotionEffect(AdditionalPotions.HAPPINESS) != null) {
			if (!this.slimeInRange(10D)) {
				this.removePotionEffect(AdditionalPotions.HAPPINESS);
			}
		} else if (this.getAttackTarget() != null) {
			this.bite();
		}

		if (this.getHealth() != this.getMaxHealth()) {
			if (this.heal-- <= 0) {
				this.heal = 40;
				this.setHealth(
						this.getHealth() + 2 < this.getMaxHealth() ? this.getHealth() + 2 : this.getHealth() + 1);
			}
		}
	}
	
	public boolean slimeInRange(double range) {
		try {
			for (Iterator<Entity> iter = this.world
					.getEntitiesWithinAABBExcludingEntity(this,
							new AxisAlignedBB(this.posX + range, this.posY + range, this.posZ + range,
									this.posX - range, this.posY - range, this.posZ - range))
					.iterator(); iter.hasNext();) {
				Entity entity = iter.next();
				if (entity instanceof Entity999) {
					return true;
				}
			}
		} catch (ConcurrentModificationException | NullPointerException | NoSuchElementException e) {
			System.out.println("exception at scp682 check.");
		}
		return false;
	}

	public float getStrength(float strength) {
		return (1 - (this.getHealth() / this.getMaxHealth())) * strength;
	}

	@Override
	protected void damageEntity(DamageSource damageSrc, float damageAmount) {
		super.damageEntity(damageSrc, this.getHealth() - (damageAmount / 100) <= 0 ? this.getHealth() - 1 : damageAmount / 100);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(500D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(128D);
	}

	@Override
	public void onUpdate() {
		if (this.getIsBiting()) {
			this.biteProgress += 0.1f;
			if (this.biteProgress == 0.4f) {
				NetworkHandler.INSTANCE.sendToAllAround(
						new PlaySound(this.getPosition(), SoundRegistration.SCP682_JAW_SNAP),
						new TargetPoint(this.dimension, this.posX, this.posY, this.posZ, 32D));
				if (this.getAttackTarget() != null) {
					if (this.getAttackTarget().getDistanceSq(this) < 4D * 4D) {
						this.getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this), this.getDamage());
					}
				}
			}
			if (this.biteProgress > 0.6f) {
				this.setBiting(false);
				this.biteProgress = 0.0f;
			}
		}
		super.onUpdate();
	}

	public float getDamage() {
		double hp = (this.getHealth() / this.getMaxHealth()) * 100;
		if (hp < 10) {
			return 18F;
		} else if (hp < 15) {
			return 35F;
		} else if (hp < 20) {
			return 40F;
		} else if (hp < 35) {
			return 35F;
		} else if (hp < 50) {
			return 30F;
		} else if (hp < 75) {
			return 25F;
		} else if (hp < 90) {
			return 20F;
		}
		return 18F;
	}

	public void setBiting(boolean flag) {
		this.dataManager.set(IS_BITING, flag);
	}

	public boolean getIsBiting() {
		return (Boolean) this.dataManager.get(IS_BITING);
	}

	public void bite() {
		if (!this.getIsBiting()) {
			if (this.delay-- <= 0) {
				this.setBiting(true);
				this.biteProgress = 0.0f;
				this.delay = 30;
			}
		}
	}

	public float getSpeed() {
		double hp = (this.getHealth() / this.getMaxHealth()) * 100;
		if (hp <= 1) {
			return 0.0F;
		} else if (hp < 5) {
			return 0.3F;
		} else if (hp < 10) {
			return 0.5F;
		} else if (hp < 15) {
			return 0.7F;
		} else if (hp < 20) {
			return 1.0F;
		} else if (hp < 35) {
			return 0.9F;
		} else if (hp < 50) {
			return 0.85F;
		} else if (hp < 75) {
			return 0.75F;
		} else if (hp < 90) {
			return 0.65F;
		}
		return 0.6F;
	}
}