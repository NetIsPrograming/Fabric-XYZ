package com.NetUserGet.XYZ.mixin;

import com.NetUserGet.XYZ.config.XYZConfig;
import com.NetUserGet.XYZ.XYZ;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Text title) {
        super(title);
    }

    // For the Fade in
    @Shadow
    boolean doBackgroundFade;
    @Shadow
    long backgroundFadeStart;


    @Inject(at = @At("RETURN"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V")
    private void render(MatrixStack matrices, int x, int y, float tickDelta,  CallbackInfo info) {
        XYZConfig config = AutoConfig.getConfigHolder(XYZConfig.class).getConfig();
        float f = this.doBackgroundFade ? (float)(Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0F : 1.0F;
        float g = this.doBackgroundFade ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
        int l = MathHelper.ceil(g * 255.0F) << 24;

        x = 2;
        y = 4;

        int color = 0xFFFFFFFF;

        // The Version of the Mod With "XYZ version added at the end"
        String text = "XYZ Version: " + XYZ.VERSION;
        // More code to fade in the text proper
        if (config.toggleXyzVersion) {
            if ((l & 0xFC000000) != 0) {
                this.textRenderer.drawWithShadow(matrices, text, x, y, color | l); // Just Draws the version with Shadow
            }
        }
    }
}
