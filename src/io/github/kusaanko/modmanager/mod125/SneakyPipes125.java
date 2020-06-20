package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class SneakyPipes125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "SneakyPipes-");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20160506140838if_/https://dl.dropboxusercontent.com/u/8224895/Minecraft/Minecraft-1.2.5/SneakyPipes-0.1.1.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20140819150430/http://www.mod-buildcraft.com/forums/topic/1-1-0-sneaky-pipes-bc-3-1-2/";
    }

    @Override
    public TYPE getType() {
        return TYPE.ADDON;
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(BCx125.class);
    }

    @Override
    public String getName() {
        return "Sneaky Pipes";
    }

    @Override
    public String getShortName() {
        return "Sneaky Pipes";
    }

    @Override
    public String getAuthor() {
        return "Krapht";
    }

    @Override
    public String getVersion() {
        return "0.1.1";
    }

    @Override
    public String getDownloadFileName() {
        return "SneakyPipes-0.1.1.zip";
    }
}
