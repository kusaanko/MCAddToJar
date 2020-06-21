package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class ModLoaderMP125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "ModLoaderMP 1.2.5 v");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20141222000844if_/https://dl.dropboxusercontent.com/u/16399537/Releases/1.2.5/ModLoaderMP%201.2.5%20v1.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "";
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
    public String getName() {
        return "ModLoaderMP";
    }

    @Override
    public String getShortName() {
        return "ModLoaderMP";
    }

    @Override
    public String getAuthor() {
        return "Unknown";
    }

    @Override
    public String getVersion() {
        return "1";
    }

    @Override
    public String getDownloadFileName() {
        return "ModLoaderMP 1.2.5 v1.zip";
    }
}
