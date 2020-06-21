package io.github.kusaanko.modmanager.mod125;

public class fragileArmor125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "fragileArmor-1_2_5-");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20190716012107if_/https://forum.minecraftuser.jp/download/file.php?id=9415";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20150127075650/http://forum.minecraftuser.jp/viewtopic.php?f=13&t=176";
    }

    @Override
    public String getName() {
        return "fragileArmor";
    }

    @Override
    public String getShortName() {
        return "fragileArmor";
    }

    @Override
    public String getAuthor() {
        return "MMM";
    }

    @Override
    public String getVersion() {
        return "1";
    }

    @Override
    public String getDownloadFileName() {
        return "fragileArmor-1_2_5-1.zip";
    }
}
