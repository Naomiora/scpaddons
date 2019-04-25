package nl.naomiora.recoded.entity.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSCP999 extends ModelBase {
	public ModelRenderer shape2;
	public ModelRenderer shape2_1;
	public ModelRenderer shape1;
	public ModelRenderer shape2_2;
	public ModelRenderer shape5;
	public ModelRenderer shape5_1;
	public ModelRenderer shape7;
	public ModelRenderer shape7_1;

	public ModelSCP999() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.shape2_1 = new ModelRenderer(this, 36, 10);
		this.shape2_1.setRotationPoint(0.0F, -0.2F, 0.0F);
		this.shape2_1.addBox(-3.5F, -0.5F, -3.5F, 7, 1, 7, 0.0F);
		this.shape5_1 = new ModelRenderer(this, 20, 0);
		this.shape5_1.setRotationPoint(-2.3F, -4.3F, -1.5F);
		this.shape5_1.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(shape5_1, 0.0F, 0.0F, -0.18203784098300857F);
		this.shape5 = new ModelRenderer(this, 20, 0);
		this.shape5.setRotationPoint(2.3F, -4.3F, -1.5F);
		this.shape5.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(shape5, 0.0F, 0.0F, 0.18203784098300857F);
		this.shape7_1 = new ModelRenderer(this, 0, 11);
		this.shape7_1.setRotationPoint(-1.5F, -1.7F, 0.0F);
		this.shape7_1.addBox(-1.0F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(shape7_1, 0.0F, 0.0F, -0.5462880558742251F);
		this.shape2_2 = new ModelRenderer(this, 28, 0);
		this.shape2_2.setRotationPoint(0.0F, 0.2F, 0.0F);
		this.shape2_2.addBox(-4.5F, -0.3F, -4.5F, 9, 1, 9, 0.0F);
		this.shape2 = new ModelRenderer(this, 40, 18);
		this.shape2.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.shape2.addBox(-3.0F, -1.4F, -3.0F, 6, 1, 6, 0.0F);
		this.shape1 = new ModelRenderer(this, 0, 0);
		this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1.addBox(-2.5F, -5.0F, -2.5F, 5, 6, 5, 0.0F);
		this.shape7 = new ModelRenderer(this, 0, 11);
		this.shape7.setRotationPoint(1.5F, -1.7F, 0.0F);
		this.shape7.addBox(0.0F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(shape7, 0.0F, 0.0F, 0.5462880558742251F);
		this.shape2.addChild(this.shape2_1);
		this.shape1.addChild(this.shape5_1);
		this.shape1.addChild(this.shape5);
		this.shape1.addChild(this.shape7_1);
		this.shape2_1.addChild(this.shape2_2);
		this.shape2.addChild(this.shape1);
		this.shape1.addChild(this.shape7);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.shape2.render(f5);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}