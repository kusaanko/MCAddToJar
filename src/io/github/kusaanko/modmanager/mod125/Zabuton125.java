package io.github.kusaanko.modmanager.mod125;

public class Zabuton125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "Zabuton-1_2_5-");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20190716011932if_/https://forum.minecraftuser.jp/download/file.php?id=9738";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20150127075650/http://forum.minecraftuser.jp/viewtopic.php?f=13&t=176";
    }

    @Override
    public String getName() {
        return "Zabuton";
    }

    @Override
    public String getShortName() {
        return "Zabuton";
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
        return "Zabuton-1_2_5-1.zip";
    }
}
