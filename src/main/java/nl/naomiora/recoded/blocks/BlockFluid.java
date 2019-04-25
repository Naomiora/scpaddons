package nl.naomiora.recoded.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import nl.naomiora.recoded.items.AdditionalItems;
import nl.naomiora.recoded.registration.BlockRegistration;

public class BlockFluid extends BlockFluidClassic {
	public BlockFluid(String name, Fluid fluid, Material material) {
		super(fluid, material);
		setUnlocalizedName(name);
		setRegistryName(name);

		BlockRegistration.blocks.add(this);
		AdditionalItems.items.add(new ItemBlock(this).setRegistryName(name));
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
}
