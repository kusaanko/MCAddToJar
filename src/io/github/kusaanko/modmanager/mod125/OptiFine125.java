package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class OptiFine125 extends Mod125 {
    @Override
    public String is(String fileName) {
        if(!fileName.contains("Fix"))
            return startsWith(fileName, "OptiFine_1.2.5_");
        return null;
    }

    @Override
    public INSTALLATION_TYPE getInstallationType() {
        return INSTALLATION_TYPE.IN_JAR;
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return null;
    }

    @Override
    public String getDownloadURL() {
        return null;
    }

    @Override
    public String getDownloadPageURL() {
        return "https://optifine.net/adloadx?f=OptiFine_1.2.5_HD_U_C7.zip";
    }

    @Override
    public boolean needUserDownload() {
        return true;
    }

    @Override
    public String getName() {
        return "OptiFine";
    }

    @Override
    public String getShortName() {
        return "OptiFine";
    }

    @Override
    public String getAuthor() {
        return "sp614x";
    }

    @Override
    public String getVersion() {
        return "HD_U_C7";
    }

    @Override
    public String getDownloadFileName() {
        return "OptiFine_1.2.5_HD_U_C7.zip";
    }
}
