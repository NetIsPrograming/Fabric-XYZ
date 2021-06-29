package com.NetUserGet.XYZ.packet;

import com.NetUserGet.XYZ.config.XYZConfig;
import me.shedaniel.autoconfig.AutoConfig;


import java.util.List;

public class MuteDeathMessage {
    public static void RegisterMuteDeaths() {
        XYZConfig config = AutoConfig.getConfigHolder(XYZConfig.class).getConfig();
        List<String> muteDeaths = config.deathMutes;

        for (String mD : muteDeaths) {

        }
    }
}
