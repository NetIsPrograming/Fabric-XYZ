package com.NetUserGet.XYZ.hud;


import com.NetUserGet.XYZ.configStuffs.XYZConfig;
import com.NetUserGet.XYZ.mixin.BossBarHudAccessor;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.client.gui.hud.ClientBossBar;
import net.minecraft.client.util.math.MatrixStack;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;


public class XYZHud {
    private static MinecraftClient client = MinecraftClient.getInstance();
    public static int Draw(MatrixStack matrices, String text, float x, float y, int color, boolean toggled) {
        if (toggled) {
            return client.textRenderer.draw(matrices, text, x, y, color);
        }else{
            return 0;
        }
    }

    public static int Draw(MatrixStack matrices, String text, float x, float y, int color, boolean toggled, boolean isAboveToggled, float prevY) {
        if (toggled) {
            if (isAboveToggled){
                return client.textRenderer.draw(matrices, text, x, y, color);
            }else {
                return client.textRenderer.draw(matrices, text, x, prevY, color);
            }
        }else {
            return 0;
        }
    }

    public static float textEquation(String str) {
        return (client.getWindow().getScaledWidth() / 2) - (client.textRenderer.getWidth(str) / 2);
    }



    //public static int color = 0xDDFFFFFF;



    public static void register() {
        XYZConfig config = AutoConfig.getConfigHolder(XYZConfig.class).getConfig();

        HudRenderCallback.EVENT.register((matrices, tickDelta) -> {

            float spacing = 8; // Spacing between the text objects.

            String coordinates;
            float cordsX;
            float cordsY;

            String direction;
            float directionX;
            float directionY;

            String saturationLevel;
            float satX;
            float satY;



            Map<UUID, ClientBossBar> bossBars = ((BossBarHudAccessor) client.inGameHud.getBossBarHud()).getBossBars();

            /* Gets Coordinates and Player Head Yaw
            Formats them into a nice little package
            of information*/

            coordinates = String.format("X: %.3f Y: %.3f Z: %.3f Yaw: %.1f",
                    client.cameraEntity.getX(),
                    client.cameraEntity.getY(),
                    client.cameraEntity.getZ(),
                    client.cameraEntity.getHeadYaw());


             cordsX = textEquation(coordinates); // The x Position of the coordinates String :>
             cordsY = 2; // The y Position of the coordinates String :>


            // A switch statement to get the players direction that their facing
            switch(client.player.getHorizontalFacing()){
                   case NORTH:
                       direction = "North";
                       break;
                   case SOUTH:
                       direction = "South";
                       break;
                   case WEST:
                       direction = "West";
                       break;
                   case EAST:
                       direction = "East";
                       break;
                   case UP:
                       direction = "Up";
                       break;
                   case DOWN:
                       direction = "Down";
                       break;
                   default:
                       direction = "Invalid";
            }


            directionX = textEquation(direction); // Same thing as cords y and x.
            directionY = config.toggleCoordinates ? cordsY + spacing : cordsY; // "spacing" is added to the previous Text Object Then put into the this one and then the next one is going to use this.


            saturationLevel = String.format("Sat: %.1f",
                client.player.getHungerManager().getSaturationLevel()); // Same thing like "coordinates"

            satX = textEquation(saturationLevel); // Self explanatory by now
            satY = config.toggleDirection ? directionY + spacing : directionY;  // Same thing here with the "spacing".

            if (!bossBars.isEmpty()) {
                float scaledHeight = client.getWindow().getScaledHeight();
                float bossBarSpacing = 5.0f;
                float bossBarBackLash = 12.0f;

                for (int i = 0; bossBars.size() >= i; i++) {
                    cordsY += bossBarSpacing;
                    directionY += bossBarSpacing;
                    satY += bossBarSpacing;

                    bossBarSpacing += bossBarSpacing;
                    if (bossBarSpacing >= scaledHeight / 3) {
                        break;
                    }
                }
            }


            if (!client.options.keyPlayerList.isPressed() || client.isInSingleplayer() && client.player.networkHandler.getPlayerList().size() <= 1 ) {
                if(!client.options.debugEnabled) {
                    Draw(matrices, coordinates, cordsX, cordsY, config.textColor, config.toggleCoordinates); // Draws Cords to the Screen all of the others are self explanatory
                    Draw(matrices, direction, directionX, directionY, config.textColor, config.toggleDirection, config.toggleCoordinates, cordsY);
                    Draw(matrices, saturationLevel, satX, satY, config.textColor, config.toggleSaturation, config.toggleDirection, directionY);
                } // This is so it doesn't render when F1 is pressed.
            }
        });
    }
}
