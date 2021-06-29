package com.NetUserGet.XYZ.config;


import me.shedaniel.autoconfig.*;
import me.shedaniel.autoconfig.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Config(name = "xyz")
public class XYZConfig implements ConfigData {

    @ConfigEntry.ColorPicker(allowAlpha = true)
    @ConfigEntry.Gui.Tooltip(count = 2)
    public int textColor = 0xDDFFFFFF;

    public boolean toggleCoordinates = true;
    public boolean toggleDirection = true;
    public boolean toggleSaturation = true;
    public boolean toggleXyzVersion = true;
    public List<String> deathMutes = Arrays.asList("NetUserGet");

}
