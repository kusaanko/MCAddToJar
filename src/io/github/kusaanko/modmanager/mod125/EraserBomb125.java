package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class EraserBomb125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_EraserBomb125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=14ksXFNV76jfXHZkI6IOmJRDGfEadeJd_";
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
        return "EraserBomb";
    }

    @Override
    public String getShortName() {
        return "EraserBomb";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "6.180523";
    }

    @Override
    public String getDownloadFileName() {
        return "sy_EraserBomb125-6.180523.zip";
    }
}
