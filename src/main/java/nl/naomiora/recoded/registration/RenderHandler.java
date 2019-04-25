package nl.naomiora.recoded.registration;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import nl.naomiora.recoded.entity.Entity2521;
import nl.naomiora.recoded.entity.Entity682;
import nl.naomiora.recoded.entity.Entity999;
import nl.naomiora.recoded.entity.render.RenderSCP2521;
import nl.naomiora.recoded.entity.render.RenderSCP682;
import nl.naomiora.recoded.entity.render.RenderSCP999;

public class RenderHandler {
	public RenderHandler() {
		MinecraftForge.EVENT_BUS.register(this);
		RenderingRegistry.registerEntityRenderingHandler(Entity999.class, new IRenderFactory<Entity999>() {
			@Override
			public Render<? super Entity999> createRenderFor(RenderManager manager) {
				return new RenderSCP999(manager);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(Entity2521.class, new IRenderFactory<Entity2521>() {
			@Override
			public Render<? super Entity2521> createRenderFor(RenderManager manager) {
				return new RenderSCP2521(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(Entity682.class, new IRenderFactory<Entity682>() {
			@Override
			public Render<? super Entity682> createRenderFor(RenderManager manager){
				return new RenderSCP682(manager);
			}
		});
	}
	
	public static void registerCustomMeshesAndStates() {
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(BlockRegistration.ACID), new ItemMeshDefinition() {
			
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return new ModelResourceLocation("scpadgrace:acid", "fluid");
			}
		});
		
		ModelLoader.setCustomStateMapper(BlockRegistration.ACID, new StateMapperBase() {
			
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation("scpadgrace:acid", "fluid");
			}
		});
	}
}