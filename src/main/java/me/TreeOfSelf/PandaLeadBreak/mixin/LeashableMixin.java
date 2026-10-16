package me.TreeOfSelf.PandaLeadBreak.mixin;

import me.TreeOfSelf.PandaLeadBreak.LeashRules;
import net.minecraft.world.entity.Leashable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Leashable.class)
public interface LeashableMixin {

	@ModifyConstant(method = "leashSnapDistance", constant = @Constant(doubleValue = 12.0))
	private static double modifyLeashDistance(double original) {
		return 50.0;
	}

	@Redirect(
		method = "canHaveALeashAttachedTo",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/entity/Leashable;canBeLeashed()Z"
		)
	)
	private static boolean leashAnyMob(Leashable leashable) {
		return LeashRules.canBeLeashed(leashable);
	}
}
