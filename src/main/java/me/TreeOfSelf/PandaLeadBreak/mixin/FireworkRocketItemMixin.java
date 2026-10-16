package me.TreeOfSelf.PandaLeadBreak.mixin;

import me.TreeOfSelf.PandaLeadBreak.LeashRules;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FireworkRocketItem;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FireworkRocketItem.class)
public class FireworkRocketItemMixin {

	@Redirect(
		method = "use",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/entity/player/Player;dropAllLeashConnections(Lnet/minecraft/world/entity/player/Player;)Z"
		)
	)
	private boolean preventLeadBreaking(Player instance, @Nullable Player player) {
		if (LeashRules.has(instance, LeashRules.STRONG)) return false;
		return instance.dropAllLeashConnections(player);
	}
}
