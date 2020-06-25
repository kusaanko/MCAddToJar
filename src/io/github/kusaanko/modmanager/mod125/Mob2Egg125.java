package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class Mob2Egg125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_Mob2Egg125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=1ojnG8KDyODOuOsndF0IjcWq1YS-A9bZz";
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
        return "Mob2Egg";
    }

    @Override
    public String getShortName() {
        return "Mob2Egg";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "3";
    }

    @Override
    public String getDownloadFileName() {
        return "sy_Mob2Egg125-3.zip";
    }
}
