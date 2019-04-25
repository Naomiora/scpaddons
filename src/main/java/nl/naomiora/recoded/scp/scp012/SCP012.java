package nl.naomiora.recoded.scp.scp012;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import nl.naomiora.recoded.Main;
import nl.naomiora.recoded.TempStorage.TempStorage;
import nl.naomiora.recoded.damage.AdditionalDamageSources;
import nl.naomiora.recoded.items.AdditionalItems;
import nl.naomiora.recoded.network.NetworkHandler;
import nl.naomiora.recoded.network.packets.Freeeezeeeee;
import nl.naomiora.recoded.network.packets.LookAtIt;
import nl.naomiora.recoded.network.packets.SyncPotions;
import nl.naomiora.recoded.network.packets.MovementSync;
import nl.naomiora.recoded.potions.AdditionalPotions;
import nl.naomiora.recoded.scp.Range;

public class SCP012 {

	public void check() {
		try {
			if (FMLCommonHandler.instance().getMinecraftServerInstance().getServer().worlds != null) {
				for (World world : FMLCommonHandler.instance().getMinecraftServerInstance().getServer().worlds) {
					if (world.loadedEntityList != null) {
						for (Iterator<Entity> iter = world.loadedEntityList.iterator(); iter.hasNext();) {
							Entity entity = iter.next();
							if (entity instanceof EntityPlayer || entity instanceof EntityLiving) {
								if (!(entity instanceof EntityZombie || entity instanceof EntitySkeleton
										|| entity instanceof EntitySlime)) {
									Range range = this.SCPInRange(entity);

									if (range.equals(Range.CLOSE)) {
										this.freezeEntity(entity, this.getScpInRange(entity));
										entity.attackEntityFrom(AdditionalDamageSources.scp012, 0.5F);
										this.stopMovement(entity);
									} else if (range.equals(Range.MID)) {
										Entity item = this.getScpInRange(entity);
										this.freezeEntity(entity, this.getScpInRange(entity));
										this.pullEntityToEntity(item, entity, 0.1F);
									} else if (range.equals(Range.FREEZE)) {
										Entity item = this.getScpInRange(entity);
										this.freezeEntity(entity, this.getScpInRange(entity));
										this.pullEntityToEntity(item, entity, 0.2F);
										this.stopMovement(entity);
									} else {
										this.unFreezeEntity(entity, false);
									}

									if (entity instanceof EntityPlayer) {
										EntityPlayer player = (EntityPlayer) entity;
										if (!player.isCreative()
												&& player.world.getLightFromNeighbors(player.getPosition()) > 11) {
											for (ItemStack itemStack : player.inventoryContainer.inventoryItemStacks) {
												if (AdditionalItems.getLoadedItem("scp_012") != null && itemStack
														.getItem().equals(AdditionalItems.getLoadedItem("scp_012"))) {
													this.freezeEntity(entity, this.getScpInRange(entity));
													entity.attackEntityFrom(AdditionalDamageSources.scp012, 0.5F);
												}
											}

											if (TempStorage.instance.saidItAlready(player.getUniqueID())) {
												this.stopMovement(player);
											}
										} else {
											if(TempStorage.instance.saidItAlready(player.getUniqueID()) && player.isCreative()) {
												TempStorage.instance.removeFromSaidIt(player.getUniqueID());
											}
											if(!TempStorage.instance.saidItAlready(player.getUniqueID()) || player.isCreative()) {
												this.unFreezeEntity(entity, true);
											}
										}
									}
								}
							}

						}
					}
				}
			}
		} catch (ConcurrentModificationException | NoSuchElementException e) {
		}
	}

