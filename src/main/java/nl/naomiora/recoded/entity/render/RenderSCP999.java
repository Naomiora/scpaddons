package nl.naomiora.recoded.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import nl.naomiora.recoded.entity.Entity999;
import nl.naomiora.recoded.entity.models.ModelSCP999;

public class RenderSCP999 extends RenderLiving<Entity999> {
	public static final ResourceLocation TEXTURES = new ResourceLocation("scpadgrace:textures/entity/scp999.png");

	public RenderSCP999(RenderManager manager) {
		super(manager, new ModelSCP999(), 0.2F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity999 entity) {
		return TEXTURES;
	}

	@Override
	protected void applyRotations(Entity999 entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
}