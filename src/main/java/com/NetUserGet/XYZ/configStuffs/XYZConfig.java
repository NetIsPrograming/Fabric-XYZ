package com.NetUserGet.XYZ.configStuffs;


import me.shedaniel.autoconfig.*;
import me.shedaniel.autoconfig.annotation.*;

@Config(name = "xyz")
public class XYZConfig implements ConfigData {

    @ConfigEntry.ColorPicker(allowAlpha = true)
    @ConfigEntry.Gui.Tooltip(count = 2)
    public int textColor = 0xDDFFFFFF;

    public boolean toggleCoordinates = true;
    public boolean toggleDirection = true;
    public boolean toggleSaturation = true;
    public boolean toggleXyzVersion = true;


}
