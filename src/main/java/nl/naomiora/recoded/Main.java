package nl.naomiora.recoded;

import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import nl.naomiora.recoded.TempStorage.TempStorage;
import nl.naomiora.recoded.client.SyncMaxHealth;
import nl.naomiora.recoded.client.SyncPotions;
import nl.naomiora.recoded.network.NetworkHandler;
import nl.naomiora.recoded.potions.AdditionalPotions;
import nl.naomiora.recoded.proxy.CommonProxy;
import nl.naomiora.recoded.registration.BlockRegistration;
import nl.naomiora.recoded.registration.EntityRegistration;
import nl.naomiora.recoded.registration.RenderHandler;
import nl.naomiora.recoded.registration.FluidsRegistration;
import nl.naomiora.recoded.registration.ItemRegistration;
import nl.naomiora.recoded.registration.PotionRegistration;
import nl.naomiora.recoded.registration.SoundRegistration;
import nl.naomiora.recoded.scp.ScpHandler;
import nl.naomiora.recoded.scp.scp2521.PlayerChat;
import nl.naomiora.recoded.server.ServerModule;

@Mod(modid = "scpadgrace", name = "scp-addons gr", version = "0.2")
public class Main {
	@Instance
	public static Main instance;
	public static boolean noConnect = false;

	@SidedProxy(clientSide = "nl.naomiora.recoded.proxy.ClientProxy", serverSide = "nl.naomiora.recoded.proxy.CommonProxy")
	public static CommonProxy proxy;

	static {
		FluidRegistry.enableUniversalBucket();
	}
	
	@Mod.EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		new FluidsRegistration();
		new AdditionalPotions();
		new PotionRegistration();
		new EntityRegistration();
		new SoundRegistration();
		new BlockRegistration();
		if (event.getSide().equals(Side.CLIENT)) {
			new RenderHandler();
			RenderHandler.registerCustomMeshesAndStates();
			new SyncPotions();
			new SyncMaxHealth();
		}
		new ServerModule();
		new ScpHandler();
		new TempStorage(event.getSide());

		MinecraftForge.EVENT_BUS.register(new ItemRegistration());
		MinecraftForge.EVENT_BUS.register(new PlayerChat());
	}

	@Mod.EventHandler
	public static void init(FMLInitializationEvent event) {
		new NetworkHandler();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	public static void freeze(boolean bool) {
		TempStorage.instance.setFreezeKeyboard(bool);
	}

	public void syncPotion(boolean add, int id, int duration, int amp) {
		if (id == -1) {
			return;
		}

		if (add) {
			SyncPotions.instance.addPotion(id, duration, amp);
		} else {
			SyncPotions.instance.removePotion(id);
		}
	}

	public Potion getPotionById(int id) {
		if (AdditionalPotions.customIds.containsKey(id)) {
			return AdditionalPotions.customIds.get(id);
		}

		return Potion.getPotionById(id);
	}

	public int getIdByPotion(Potion potion) {
		if (AdditionalPotions.customIds.containsValue(potion)) {
			for (int id : AdditionalPotions.customIds.keySet()) {
				if (AdditionalPotions.customIds.get(id).equals(potion)) {
					return id;
				}
			}
		}

		return Potion.getIdFromPotion(potion);
	}

	public void setHealth(double health) {
		TempStorage.instance.setHealth(health);
	}

	public void saidIt() {
		TempStorage.instance.saidIt();
	}
}