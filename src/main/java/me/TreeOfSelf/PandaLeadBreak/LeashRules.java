package me.TreeOfSelf.PandaLeadBreak;

import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.villager.AbstractVillager;

// Each perk is a permission, granted by default so the mod works without a permission provider
// (PandaVotes locks the ones it sells).
public final class LeashRules {
	public static final String STRONG = "pandaleadbreak.strong";
	public static final String VILLAGERS = "pandaleadbreak.villagers";
	public static final String ANIMALS = "pandaleadbreak.animals";
	public static final String HOSTILE = "pandaleadbreak.hostile";

	public static final double STRONG_SNAP_DISTANCE = 25.0;
	// Mobs vanilla won't leash stay unleashable above this width (ravager, elder guardian, ghast, big slimes).
	private static final double MAX_WIDTH = 1.5;

	private LeashRules() {}

	public static boolean has(Entity entity, String permission) {
		return entity instanceof ServerPlayer player && Permissions.check(player, permission, true);
	}

	// vanilla: what Leashable.canBeLeashed() said. holder: who is leashing it.
	public static boolean canBeLeashed(Leashable leashable, boolean vanilla, Entity holder) {
		if (vanilla) return true;
		if (!(leashable instanceof Mob mob)) return false;
		if (mob instanceof WitherBoss || mob instanceof EnderDragon || mob instanceof Warden) return false;
		if (mob.getBbWidth() > MAX_WIDTH) return false;
		// Moving a leash from one holder to another (fence knots etc.): it was already allowed.
		if (!(holder instanceof ServerPlayer)) return mob.isLeashed();
		if (mob instanceof AbstractVillager) return has(holder, VILLAGERS);
		if (mob instanceof Enemy) return has(holder, HOSTILE);
		return has(holder, ANIMALS);
	}

	public static double snapDistance(double vanilla, Entity holder) {
		return has(holder, STRONG) ? Math.max(vanilla, STRONG_SNAP_DISTANCE) : vanilla;
	}
}
