package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class psFirework125 extends Mod125 {

    public String is(String fileName) {
        return startsWith(fileName, "psFirework");
    }

    @Override
    public String getDownloadURL() {
        return "http://minecraft125user.nisfan.net/forum/download/file.php?id=809";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://minecraft125user.nisfan.net/forum/viewtopic.php?f=19&t=20#p40";
    }

    @Override
    public String getReferer() {
        return "http://minecraft125user.nisfan.net/forum/viewtopic.php?f=19&t=20";
    }

    @Override
    public Class<? extends Mod>[] getConflictMods() {
        return genRequireMods(RailCraft125.class);
    }

    @Override
    public String getName() {
        return "psFireworks";
    }

    @Override
    public String getShortName() {
        return "psFireworks";
    }

    @Override
    public String getAuthor() {
        return "A.K.";
    }

    @Override
    public String getVersion() {
        return "1.1b";
    }

    @Override
    public String getDownloadFileName() {
        return "psFirework1.1b.zip";
    }
}
