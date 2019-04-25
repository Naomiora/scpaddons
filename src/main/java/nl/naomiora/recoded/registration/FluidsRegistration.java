package nl.naomiora.recoded.registration;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import nl.naomiora.recoded.liquids.FluidLiquid;

public class FluidsRegistration {
	public static final Fluid ACID = new FluidLiquid("acid", new ResourceLocation("scpadgrace", "blocks/acid_still"), new ResourceLocation("scpadgrace", "blocks/acid_flowing"));

	public FluidsRegistration() {
		FluidRegistry.registerFluid(ACID);
		FluidRegistry.addBucketForFluid(ACID);
	}
}