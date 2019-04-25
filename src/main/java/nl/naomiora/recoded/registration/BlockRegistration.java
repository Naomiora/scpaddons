package nl.naomiora.recoded.registration;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nl.naomiora.recoded.blocks.BlockFluid;

public class BlockRegistration {
	public static final List<Block> blocks = new ArrayList<Block>();

	public static final Block ACID = new BlockFluid("acid", FluidsRegistration.ACID, Material.WATER);

	public BlockRegistration() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(blocks.toArray(new Block[0]));
	}
}