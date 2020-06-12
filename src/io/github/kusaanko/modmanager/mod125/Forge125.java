package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class Forge125 extends Mod125 {
    @Override
    public String is(String fileName) {
        if(fileName.startsWith("forge-1.2.5-") && fileName.endsWith("-client")) {
            return fileName.substring("forge-1.2.5-".length(), fileName.lastIndexOf("-client"));
        }
        return null;
    }

    @Override
    public TYPE getType() {
        return TYPE.REQUIREMENTS;
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
        return "http://files.minecraftforge.net/maven/net/minecraftforge/forge/1.2.5-3.4.9.171/forge-1.2.5-3.4.9.171-client.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://files.minecraftforge.net/maven/net/minecraftforge/forge/index_1.2.5.html";
    }

    @Override
    public String getDownloadFolder() {
        return "forge/";
    }

    @Override
    public String getName() {
        return "Forge";
    }

    @Override
    public String getShortName() {
        return "Forge";
    }

    @Override
    public String getAuthor() {
        return "Forge Team";
    }

    @Override
    public String getVersion() {
        return "3.4.9.171";
    }

    @Override
    public String getDownloadFileName() {
        return "forge-1.2.5-3.4.9.171-client.zip";
    }
}
