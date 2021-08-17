package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class ForestryTMI125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_ForestryTMI");
    }

    @Override
    public String getDownloadURL() {
        //return "https://drive.google.com/uc?id=0BzZbvRtrx7SsZVZvVUNjeEpqZzQ";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods/raw/main/takanasayo/sy_ForestryTMI125.zip";
    }

    @Override
    public String getDownloadPageURL() {
        //return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods";
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods(Forge125.class, MCAPI125.class, TooManyItems125.class);
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(ForestryForMinecraft125.class);
    }

    @Override
    public TYPE getType() {
        return TYPE.ADDON;
    }

    @Override
    public String getName() {
        return "ForestryTMI";
    }

    @Override
    public String getShortName() {
        return "ForestryTMI";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "125";
    }

    @Override
    public String getDownloadFileName() {
        return "sy_ForestryTMI125.zip";
    }
}
