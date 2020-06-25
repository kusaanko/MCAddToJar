package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class AlternativeRecipes125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_AlternativeRecipes125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsNmNOVGpQYWlzM2M";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
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
    public String getName() {
        return "AlternativeRecipes";
    }

    @Override
    public String getShortName() {
        return "AlternativeRecipes";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "6.170330";
    }

    @Override
    public String getDownloadFileName() {
        return "sy_AlternativeRecipes125-6.170330.zip";
    }
}
