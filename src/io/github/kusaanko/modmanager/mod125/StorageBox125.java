package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class StorageBox125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_StorageBox125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsVUJ1OFBsYXVPYXc";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods(Forge125.class, MCAPI125.class);
    }

    @Override
    public String getName() {
        return "StorageBox";
    }

    @Override
    public String getShortName() {
        return "StorageBox";
    }

    @Override
    public String getAuthor() {
        return "さよ";
    }

    @Override
    public String getVersion() {
        return "13.170426";
    }

    @Override
    public String getDownloadFileName() {
        return "sy_StorageBox125-13.170426.zip";
    }
}
