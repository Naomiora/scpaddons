package nl.naomiora.recoded.client.camera;

import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class Camera {

	public boolean enabled = false;
	public float cameraYaw = 0.0f;
	public float cameraPitch = 0.0f;
	public float playerYaw = 0.0f;
	public float playerPitch = 0.0f;
	public float cameraDistance = -8.0f;

	public void update(boolean start) {
		Minecraft mc = Minecraft.getMinecraft();
		Entity view = mc.getRenderViewEntity();
		if (!this.enabled || view == null) {
			return;
		}
		mc.player.rotationPitch = 120;
		this.updateCamera();
		if (start) {
			view.rotationYaw = view.prevRotationYaw = this.cameraYaw;
			view.rotationPitch = view.prevRotationPitch = this.cameraPitch;
		} else {
			view.rotationYaw = mc.player.rotationYaw - this.cameraYaw + this.playerYaw;
			view.prevRotationYaw = mc.player.prevRotationYaw - this.cameraYaw + this.playerYaw;
			view.rotationPitch = -this.playerPitch;
			view.prevRotationPitch = -this.playerPitch;
		}
	}

	private void updateCamera() {
		Minecraft mc = Minecraft.getMinecraft();
		if (!mc.inGameHasFocus) {
			return;
		}
		float f = mc.gameSettings.mouseSensitivity * 0.6f + 0.2f;
		float f1 = f * f * f * 8.0f;
		double dx = (double) ((float) Mouse.getDX() * f1) * 0.15;
		double dy = (double) ((float) Mouse.getDY() * f1) * 0.15;
	    this.playerYaw = (float)((double)this.playerYaw + dx);
        this.playerPitch = (float)((double)this.playerPitch + dy);
        this.playerPitch = MathHelper.clamp((float)this.playerPitch, (float)-90.0f, (float)90.0f);
	}
	
	public void disable() {
		this.reset();
	}

	private void reset() {
		this.enabled = false;
		this.cameraYaw = 0.0f;
		this.cameraPitch = 0.0f;
		this.playerYaw = 0.0f;
		this.playerPitch = 0.0f;
		this.cameraDistance = -4.0f;
	}

	public void enable() {
		Minecraft mc = Minecraft.getMinecraft();
		if (!this.enabled) {
			this.cameraYaw = this.playerYaw = mc.player.rotationYaw;
			this.cameraPitch = 2;
			this.playerPitch = -this.cameraPitch;
			this.cameraDistance = -7.0F;
		}
		this.enabled = true;
	}
}