package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class PowerConverters125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "IC2-1.95b_BC3.1.5_PowerConverters125-");
    }

    @Override
    public String getDownloadURL() {
        return "http://minecraft125user.nisfan.net/forum/download/file.php?id=914";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://minecraft125user.nisfan.net/forum/viewtopic.php?f=11&t=42&start=20";
    }

    @Override
    public String getReferer() {
        return "http://minecraft125user.nisfan.net/forum/viewtopic.php?f=11&t=42&start=20";
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(BCx125.class, IC2125.class);
    }

    @Override
    public String getName() {
        return "PowerConverters";
    }

    @Override
    public String getShortName() {
        return "PowerConverters";
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
        return "IC2-1.95b_BC3.1.5_PowerConverters125-1.zip";
    }
}
