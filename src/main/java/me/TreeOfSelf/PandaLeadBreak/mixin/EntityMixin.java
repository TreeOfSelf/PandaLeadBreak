package me.TreeOfSelf.PandaLeadBreak.mixin;

import me.TreeOfSelf.PandaLeadBreak.LeashRules;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Leashable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public class EntityMixin {

	@Redirect(
		method = "interact",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/entity/Leashable;canBeLeashed()Z"
		)
	)
	private boolean leashAnyMob(Leashable leashable) {
		return LeashRules.canBeLeashed(leashable);
	}
}
