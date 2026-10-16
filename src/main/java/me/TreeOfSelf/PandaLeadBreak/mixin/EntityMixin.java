package me.TreeOfSelf.PandaLeadBreak.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.TreeOfSelf.PandaLeadBreak.LeashRules;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public class EntityMixin {

	// Shift-clicking a mob to tie the player's leashed mobs to it.
	@ModifyExpressionValue(
		method = "interact",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/entity/Leashable;canBeLeashed()Z"
		)
	)
	private boolean leashAnyMob(boolean vanilla, Player player, InteractionHand hand, Vec3 location) {
		return LeashRules.canBeLeashed((Leashable) this, vanilla, player);
	}
}
