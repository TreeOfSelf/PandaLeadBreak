package me.TreeOfSelf.PandaLeadBreak.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import me.TreeOfSelf.PandaLeadBreak.LeashRules;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Leashable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Leashable.class)
public interface LeashableMixin {

	@WrapOperation(
		method = "canHaveALeashAttachedTo",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/entity/Leashable;canBeLeashed()Z"
		)
	)
	private static boolean leashAnyMob(Leashable self, Operation<Boolean> original, @Local(argsOnly = true) Entity holder) {
		return LeashRules.canBeLeashed(self, original.call(self), holder);
	}

	@ModifyExpressionValue(
		method = "canHaveALeashAttachedTo",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/entity/Leashable;leashSnapDistance()D"
		)
	)
	private static double strongLeadAttach(double vanilla, @Local(argsOnly = true) Entity holder) {
		return LeashRules.snapDistance(vanilla, holder);
	}

	@ModifyExpressionValue(
		method = "tickLeash",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/entity/Leashable;leashSnapDistance()D"
		)
	)
	private static double strongLeadTick(double vanilla, @Local(argsOnly = true) Entity entity) {
		return LeashRules.snapDistance(vanilla, ((Leashable) entity).getLeashHolder());
	}
}
