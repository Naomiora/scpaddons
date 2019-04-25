package nl.naomiora.recoded.damage;

import net.minecraft.util.DamageSource;
import nl.naomiora.recoded.damage.base.DamageSourceBase;

public class AdditionalDamageSources {

	public static DamageSource scp012 = new DamageSourceBase("scp-012", true, true, true, false, false, false, "%USER% has been affected by SCP-012's curse!");
}