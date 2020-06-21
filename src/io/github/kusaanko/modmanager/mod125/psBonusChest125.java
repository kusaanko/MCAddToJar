package io.github.kusaanko.modmanager.mod125;

public class psBonusChest125 extends Mod125 {

    public String is(String fileName) {
        return startsWith(fileName, "psBonusChest");
    }

    @Override
    public String getDownloadURL() {
        return "http://minecraft125user.nisfan.net/forum/download/file.php?id=1243";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://minecraft125user.nisfan.net/forum/viewtopic.php?f=19&t=20#p40";
    }

    @Override
    public String getReferer() {
        return "http://minecraft125user.nisfan.net/forum/viewtopic.php?f=19&t=20";
    }

    @Override
    public String getName() {
        return "psBonusChest";
    }

    @Override
    public String getShortName() {
        return "psBonusChest";
    }

    @Override
    public String getAuthor() {
        return "A.K.";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String getDownloadFileName() {
        return "psBonusChest1.0.0.zip";
    }
}
