package nl.naomiora.recoded.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import nl.naomiora.recoded.entity.Entity2521;
import nl.naomiora.recoded.entity.Entity999;
import nl.naomiora.recoded.entity.models.ModelSCP2521;
import nl.naomiora.recoded.entity.models.ModelSCP999;

public class RenderSCP2521 extends RenderLiving<Entity2521> {
	public static final ResourceLocation TEXTURES = new ResourceLocation("scpadgrace:textures/entity/scp2521.png");

	public RenderSCP2521(RenderManager manager) {
		super(manager, new ModelSCP2521(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity2521 entity) {
		return TEXTURES;
	}

	@Override
	protected void applyRotations(Entity2521 entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
}