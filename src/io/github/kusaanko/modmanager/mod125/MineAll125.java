package io.github.kusaanko.modmanager.mod125;

public class MineAll125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "[1.2.5]MineAll v");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20190715170403if_/https://forum.minecraftuser.jp/download/file.php?id=12121";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20190716004858/https://forum.minecraftuser.jp/viewtopic.php?f=13&t=2592&start=0";
    }

    @Override
    public String getName() {
        return "MineAll";
    }

    @Override
    public String getShortName() {
        return "MineAll";
    }

    @Override
    public String getAuthor() {
        return "masa";
    }

    @Override
    public String getVersion() {
        return "2.5";
    }

    @Override
    public String getDownloadFileName() {
        return "[1.2.5]MineAll v2.5.zip";
    }
}
