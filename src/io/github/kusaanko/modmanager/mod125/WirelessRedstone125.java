package io.github.kusaanko.modmanager.mod125;

public class WirelessRedstone125 extends Mod125 {
    @Override
    public String is(String fileName) {
        if(fileName.equals("WirelessRedstone")) {
            return "1.6";
        }
        return null;
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20130502013446if_/http://minecraft.ali4z.com/downloads/1.6/1_2_5/WirelessRedstone.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1273821-1-4-7-wireless-redstone-v1-6-1";
    }

    @Override
    public String getName() {
        return "WirelessRedstone";
    }

    @Override
    public String getShortName() {
        return "WirelessRedstone";
    }

    @Override
    public String getAuthor() {
        return "ali4z";
    }

    @Override
    public String getVersion() {
        return "1.6";
    }

    @Override
    public String getDownloadFileName() {
        return "WirelessRedstone.zip";
    }
}
