package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class AdditionalBuildcraftObjects125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "buildcraft-C-additionalbuildcraftobjects-");
    }

    @Override
    public String getDownloadURL() {
        return "https://www.siedler25.org/uploads/minecraft/AdditionalBuildcraftObjects/archive/3.1.5/buildcraft-C-additionalbuildcraftobjects-latest.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://www.siedler25.org/uploads/minecraft/AdditionalBuildcraftObjects/archive/3.1.5/";
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
        return "Additional Buildcraft Objects";
    }

    @Override
    public String getShortName() {
        return "Additional Buildcraft Objects";
    }

    @Override
    public String getAuthor() {
        return "Flow86";
    }

    @Override
    public String getVersion() {
        return "latest";
    }

    @Override
    public String getDownloadFileName() {
        return "buildcraft-C-additionalbuildcraftobjects-latest.zip";
    }
}
