package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class ChainDestruction125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "ChainDestruction-");
    }

    @Override
    public String getDownloadURL() {
        return "https://dl.dropboxusercontent.com/s/owl11lol8egrr99/ChainDestruction-1.2.5-1.4.5.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://minecraft125user.nisfan.net/forum/viewtopic.php?f=12&t=142";
    }

    @Override
    public Class<? extends Mod>[] getCompetingMods() {
        return genRequireMods(TrainMod125.class);
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods(Forge125.class, MCAPI125.class);
    }

    @Override
    public String getName() {
        return "連鎖破壊系MOD";
    }

    @Override
    public String getShortName() {
        return "連鎖破壊系MOD";
    }

    @Override
    public String getAuthor() {
        return "A.K.";
    }

    @Override
    public String getVersion() {
        return "1.2.5-1.4.5";
    }

    @Override
    public String getDownloadFileName() {
        return "ChainDestruction-1.2.5-1.4.5.zip";
    }
}
