package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class VillagersSpawnBan125 extends Mod125 {
    @Override
    public String is(String fileName) {
        if(fileName.startsWith("sy_VillagersSpawnBan125-") && fileName.contains("(jar)")) {
            return fileName.substring("sy_VillagersSpawnBan125-".length(), fileName.lastIndexOf("(jar)"));
        }
        return null;
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsMHhxcnRiZm4tZ1k";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SsX0lGQlh1UTc2cVE";
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
        return "VillagersSpawnBan";
    }

    @Override
    public String getShortName() {
        return "VillagersSpawnBan";
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
        return "sy_VillagersSpawnBan125-1(jar).zip";
    }
}
