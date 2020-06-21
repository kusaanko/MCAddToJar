package io.github.kusaanko.modmanager.mod125;

public class mobEgg125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mobEgg-1_2_5-");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20160706210051if_/http://forum.minecraftuser.jp/download/file.php?id=9739";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20150127075650/http://forum.minecraftuser.jp/viewtopic.php?f=13&t=176";
    }

    @Override
    public String getName() {
        return "mobEgg";
    }

    @Override
    public String getShortName() {
        return "mobEgg";
    }

    @Override
    public String getAuthor() {
        return "MMM";
    }

    @Override
    public String getVersion() {
        return "2";
    }

    @Override
    public String getDownloadFileName() {
        return "mobEgg-1_2_5-2.zip";
    }
}
