package nl.naomiora.recoded.items.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nl.naomiora.recoded.Main;
import nl.naomiora.recoded.items.AdditionalItems;
import nl.naomiora.recoded.registration.interfaces.Items;

public class ItemBase extends Item implements Items{

	private boolean hasEnchantmentGlow;
	
	public ItemBase(String name, int maxStackSize, boolean enchantmentGlow) {
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(CreativeTabs.BREWING);
		this.setMaxStackSize(maxStackSize);
		this.hasEnchantmentGlow = enchantmentGlow;
		AdditionalItems.items.add(this);
	}
	
	@Override
	public void registerItem() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return this.hasEnchantmentGlow;
	}
}