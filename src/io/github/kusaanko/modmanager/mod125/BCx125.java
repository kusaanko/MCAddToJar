package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class BCx125 extends Mod125 {

    public String is(String fileName) {
        if(fileName.startsWith("BCx") && !fileName.contains("Addon")) {
            return fileName.substring(3);
        }
        return null;
    }

    @Override
    public String getDownloadURL() {
        //return "https://drive.google.com/uc?id=1IrYy83ZiWHLGO87PQkR89-hIjN4U-AZr";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods/raw/main/takanasayo/BCx3.1.5.21.180727.zip";
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
        return "BCx";
    }

    @Override
    public String getShortName() {
        return "BCx";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "3.1.5.21.180727";
    }

    @Override
    public String getDownloadFileName() {
        return "BCx3.1.5.21.180727.zip";
    }
}
