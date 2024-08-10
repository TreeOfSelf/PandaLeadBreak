package me.sebastian420.PandaLeadBreak.mixin;

import net.minecraft.entity.Leashable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;


@Mixin(Leashable.class)
public interface LeashableMixin {

    @ModifyConstant(
            method = "tickLeash",
            constant = @Constant(doubleValue = 10.0)
    )
    private static double modifyLeashDistance(double original) {
        return 100.0;
    }
}