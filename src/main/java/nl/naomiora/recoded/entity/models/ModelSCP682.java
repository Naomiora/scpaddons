package nl.naomiora.recoded.entity.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.MathHelper;
import nl.naomiora.recoded.entity.Entity682;

public class ModelSCP682 extends ModelBase {
	public ModelRenderer body;
	public ModelRenderer neck;
	public ModelRenderer back;
	public ModelRenderer spine3;
	public ModelRenderer spine4;
	public ModelRenderer spine5;
	public ModelRenderer head;
	public ModelRenderer legfrontleft;
	public ModelRenderer legbackleft;
	public ModelRenderer legbackright;
	public ModelRenderer legfrontright;
	public ModelRenderer spine1;
	public ModelRenderer spine2;
	public ModelRenderer tailpart1;
	public ModelRenderer spine6;
	public ModelRenderer tailpart2;
	public ModelRenderer spine7;
	public ModelRenderer spine8;
	public ModelRenderer tailpart3;
	public ModelRenderer spine9;
	public ModelRenderer tailpart4;
	public ModelRenderer spine10;
	public ModelRenderer tailpart5;
	public ModelRenderer spine11;
	public ModelRenderer tailpart6;
	public ModelRenderer spine12;
	public ModelRenderer spine13;
	public ModelRenderer spine14;
	public ModelRenderer snout;
	public ModelRenderer jaw;
	public ModelRenderer clawfl1;
	public ModelRenderer clawfl2;
	public ModelRenderer clawfl3;
	public ModelRenderer clawbl1;
	public ModelRenderer clawbl3;
	public ModelRenderer clawbl2;
	public ModelRenderer clawbr1;
	public ModelRenderer clawbr3;
	public ModelRenderer clawbr2;
	public ModelRenderer clawfr1;
	public ModelRenderer clawfr3;
	public ModelRenderer clawfr2;
	public float biteProgress;
	public boolean swimming;

