package io.github.kusaanko.modmanager.mod125;

public class Bamboo125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "竹mod ver");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20190715140525if_/https://forum.minecraftuser.jp/download/file.php?id=14401";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20190715123938/https://forum.minecraftuser.jp/viewtopic.php?f=13&t=172";
    }

    @Override
    public String getName() {
        return "竹mod";
    }

    @Override
    public String getShortName() {
        return "竹mod";
    }

    @Override
    public String getAuthor() {
        return "ruby";
    }

    @Override
    public String getVersion() {
        return "2.4.6";
    }

    @Override
    public String getDownloadFileName() {
        return "竹mod ver2.4.6.zip";
    }
}
