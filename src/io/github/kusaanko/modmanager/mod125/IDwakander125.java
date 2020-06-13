package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class IDwakander125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_IDwakander125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsUGItSVM3VkI1VU0";
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
        return "IDwakander";
    }

    @Override
    public String getShortName() {
        return "IDwakander";
    }

    @Override
    public String getAuthor() {
        return "さよ";
    }

    @Override
    public String getVersion() {
        return "15.170412";
    }

    @Override
    public String getDownloadFileName() {
        return "sy_IDwakander125-15.170412.zip";
    }
}
