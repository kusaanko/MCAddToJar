package io.github.kusaanko.modmanager.mod125;

public class FrenchBread125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "FranchBread-1_2_5-");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20160706213547if_/http://forum.minecraftuser.jp/download/file.php?id=9416";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20150127075650/http://forum.minecraftuser.jp/viewtopic.php?f=13&t=176";
    }

    @Override
    public String getName() {
        return "FranchBread";
    }

    @Override
    public String getShortName() {
        return "FranchBread";
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
        return "FranchBread-1_2_5-1.zip";
    }
}
