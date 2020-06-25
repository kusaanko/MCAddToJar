package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class CreativeGui125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "[1.2.5]CreativeGui_");
    }

    @Override
    public String getDownloadURL() {
        return "https://www.dropbox.com/s/ixk2yqwwfn661f8/%5B1.2.5%5DCreativeGui_1.2.1.zip?dl=1";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://mod.kusaanko.cf/mod.php?mod=1.2.5/CreativeGui";
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods(Forge125.class, MCAPI125.class);
    }

    @Override
    public String getName() {
        return "CreativeGui";
    }

    @Override
    public String getShortName() {
        return "CreativeGui";
    }

    @Override
    public String getAuthor() {
        return "Kusaanko";
    }

    @Override
    public String getVersion() {
        return "1.2.1";
    }

    @Override
    public String getDownloadFileName() {
        return "[1.2.5]CreativeGui_1.2.1.zip";
    }
}
