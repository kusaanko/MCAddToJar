package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class Cardboard125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_Cardboard125-");
    }

    @Override
    public String getDownloadURL() {
        //return "https://drive.google.com/uc?id=0BzZbvRtrx7SsLU1zQy1kSnhWdE0";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods/raw/main/takanasayo/sy_Cardboard125-6.170406.zip";
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
        return "Cardboard";
    }

    @Override
    public String getShortName() {
        return "Cardboard";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "6.170406";
    }

    @Override
    public String getDownloadFileName() {
        return "sy_Cardboard125-6.170406.zip";
    }
}
