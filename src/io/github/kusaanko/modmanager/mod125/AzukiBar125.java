package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class AzukiBar125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_AzukiBar125-");
    }

    @Override
    public String getDownloadURL() {
        //return "https://drive.google.com/uc?id=0BzZbvRtrx7SsdlBSQnZZZmhDdTA";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods/raw/main/takanasayo/jar/FururinMod125-1(jar).zip";
    }

    @Override
    public String getDownloadPageURL() {
        //return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods";
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods(Forge125.class, MCAPI125.class);
    }

    @Override
    public String getName() {
        return "AzukiBar";
    }

    @Override
    public String getShortName() {
        return "AzukiBar";
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
        return "sy_AzukiBar125-1.zip";
    }
}
