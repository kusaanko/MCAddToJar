package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class extraIC125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "extraIC125-");
    }

    @Override
    public String getDownloadURL() {
        return "http://minecraft125user.nisfan.net/forum/download/file.php?id=2026";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://minecraft125user.nisfan.net/forum/viewtopic.php?f=6&t=88";
    }

    @Override
    public String getReferer() {
        return "http://minecraft125user.nisfan.net/forum/viewtopic.php?f=6&t=88";
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods(Forge125.class, MCAPI125.class);
    }

    @Override
    public String getName() {
        return "extraIC";
    }

    @Override
    public String getShortName() {
        return "extraIC";
    }

    @Override
    public String getAuthor() {
        return "Deer05";
    }

    @Override
    public String getVersion() {
        return "1-180523";
    }

    @Override
    public String getDownloadFileName() {
        return "extraIC125-1-180523.zip";
    }
}
