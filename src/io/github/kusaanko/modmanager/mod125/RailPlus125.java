package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class RailPlus125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_RailPlus125-");
    }

    @Override
    public String getDownloadURL() {
        //return "https://drive.google.com/uc?id=1XpyXR6u8gkOCHd-y2iNXopkVxlCHBsvx";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods/raw/main/takanasayo/sy_RailPlus125-16.200427.zip";
    }

    @Override
    public String getDownloadPageURL() {
        //return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods";
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods(Forge125.class, MCAPI125.class);
    }

    @Override
    public String getName() {
        return "RailPlus";
    }

    @Override
    public String getShortName() {
        return "RailPlus";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "16.200427";
    }

    @Override
    public String getDownloadFileName() {
        return "sy_RailPlus125-16.200427.zip";
    }
}
