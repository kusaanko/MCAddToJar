package io.github.kusaanko.modmanager.mod125;

public class Tachikoma125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "Tachikoma-1_2_5-");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20160706214430if_/http://forum.minecraftuser.jp/download/file.php?id=9427";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20150127075650/http://forum.minecraftuser.jp/viewtopic.php?f=13&t=176";
    }

    @Override
    public String getName() {
        return "Tachikoma";
    }

    @Override
    public String getShortName() {
        return "Tachikoma";
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
        return "Tachikoma-1_2_5-1.zip";
    }
}
