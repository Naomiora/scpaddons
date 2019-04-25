package nl.naomiora.recoded.damage.base;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class DamageSourceBase extends DamageSource{
	private String deathMessage;
	
	
	public DamageSourceBase(String sourceName, boolean isAbsolute, boolean bypassesArmor, boolean isMagic, boolean isFire, boolean isExplosion, boolean isProjectile, String deathMessage) {
		super(sourceName);
		if(isAbsolute) {
			this.setDamageIsAbsolute();
		} else if(isMagic) {
			this.setMagicDamage();
		} else if(isFire) {
			this.setFireDamage();
		} else if(isExplosion) {
			this.setExplosion();
		} else if (isProjectile) {
			this.setProjectile();
		} else if (bypassesArmor) {
			this.setDamageBypassesArmor();
		}
		this.deathMessage = deathMessage;
	}
	
	@Override
	public ITextComponent getDeathMessage(EntityLivingBase entityLivingBase) {
		return new TextComponentString(deathMessage.replace("%USER%", entityLivingBase.getName()));
	}
}
