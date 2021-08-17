package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class FururinMod125 extends Mod125 {
    @Override
    public String is(String fileName) {
        if(fileName.startsWith("FururinMod125-") && fileName.contains("(jar)")) {
            return fileName.substring("FururinMod125-".length(), fileName.lastIndexOf("(jar)"));
        }
        return null;
    }

    @Override
    public String getDownloadURL() {
        //return "https://drive.google.com/uc?id=0BzZbvRtrx7SsWWpEcU1SODI3RnM";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods/raw/main/takanasayo/jar/FururinMod125-1(jar).zip";
    }

    @Override
    public String getDownloadPageURL() {
        //return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SsX0lGQlh1UTc2cVE";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods";
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return null;
    }

    @Override
    public INSTALLATION_TYPE getInstallationType() {
        return INSTALLATION_TYPE.IN_JAR;
    }

    @Override
    public String getName() {
        return "FururinMod";
    }

    @Override
    public String getShortName() {
        return "FururinMod";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "1";
    }

    @Override
    public String getDownloadFileName() {
        return "FururinMod125-1(jar).zip";
    }
}