	public Entity getScpInRange(Entity entity) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if (!player.isCreative() && player.world.getLightFromNeighbors(player.getPosition()) > 11) {
				for (ItemStack itemStack : player.inventoryContainer.inventoryItemStacks) {
					if (AdditionalItems.getLoadedItem("scp_012") != null
							&& itemStack.getItem().equals(AdditionalItems.getLoadedItem("scp_012"))) {
						return entity;
					}
				}
			}
		}

		try {
			for (Iterator<Entity> iterRange = entity.world
					.getEntitiesInAABBexcluding(entity, new AxisAlignedBB(entity.posX + 8, entity.posY + 2,
							entity.posZ + 8, entity.posX - 8, entity.posY - 2, entity.posZ - 8), null)
					.iterator(); iterRange.hasNext();) {
				Entity entityInRange = iterRange.next();
				if (entityInRange instanceof EntityItem) {
					EntityItem item = (EntityItem) entityInRange;
					if (item.getItem().getItem().equals(AdditionalItems.scp012)) {
						if (item.world.getLightFromNeighbors(item.getPosition()) > 11) {
							return item;
						}
					}
				}

				if (entityInRange instanceof EntityPlayer) {
					EntityPlayer maybeHolding012 = (EntityPlayer) entityInRange;
					if (has012(maybeHolding012)) {
						if (maybeHolding012.world.getLightFromNeighbors(maybeHolding012.getPosition()) > 11) {
							return maybeHolding012;
						}
					}
				}
			}
		} catch (ConcurrentModificationException | NullPointerException | NoSuchElementException e) {
			System.out.println("exception at second check.");
		}
		return null;
	}

	public Range SCPInRange(Entity entity) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if (!player.isCreative() && player.world.getLightFromNeighbors(player.getPosition()) > 11) {
				for (ItemStack itemStack : player.inventoryContainer.inventoryItemStacks) {
					if (AdditionalItems.getLoadedItem("scp_012") != null
							&& itemStack.getItem().equals(AdditionalItems.getLoadedItem("scp_012"))) {
						return Range.CLOSE;
					}
				}
			}
		}

		try {
			for (Iterator<Entity> iterRange = entity.world
					.getEntitiesInAABBexcluding(entity, new AxisAlignedBB(entity.posX + 8, entity.posY + 2,
							entity.posZ + 8, entity.posX - 8, entity.posY - 2, entity.posZ - 8), null)
					.iterator(); iterRange.hasNext();) {
				Entity entityInRange = iterRange.next();
				if (entityInRange instanceof EntityItem) {
					EntityItem item = (EntityItem) entityInRange;

					if (item.getItem().getItem().equals(AdditionalItems.scp012)) {
						if (item.world.getLightFromNeighbors(item.getPosition()) > 11) {
							if (entity.getDistanceSq(item) < 5.0 * 5.0) {
								if (entity.getDistanceSq(item) < 1.5 * 1.5) {
									return Range.CLOSE;
								}
								return Range.FREEZE;
							}
							return Range.MID;
						}
					}
				}

				if (entityInRange instanceof EntityPlayer) {
					EntityPlayer maybeHolding012 = (EntityPlayer) entityInRange;
					if (has012(maybeHolding012)) {
						if (maybeHolding012.world.getLightFromNeighbors(maybeHolding012.getPosition()) > 11) {
							if (entity.getDistanceSq(maybeHolding012) < 5.0 * 5.0) {
								if (entity.getDistanceSq(maybeHolding012) < 1.5 * 1.5) {
									return Range.CLOSE;
								}
								return Range.FREEZE;
							}
							return Range.MID;
						}
					}
				}
			}
		} catch (ConcurrentModificationException | NullPointerException | NoSuchElementException e) {
		}
		return Range.FAR;
	}

	public boolean has012(EntityPlayer maybeHolding012) {
		for (ItemStack item : maybeHolding012.inventoryContainer.inventoryItemStacks) {
			if (item.getItem().equals(AdditionalItems.scp012)) {
				return true;
			}
		}
		return false;
	}

	public void freezeEntity(Entity entity, Entity entity2) {
		if (entity instanceof EntityPlayerMP) {
			if (((EntityPlayerMP) entity).isCreative()) {
				return;
			}

			EntityPlayerMP player = (EntityPlayerMP) entity;
			if (entity2 != null && !entity2.equals(player)) {
				double d0 = player.posX - entity2.posX;
				double d1 = player.posY - (entity2.posY + entity2.getEyeHeight());
				double d2 = player.posZ - entity2.posZ;
				double d3 = MathHelper.sqrt((double) (d0 * d0 + d2 * d2));
				float angleYaw = (float) (MathHelper.atan2((double) d2, (double) d0) * 57.29577951308232) - 90.0f;
				float anglePitch = (float) (-(MathHelper.atan2((double) d1, (double) d3) * 57.29577951308232));
				float pitch = MathHelper.wrapDegrees(anglePitch);
				player.rotationPitch = pitch > 160 ? pitch + 20 : pitch;
				player.rotationYaw = MathHelper.wrapDegrees(angleYaw) + 180.0f;
				NetworkHandler.INSTANCE.sendTo(new LookAtIt(player.rotationYaw, player.rotationPitch), player);
			}

			NetworkHandler.INSTANCE.sendTo(
					new SyncPotions(Main.instance.getIdByPotion(AdditionalPotions.CONTROLLED), 20, 0, true), player);
			((EntityPlayerMP) entity).addPotionEffect(new PotionEffect(AdditionalPotions.CONTROLLED, 20, 0));
			return;
		}

		if (entity instanceof EntityLiving) {
			((EntityLiving) entity).addPotionEffect(new PotionEffect(Potion.getPotionById(2), 100000, 255));
		}
	}

	public void unFreezeEntity(Entity entity, boolean packet) {
		if (entity instanceof EntityPlayerMP && packet) {
			((EntityPlayerMP) entity).removePotionEffect(AdditionalPotions.CONTROLLED);
			NetworkHandler.INSTANCE.sendTo(new Freeeezeeeee(false), (EntityPlayerMP) entity);
			NetworkHandler.INSTANCE.sendTo(
					new SyncPotions(Main.instance.getIdByPotion(AdditionalPotions.CONTROLLED), 0, 0, false),
					(EntityPlayerMP) entity);
			return;
		}

		if (entity instanceof EntityLiving) {
			((EntityLiving) entity).removeActivePotionEffect(Potion.getPotionById(2));
		}
	}

	public void stopMovement(Entity entity) {
		if (entity instanceof EntityPlayerMP) {
			NetworkHandler.INSTANCE.sendTo(new Freeeezeeeee(true), (EntityPlayerMP) entity);
		}
	}

	public void pullEntityToEntity(Entity entity, Entity secondEntity, double moveFactor) {
		try {
			if (secondEntity instanceof EntityLiving) {
				((EntityLiving) secondEntity).getMoveHelper().setMoveTo(entity.posX, entity.posY, entity.posZ, 0.06F);
			}

			double distX = entity.posX - secondEntity.posX;
			double distZ = entity.posZ - secondEntity.posZ;
			double dir = MathHelper.atan2(distZ, distX);
			double speed = 1f / secondEntity.getDistance(entity.posX, entity.posY, entity.posZ) * moveFactor;

			if (secondEntity instanceof EntityPlayerMP) {
				if (((EntityPlayerMP) secondEntity).isCreative()) {
					return;
				}
				if (((EntityPlayerMP) secondEntity).isSpectator()) {
					return;
				}

				NetworkHandler.INSTANCE.sendTo(
						new MovementSync(MathHelper.cos((float) dir) * speed, MathHelper.sin((float) dir) * speed),
						(EntityPlayerMP) secondEntity);
				return;
			}

			secondEntity.motionX = MathHelper.cos((float) dir) * speed;
			secondEntity.motionZ = MathHelper.sin((float) dir) * speed;
			secondEntity.onUpdate();
		} catch (NullPointerException e) {
		}
	}
}