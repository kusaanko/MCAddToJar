package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class ReiMinimap125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "[1.2.5]ReiMinimap_v");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20121221064005if_/http://dl.dropbox.com/u/34787499/minecraft/%5B1.2.5%5DReiMinimap_v3.2_05.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20160531201059/http://forum.minecraftuser.jp/viewtopic.php?t=153";
    }

    @Override
    public String getName() {
        return "Rei's Minimap";
    }

    @Override
    public String getShortName() {
        return "Rei's Minimap";
    }

    @Override
    public String getAuthor() {
        return "ReiFNSK";
    }

    @Override
    public String getVersion() {
        return "3.2_05";
    }

    @Override
    public String getDownloadFileName() {
        return "[1.2.5]ReiMinimap_v3.2_05.zip";
    }
}
