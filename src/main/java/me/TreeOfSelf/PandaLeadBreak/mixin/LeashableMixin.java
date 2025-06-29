package me.TreeOfSelf.PandaLeadBreak.mixin;

import net.minecraft.entity.Leashable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;


@Mixin(Leashable.class)
public interface LeashableMixin {

    @ModifyConstant(
            method = "getLeashSnappingDistance",
            constant = @Constant(doubleValue = 12.0)
    )
    private static double modifyLeashDistance(double original) {
        return 50.0;
    }
}