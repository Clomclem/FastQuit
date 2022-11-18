package com.kingcontaria.fastquit.mixin;

import com.kingcontaria.fastquit.FastQuit;
import net.minecraft.server.integrated.IntegratedServerLoader;
import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;

@Mixin(IntegratedServerLoader.class)
public abstract class IntegratedServerLoaderMixin {

    @Inject(method = "createSession", at = @At("HEAD"))
    private void fastQuit_waitForSaveOnWorldLoad(String levelName, CallbackInfoReturnable<LevelStorage.Session> cir) {
        FastQuit.getSavingWorld(levelName).ifPresent(server -> FastQuit.wait(Collections.singleton(server)));
    }
}