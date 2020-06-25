package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class matterZip125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mod_matterzip");
    }

    @Override
    public String getDownloadURL() {
        return "http://minecraft125user.nisfan.net/forum/download/file.php?id=518";
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
    public TYPE getType() {
        return TYPE.ADDON;
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(IC2125.class);
    }

    @Override
    public String getName() {
        return "matterZip";
    }

    @Override
    public String getShortName() {
        return "matterZip";
    }

    @Override
    public String getAuthor() {
        return "Deer05";
    }

    @Override
    public String getVersion() {
        return "05";
    }

    @Override
    public String getDownloadFileName() {
        return "mod_matterzip05.zip";
    }
}
