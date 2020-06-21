package io.github.kusaanko.modmanager.mod125;

public class CutAll125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "[1.2.5]CutAll v");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20190715170404if_/https://forum.minecraftuser.jp/download/file.php?id=12120";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20190716004858/https://forum.minecraftuser.jp/viewtopic.php?f=13&t=2592&start=0";
    }

    @Override
    public String getName() {
        return "CutAll";
    }

    @Override
    public String getShortName() {
        return "CutAll";
    }

    @Override
    public String getAuthor() {
        return "masa";
    }

    @Override
    public String getVersion() {
        return "2.4";
    }

    @Override
    public String getDownloadFileName() {
        return "[1.2.5]CutAll v2.4.zip";
    }
}
