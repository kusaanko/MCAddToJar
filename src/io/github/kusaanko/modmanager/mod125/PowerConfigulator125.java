package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class PowerConfigulator125 extends Mod125 {
    @Override
    public String is(String fileName) {
        if(fileName.equals("PowerConfig")) {
            return "1.0";
        }
        return null;
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20130502075021if_/http://minecraft.ali4z.com/downloads/1.6/1_2_5/PowerConfig.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1273821-1-4-7-wireless-redstone-v1-6-1";
    }

    @Override
    public TYPE getType() {
        return TYPE.ADDON;
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(WirelessRedstone125.class);
    }

    @Override
    public String getName() {
        return "Power Configulator";
    }

    @Override
    public String getShortName() {
        return "Power Configulator";
    }

    @Override
    public String getAuthor() {
        return "ali4z";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getDownloadFileName() {
        return "PowerConfig.zip";
    }
}