	public ModelSCP682() {
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.spine11 = new ModelRenderer(this, 30, 0);
		this.spine11.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.spine11.addBox(-1.5F, -6.0F, 2.0F, 3, 5, 3, 0.0F);
		this.spine13 = new ModelRenderer(this, 30, 0);
		this.spine13.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.spine13.addBox(-1.5F, -6.0F, 2.0F, 3, 5, 3, 0.0F);
		this.spine6 = new ModelRenderer(this, 0, 0);
		this.spine6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.spine6.addBox(-1.5F, -10.5F, -1.0F, 3, 10, 3, 0.0F);
		this.tailpart2 = new ModelRenderer(this, 88, 20);
		this.tailpart2.setRotationPoint(0.0F, 0.0F, 9.0F);
		this.tailpart2.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 8, 0.0F);
		this.snout = new ModelRenderer(this, 40, 32);
		this.snout.setRotationPoint(0.0F, 0.0F, -7.0F);
		this.snout.addBox(-2.5F, -4.0F, -16.0F, 5, 6, 16, 0.0F);
		this.clawfl3 = new ModelRenderer(this, 48, 24);
		this.clawfl3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.clawfl3.addBox(2.0F, 13.0F, -5.0F, 2, 2, 5, 0.0F);
		this.jaw = new ModelRenderer(this, 0, 20);
		this.jaw.setRotationPoint(0.0F, 0.0F, -7.0F);
		this.jaw.addBox(-2.0F, 0.0F, -15.5F, 4, 5, 16, 0.0F);
		this.legfrontleft = new ModelRenderer(this, 48, 0);
		this.legfrontleft.setRotationPoint(6.0F, 1.0F, -2.5F);
		this.legfrontleft.addBox(0.0F, 0.0F, 0.0F, 6, 15, 6, 0.0F);
		this.spine1 = new ModelRenderer(this, 0, 0);
		this.spine1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.spine1.addBox(-1.5F, -8.0F, -10.0F, 3, 10, 3, 0.0F);
		this.head = new ModelRenderer(this, 0, 46);
		this.head.setRotationPoint(0.0F, 0.0F, -12.0F);
		this.head.addBox(-5.0F, -5.0F, -8.0F, 10, 10, 8, 0.0F);
		this.legbackleft = new ModelRenderer(this, 48, 0);
		this.legbackleft.setRotationPoint(6.0F, 1.0F, 20.0F);
		this.legbackleft.addBox(0.0F, 0.0F, 0.0F, 6, 15, 6, 0.0F);
		this.clawbl1 = new ModelRenderer(this, 48, 24);
		this.clawbl1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.clawbl1.addBox(0.0F, 13.0F, -3.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(clawbl1, 0.0F, 0.5235987755982988F, 0.0F);
		this.tailpart6 = new ModelRenderer(this, 88, 36);
		this.tailpart6.setRotationPoint(0.0F, 0.0F, 7.0F);
		this.tailpart6.addBox(-3.5F, -3.5F, 0.0F, 7, 7, 7, 0.0F);
		this.spine10 = new ModelRenderer(this, 30, 0);
		this.spine10.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.spine10.addBox(-1.5F, -6.0F, 2.0F, 3, 5, 3, 0.0F);
		this.back = new ModelRenderer(this, 0, 66);
		this.back.setRotationPoint(0.0F, 0.0F, 24.0F);
		this.back.addBox(-7.5F, -6.0F, 0.0F, 15, 12, 8, 0.0F);
		this.spine8 = new ModelRenderer(this, 0, 0);
		this.spine8.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.spine8.addBox(-1.5F, -8.0F, 5.5F, 3, 10, 3, 0.0F);
		this.clawbr1 = new ModelRenderer(this, 48, 24);
		this.clawbr1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.clawbr1.addBox(-5.0F, 13.0F, -6.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(clawbr1, 0.0F, 0.5235987755982988F, 0.0F);
		this.spine4 = new ModelRenderer(this, 0, 0);
		this.spine4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.spine4.addBox(-1.5F, -12.0F, 9.5F, 3, 10, 3, 0.0F);
		this.spine5 = new ModelRenderer(this, 0, 0);
		this.spine5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.spine5.addBox(-1.5F, -13.0F, 16.0F, 3, 10, 3, 0.0F);
		this.clawfr3 = new ModelRenderer(this, 48, 24);
		this.clawfr3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.clawfr3.addBox(-4.0F, 13.0F, -5.0F, 2, 2, 5, 0.0F);
		this.spine2 = new ModelRenderer(this, 0, 0);
		this.spine2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.spine2.addBox(-1.5F, -9.5F, -3.5F, 3, 10, 3, 0.0F);
		this.neck = new ModelRenderer(this, 0, 86);
		this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neck.addBox(-7.5F, -6.0F, -12.0F, 15, 12, 12, 0.0F);
		this.tailpart5 = new ModelRenderer(this, 88, 36);
		this.tailpart5.setRotationPoint(0.0F, 0.0F, 7.0F);
		this.tailpart5.addBox(-3.5F, -3.5F, 0.0F, 7, 7, 7, 0.0F);
		this.clawfl2 = new ModelRenderer(this, 48, 24);
		this.clawfl2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.clawfl2.addBox(3.0F, 13.0F, -6.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(clawfl2, 0.0F, -0.5235987755982988F, 0.0F);
		this.clawbl2 = new ModelRenderer(this, 48, 24);
		this.clawbl2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.clawbl2.addBox(3.0F, 13.0F, -6.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(clawbl2, 0.0F, -0.5235987755982988F, 0.0F);
		this.clawfr1 = new ModelRenderer(this, 48, 24);
		this.clawfr1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.clawfr1.addBox(-5.0F, 13.0F, -6.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(clawfr1, 0.0F, 0.5235987755982988F, 0.0F);
		this.body = new ModelRenderer(this, 40, 88);
		this.body.setRotationPoint(0.0F, 0.0F, -6.0F);
		this.body.addBox(-8.5F, -7.0F, 0.0F, 17, 14, 24, 0.0F);
		this.clawbr2 = new ModelRenderer(this, 48, 24);
		this.clawbr2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.clawbr2.addBox(-2.0F, 13.0F, -3.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(clawbr2, 0.0F, -0.5235987755982988F, 0.0F);
		this.spine3 = new ModelRenderer(this, 0, 0);
		this.spine3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.spine3.addBox(-1.5F, -10.5F, 3.0F, 3, 10, 3, 0.0F);
		this.spine7 = new ModelRenderer(this, 0, 0);
		this.spine7.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.spine7.addBox(-1.5F, -8.5F, -1.5F, 3, 10, 3, 0.0F);
		this.legfrontright = new ModelRenderer(this, 48, 0);
		this.legfrontright.setRotationPoint(-6.0F, 1.0F, -2.5F);
		this.legfrontright.addBox(-6.0F, 0.0F, 0.0F, 6, 15, 6, 0.0F);
		this.legbackright = new ModelRenderer(this, 48, 0);
		this.legbackright.setRotationPoint(-6.0F, 1.0F, 20.0F);
		this.legbackright.addBox(-6.0F, 0.0F, 0.0F, 6, 15, 6, 0.0F);
		this.clawfl1 = new ModelRenderer(this, 48, 24);
		this.clawfl1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.clawfl1.addBox(0.0F, 13.0F, -3.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(clawfl1, 0.0F, 0.5235987755982988F, 0.0F);
		this.clawbl3 = new ModelRenderer(this, 48, 24);
		this.clawbl3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.clawbl3.addBox(2.0F, 13.0F, -5.0F, 2, 2, 5, 0.0F);
		this.clawfr2 = new ModelRenderer(this, 48, 24);
		this.clawfr2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.clawfr2.addBox(-2.0F, 13.0F, -3.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(clawfr2, 0.0F, -0.5235987755982988F, 0.0F);
		this.spine12 = new ModelRenderer(this, 30, 0);
		this.spine12.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.spine12.addBox(-1.5F, -6.0F, 2.0F, 3, 5, 3, 0.0F);
		this.tailpart3 = new ModelRenderer(this, 88, 36);
		this.tailpart3.setRotationPoint(0.0F, 0.0F, 8.0F);
		this.tailpart3.addBox(-3.5F, -3.5F, 0.0F, 7, 7, 7, 0.0F);
		this.tailpart4 = new ModelRenderer(this, 88, 36);
		this.tailpart4.setRotationPoint(0.0F, 0.0F, 7.0F);
		this.tailpart4.addBox(-3.5F, -3.5F, 0.0F, 7, 7, 7, 0.0F);
		this.tailpart1 = new ModelRenderer(this, 88, 0);
		this.tailpart1.setRotationPoint(0.0F, 0.0F, 8.0F);
		this.tailpart1.addBox(-4.5F, -4.5F, 0.0F, 9, 9, 9, 0.0F);
		this.spine9 = new ModelRenderer(this, 30, 0);
		this.spine9.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.spine9.addBox(-1.5F, -7.0F, 2.5F, 3, 5, 3, 0.0F);
		this.clawbr3 = new ModelRenderer(this, 48, 24);
		this.clawbr3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.clawbr3.addBox(-4.0F, 13.0F, -5.0F, 2, 2, 5, 0.0F);
		this.spine14 = new ModelRenderer(this, 0, 0);
		this.spine14.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.spine14.addBox(-1.5F, -13.0F, -1.0F, 3, 8, 3, 0.0F);
		this.setRotateAngle(spine14, -1.3962634015954636F, 0.0F, 0.0F);
		this.tailpart4.addChild(this.spine11);
		this.tailpart6.addChild(this.spine13);
		this.back.addChild(this.spine6);
		this.tailpart1.addChild(this.tailpart2);
		this.head.addChild(this.snout);
		this.legfrontleft.addChild(this.clawfl3);
		this.head.addChild(this.jaw);
		this.body.addChild(this.legfrontleft);
		this.neck.addChild(this.spine1);
		this.body.addChild(this.head);
		this.body.addChild(this.legbackleft);
		this.legbackleft.addChild(this.clawbl1);
		this.tailpart5.addChild(this.tailpart6);
		this.tailpart3.addChild(this.spine10);
		this.body.addChild(this.back);
		this.tailpart1.addChild(this.spine8);
		this.legbackright.addChild(this.clawbr1);
		this.body.addChild(this.spine4);
		this.body.addChild(this.spine5);
		this.legfrontright.addChild(this.clawfr3);
		this.neck.addChild(this.spine2);
		this.body.addChild(this.neck);
		this.tailpart4.addChild(this.tailpart5);
		this.legfrontleft.addChild(this.clawfl2);
		this.legbackleft.addChild(this.clawbl2);
		this.legfrontright.addChild(this.clawfr1);
		this.legbackright.addChild(this.clawbr2);
		this.body.addChild(this.spine3);
		this.tailpart1.addChild(this.spine7);
		this.body.addChild(this.legfrontright);
		this.body.addChild(this.legbackright);
		this.legfrontleft.addChild(this.clawfl1);
		this.legbackleft.addChild(this.clawbl3);
		this.legfrontright.addChild(this.clawfr2);
		this.tailpart5.addChild(this.spine12);
		this.tailpart2.addChild(this.tailpart3);
		this.tailpart3.addChild(this.tailpart4);
		this.back.addChild(this.tailpart1);
		this.tailpart2.addChild(this.spine9);
		this.legbackright.addChild(this.clawbr3);
		this.tailpart6.addChild(this.spine14);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(this.body.offsetX, this.body.offsetY, this.body.offsetZ);
		GlStateManager.translate(this.body.rotationPointX * f5, this.body.rotationPointY * f5,
				this.body.rotationPointZ * f5);
		GlStateManager.scale(1.5D, 1.5D, 1.5D);
		GlStateManager.translate(-this.body.offsetX, -this.body.offsetY, -this.body.offsetZ);
		GlStateManager.translate(-this.body.rotationPointX * f5, -this.body.rotationPointY * f5,
				-this.body.rotationPointZ * f5);
		this.body.render(f5);
		GlStateManager.popMatrix();
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		float f25;
		this.head.rotateAngleX = headPitch / 57.29578f;
		this.head.rotateAngleY = netHeadYaw / 57.29578f;
		if (entityIn instanceof EntityLiving) {
			EntityLiving living = (EntityLiving) entityIn;
			double hp = (living.getHealth() / living.getMaxHealth()) * 100;
			if (hp <= 15) {
				this.tailpart6.isHidden = true;
				if (hp <= 10) {
					this.tailpart5.isHidden = true;
					this.tailpart4.isHidden = true;
					if (hp <= 5) {
						this.jaw.isHidden = true;
						this.legbackleft.isHidden = true;
						if (hp <= 1) {
							this.legfrontleft.isHidden = true;
						} else {
							this.legfrontleft.isHidden = false;
						}
					} else {
						this.jaw.isHidden = false;
						this.legbackleft.isHidden = false;
						this.legbackright.isHidden = false;
					}
				} else {
					this.jaw.isHidden = false;
					this.tailpart4.isHidden = false;
					this.tailpart5.isHidden = false;
					this.legbackleft.isHidden = false;
					this.legbackright.isHidden = false;
				}
			} else {
				this.jaw.isHidden = false;
				this.legfrontleft.isHidden = false;
				this.legbackleft.isHidden = false;
				this.tailpart6.isHidden = false;
				this.tailpart5.isHidden = false;
				this.tailpart4.isHidden = false;
			}
		}

		Entity682 scp;

		if (entityIn instanceof Entity682) {
			scp = (Entity682) entityIn;
			this.biteProgress = scp.biteProgress;
			this.swimming = scp.isInWater();
		}

		float f = 1.0F;
		this.legfrontleft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
		this.legfrontright.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount
				/ f;
		this.legbackright.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
		this.legbackleft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount
				/ f;
		this.legfrontright.rotateAngleY = 0.0F;
		this.legfrontleft.rotateAngleY = 0.0F;
		this.legbackright.rotateAngleY = 0.0F;
		this.legbackleft.rotateAngleY = 0.0F;
		this.legfrontright.rotateAngleZ = 0.0F;
		this.legfrontleft.rotateAngleZ = 0.0F;
		this.legbackright.rotateAngleZ = 0.0F;
		this.legbackleft.rotateAngleZ = 0.0F;

		this.tailpart2.rotateAngleY = this.tailpart1.rotateAngleY = MathHelper.cos((float) (limbSwing * (0.6662f / 2)))
				* 0.7f * limbSwingAmount / 2;
		this.tailpart3.rotateAngleY = this.tailpart1.rotateAngleY;
		this.tailpart4.rotateAngleY = this.tailpart1.rotateAngleY;
		this.tailpart5.rotateAngleY = this.tailpart1.rotateAngleY;
		this.tailpart6.rotateAngleY = this.tailpart1.rotateAngleY;

		float f26 = f25 = this.biteProgress;
		if (f25 >= 0.5f) {
			f26 = 0.5f - (f25 - 0.5f);
		}
		this.snout.rotateAngleX = this.head.rotateAngleX - f26;
		this.jaw.rotateAngleX = this.head.rotateAngleX + f26 / 2.0f;
	}
}
