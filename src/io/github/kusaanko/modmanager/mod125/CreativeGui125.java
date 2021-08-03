package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class CreativeGui125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "[1.2.5]CreativeGui_");
    }

    @Override
    public String getDownloadURL() {
        return "https://github.com/kusaanko/Minecraft_CreativeGui/releases/download/v1.2.1/1.2.5.CreativeGui_1.2.1.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://github.com/kusaanko/Minecraft_CreativeGui";
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods(Forge125.class, MCAPI125.class);
    }

    @Override
    public String getName() {
        return "CreativeGui";
    }

    @Override
    public String getShortName() {
        return "CreativeGui";
    }

    @Override
    public String getAuthor() {
        return "Kusaanko";
    }

    @Override
    public String getVersion() {
        return "1.2.1";
    }

    @Override
    public String getDownloadFileName() {
        return "[1.2.5]CreativeGui_1.2.1.zip";
    }
}
