package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class BedrockTools125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_BedrockTools125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsdlBSQnZZZmhDdTA";
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
        return "BedrockTools";
    }

    @Override
    public String getShortName() {
        return "BedrockTools";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "8.160522";
    }

    @Override
    public String getDownloadFileName() {
        return "sy_BedrockTools125-8.160522.zip";
    }
}
