package me.TreeOfSelf.PandaLeadBreak;

import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;

public final class LeashRules {
	private LeashRules() {}

	public static boolean canBeLeashed(Leashable leashable) {
		if (leashable instanceof Mob mob) {
			return !(mob instanceof WitherBoss) && !(mob instanceof EnderDragon);
		}
		return leashable.canBeLeashed();
	}
}
