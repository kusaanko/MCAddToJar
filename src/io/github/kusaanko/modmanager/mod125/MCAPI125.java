package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class MCAPI125 extends Mod125 {

    public String is(String fileName) {
        return startsWith(fileName, "@MinecraftAPI-");
    }

    @Override
    public INSTALLATION_TYPE getInstallationType() {
        return INSTALLATION_TYPE.IN_JAR;
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=1ICtaX6zPmoixM1lNUDY9jeoeP7yYtrvd";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SsX0lGQlh1UTc2cVE";
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods(Forge125.class, OptiFine125.class);
    }

    @Override
    public String getName() {
        return "MinecraftAPI";
    }

    @Override
    public String getShortName() {
        return "MCAPI";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "1.24.190503";
    }

    @Override
    public String getDownloadFileName() {
        return "@MinecraftAPI-1.24.190503.zip";
    }
}
