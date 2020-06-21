package io.github.kusaanko.modmanager.mod125;

public class IKATORITame125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "IKATORITame-1_2_5-");
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
        return "イカテイム ＋ トリテイム";
    }

    @Override
    public String getShortName() {
        return "IKATORITame";
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
        return "IKATORITame-1_2_5-2.zip";
    }
}
