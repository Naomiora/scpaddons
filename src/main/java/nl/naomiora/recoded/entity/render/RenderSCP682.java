package nl.naomiora.recoded.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import nl.naomiora.recoded.entity.Entity682;
import nl.naomiora.recoded.entity.models.ModelSCP682;

public class RenderSCP682 extends RenderLiving<Entity682> {
	public static final ResourceLocation TEXTURES = new ResourceLocation("scpadgrace:textures/entity/scp682.png");

	public RenderSCP682(RenderManager manager) {
		super(manager, new ModelSCP682(), 0.7F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity682 entity) {
		return TEXTURES;
	}

	@Override
	protected void applyRotations(Entity682 entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
}
