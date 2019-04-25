package nl.naomiora.recoded.ai.scp999;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import nl.naomiora.recoded.Main;
import nl.naomiora.recoded.entity.Entity999;
import nl.naomiora.recoded.network.NetworkHandler;
import nl.naomiora.recoded.network.packets.SyncPotions;
import nl.naomiora.recoded.potions.AdditionalPotions;

public class AIPositive extends EntityAIBase {
	private final Entity999 entity;
	private EntityPlayer target;
	private int executeWait;

	public AIPositive(Entity999 entity) {
		this.entity = entity;
		this.executeWait = 100;
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public void updateTask() {
		try {
			for (Iterator<Entity> iter = this.entity.world
					.getEntitiesInAABBexcluding(this.entity,
							new AxisAlignedBB(this.entity.posX + 10, this.entity.posY + 2, this.entity.posZ + 10,
									this.entity.posX - 10, this.entity.posY - 2, this.entity.posZ - 10),
							null)
					.iterator(); iter.hasNext();) {
				Entity entityCheck = iter.next();
				if (entityCheck instanceof EntityPlayer) {
					EntityPlayer entity = (EntityPlayer) entityCheck;
					if (entity.getActivePotionEffect(AdditionalPotions.HAPPINESS) == null) {
						entity.addPotionEffect(new PotionEffect(AdditionalPotions.HAPPINESS, Integer.MAX_VALUE, 0));
						NetworkHandler.INSTANCE
								.sendTo(new SyncPotions(Main.instance.getIdByPotion(AdditionalPotions.HAPPINESS),
										Integer.MAX_VALUE, 0, true), (EntityPlayerMP) entity);
					}
				}
			}
		} catch (ConcurrentModificationException e) {
		}

		if(this.entity.getAttackTarget() instanceof EntityPlayer) {
			this.target = (EntityPlayer) this.entity.getAttackTarget();
			if (this.target != null) {
				if (this.entity.getAttackTarget().getDistanceSq(this.entity) < 4D) {
					this.entity.setHealingTarget(true);
					if (this.executeWait == 0) {
						this.executeWait = 20;
	
						if (this.hasBadEffect(this.target)) {
							this.removeBadEffects(this.target);
						} else if (this.target.getHealth() < this.target.getMaxHealth()) {
							this.target.setHealth(this.target.getHealth() + 0.5F);
						}
	
						if (this.target.getHealth() == this.target.getMaxHealth() && !this.hasBadEffect(this.target)) {
							this.entity.setAttackTarget(null);
							this.target = null;
							this.entity.setHealingTarget(false);
							this.executeWait = 100;
						}
					} else {
						this.executeWait = this.executeWait - 1;
					}
				} else {
					this.entity.setHealingTarget(false);
				}
			} else {
				this.entity.setHealingTarget(false);
			}
		}
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

	public void removeBadEffects(EntityPlayer player) {
		try {
			for (PotionEffect effect : player.getActivePotionEffects()) {
				if (effect.getPotion().isBadEffect() && !effect.getPotion().equals(AdditionalPotions.CONTROLLED)) {
					player.removePotionEffect(effect.getPotion());
					NetworkHandler.INSTANCE.sendTo(
							new SyncPotions(Main.instance.getIdByPotion(effect.getPotion()), 0, 0, false),
							(EntityPlayerMP) player);
				}
			}
		} catch (ConcurrentModificationException e) {
		}
	}
}