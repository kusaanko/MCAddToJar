package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class MineFactoryReloaded125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mod_MineFactory_forBC3.1.5_c1.2.5r");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20190715135920if_/https://forum.minecraftuser.jp/download/file.php?id=12479";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20190715113513/https://forum.minecraftuser.jp/viewtopic.php?f=13&t=2150";
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(BCx125.class);
    }

    @Override
    public String getName() {
        return "MineFactoryReloaded";
    }

    @Override
    public String getShortName() {
        return "MFR";
    }

    @Override
    public String getAuthor() {
        return "Ferne";
    }

    @Override
    public String getVersion() {
        return "3";
    }

    @Override
    public String getDownloadFileName() {
        return "mod_MineFactory_forBC3.1.5_c1.2.5r3.zip";
    }
}
