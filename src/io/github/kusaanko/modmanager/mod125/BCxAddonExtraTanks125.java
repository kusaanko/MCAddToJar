package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class BCxAddonExtraTanks125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "BCxAddon-ExtraTanks125-");
    }

    @Override
    public String getDownloadURL() {
        //return "https://drive.google.com/uc?id=0BzZbvRtrx7SsTU1HNUJnR0wyUTQ";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods/raw/main/takanasayo/BCxAddon-ExtraTanks125-15.170426.zip";
    }

    @Override
    public String getDownloadPageURL() {
        //return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods";
    }

    @Override
    public TYPE getType() {
        return TYPE.ADDON;
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods(Forge125.class, MCAPI125.class);
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(BCx125.class);
    }

    @Override
    public String getName() {
        return "BCxAddon Extra Tanks";
    }

    @Override
    public String getShortName() {
        return "ExtraTanks";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "15.170426";
    }

    @Override
    public String getDownloadFileName() {
        return "BCxAddon-ExtraTanks125-15.170426.zip";
    }
}
