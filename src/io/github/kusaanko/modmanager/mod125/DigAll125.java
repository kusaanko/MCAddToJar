package io.github.kusaanko.modmanager.mod125;

public class DigAll125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "[1.2.5]DigAll v");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20190715170405if_/https://forum.minecraftuser.jp/download/file.php?id=12119";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20190716004858/https://forum.minecraftuser.jp/viewtopic.php?f=13&t=2592&start=0";
    }

    @Override
    public String getName() {
        return "DigAll";
    }

    @Override
    public String getShortName() {
        return "DigAll";
    }

    @Override
    public String getAuthor() {
        return "masa";
    }

    @Override
    public String getVersion() {
        return "2.2";
    }

    @Override
    public String getDownloadFileName() {
        return "[1.2.5]DigAll v2.2.zip";
    }
}
