package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class SmallStairs125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_SmallStairs125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=1YBAwUEL99hGGrCVYm38tMYv-iv6lRPy1";
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
        return "SmallStairs";
    }

    @Override
    public String getShortName() {
        return "SmallStairs";
    }

    @Override
    public String getAuthor() {
        return "さよ";
    }

    @Override
    public String getVersion() {
        return "7.171111";
    }

    @Override
    public String getDownloadFileName() {
        return "sy_SmallStairs125-7.171111.zip";
    }
}
