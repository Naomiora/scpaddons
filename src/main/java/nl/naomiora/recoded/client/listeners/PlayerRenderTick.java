package nl.naomiora.recoded.client.listeners;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import nl.naomiora.recoded.TempStorage.TempStorage;
import nl.naomiora.recoded.client.camera.Camera;
import nl.naomiora.recoded.potions.AdditionalPotions;

public class PlayerRenderTick {
	public static PlayerRenderTick instance;
	private static final ResourceLocation motionblur = new ResourceLocation("scpadgrace", "shaders/phosphor.json");
	private boolean loaded;
	private Camera camera;
	
	public PlayerRenderTick() {
		instance = this;
		this.loaded = false;
		this.camera = new Camera();
		MinecraftForge.EVENT_BUS.register(this);
	}
    
	@SubscribeEvent
	public void onHUDrender(RenderGameOverlayEvent.Pre render) {
		if (Minecraft.getMinecraft().player != null) {
			EntityPlayerSP player = Minecraft.getMinecraft().player;

			if (!player.isCreative()) {
				if(TempStorage.instance.cutSceneEnabled()) {
					if(!camera.enabled) {
						if(Minecraft.getMinecraft().gameSettings.thirdPersonView != 2) {
							Minecraft.getMinecraft().gameSettings.thirdPersonView = 2;
						}
						camera.enable();
					}
				} else {
					camera.disable();
				}
				
				if(Minecraft.getMinecraft().entityRenderer.isShaderActive()) {
					if (Minecraft.getMinecraft().entityRenderer.getShaderGroup().getShaderGroupName()
							.equalsIgnoreCase("scpadgrace:shaders/phosphor.json")) {
						if (!player.isPotionActive(AdditionalPotions.CONTROLLED)
								&& !player.isPotionActive(AdditionalPotions.FEAR)) {
							Minecraft.getMinecraft().entityRenderer.stopUseShader();
							this.loaded = false;
						}
					}
				}
				
				if (!Minecraft.getMinecraft().entityRenderer.isShaderActive() && (player.isPotionActive(AdditionalPotions.CONTROLLED)
							|| player.isPotionActive(AdditionalPotions.FEAR))) {
						if(!this.loaded) {
							this.loaded = true;
							Minecraft.getMinecraft().entityRenderer.loadShader(motionblur);
						}
				}

				if (player.isPotionActive(AdditionalPotions.FEAR)) {
					Random rand = new Random();
					player.rotationYaw += -0.05F + rand.nextFloat() * (0.05F - -0.05F);
					player.rotationPitch += -0.03F + rand.nextFloat() * (0.03F - -0.03F);
				}
				
				return;
			} 
			
			if (Minecraft.getMinecraft().entityRenderer.isShaderActive()) {
				if (Minecraft.getMinecraft().entityRenderer.getShaderGroup().getShaderGroupName()
						.equalsIgnoreCase("scpadgrace:shaders/phosphor.json")) {
					Minecraft.getMinecraft().entityRenderer.stopUseShader();
					this.loaded = false;
				}
			}
			
			if(camera.enabled) {
				camera.disable();
			}
		}
	}
	
    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        camera.update(event.phase == TickEvent.Phase.START);
    }

    @SubscribeEvent
    public void onCamera(EntityViewRenderEvent.CameraSetup event) {
        Minecraft mc = Minecraft.getMinecraft();
        Entity entity = event.getEntity();
        if (entity instanceof EntityLivingBase && ((EntityLivingBase)entity).isPlayerSleeping() || mc.gameSettings.thirdPersonView != 2 && !camera.enabled) {
            return;
        }
        float f = entity.getEyeHeight();
        double partialTicks = event.getRenderPartialTicks();
        double d0 = entity.prevPosX + (entity.posX - entity.prevPosX) * partialTicks;
        double d1 = entity.prevPosY + (entity.posY - entity.prevPosY) * partialTicks + (double)f;
        double d2 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * partialTicks;
        double d3 = this.camera.cameraDistance + 4.0f;
        float f1 = entity.rotationYaw;
        float f2 = entity.rotationPitch;
        double d4 = (double)(-MathHelper.sin((float)(f1 * 0.017453292f)) * MathHelper.cos((float)(f2 * 0.017453292f))) * d3;
        double d5 = (double)(MathHelper.cos((float)(f1 * 0.017453292f)) * MathHelper.cos((float)(f2 * 0.017453292f))) * d3;
        double d6 = (double)(-MathHelper.sin((float)(f2 * 0.017453292f))) * d3;
        for (int i = 0; i < 8; ++i) {
            RayTraceResult raytraceresult;
            double d7;
            float f3 = (i & 1) * 2 - 1;
            float f4 = (i >> 1 & 1) * 2 - 1;
            float f5 = (i >> 2 & 1) * 2 - 1;
            if ((raytraceresult = mc.world.rayTraceBlocks(new Vec3d(d0 + (double)(f3 *= 0.1f), d1 + (double)(f4 *= 0.1f), d2 + (double)(f5 *= 0.1f)), new Vec3d(d0 - d4 + (double)f3 + (double)f5, d1 - d6 + (double)f4, d2 - d5 + (double)f5))) == null || !((d7 = raytraceresult.hitVec.distanceTo(new Vec3d(d0, d1, d2))) < d3)) continue;
            d3 = d7;
        }
        GlStateManager.rotate((float)(entity.rotationPitch - f2), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.rotate((float)(entity.rotationYaw - f1), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.translate((float)0.0f, (float)0.0f, (float)((float)(-d3)));
        GlStateManager.rotate((float)(f1 - entity.rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)(f2 - entity.rotationPitch), (float)1.0f, (float)0.0f, (float)0.0f);
    }

	@SubscribeEvent
	public void mouseListener(PlayerInteractEvent.LeftClickBlock event) {
		if (TempStorage.instance.getFreezeKeyBoard() && event.isCancelable()) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void mouseListener(PlayerInteractEvent.EntityInteract event) {
		if (TempStorage.instance.getFreezeKeyBoard() && event.isCancelable()) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void mouseListener(PlayerInteractEvent.RightClickBlock event) {
		if (TempStorage.instance.getFreezeKeyBoard() && event.isCancelable()) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void mouseListener(PlayerInteractEvent.RightClickEmpty event) {
		if (TempStorage.instance.getFreezeKeyBoard() && event.isCancelable()) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void mouseListener(PlayerInteractEvent.RightClickItem event) {
		if (TempStorage.instance.getFreezeKeyBoard() && event.isCancelable()) {
			event.setCanceled(true);
		}
	}

	public Camera getCamera() {
		return this.camera;
	}

}