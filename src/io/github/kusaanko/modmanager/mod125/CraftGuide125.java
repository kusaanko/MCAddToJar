package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class CraftGuide125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_CraftGuide125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsTVg0SGVXelNsQ0k";
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
        return "CraftGuide";
    }

    @Override
    public String getShortName() {
        return "CraftGuide";
    }

    @Override
    public String getAuthor() {
        return "さよ";
    }

    @Override
    public String getVersion() {
        return "7.170827";
    }

    @Override
    public String getDownloadFileName() {
        return "sy_CraftGuide125-7.170827.zip";
    }
}
