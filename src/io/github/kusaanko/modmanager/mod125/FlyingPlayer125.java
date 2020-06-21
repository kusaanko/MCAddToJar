package io.github.kusaanko.modmanager.mod125;

public class FlyingPlayer125 extends Mod125 {
    @Override
    public String is(String fileName) {
        if(fileName.startsWith("[1.2.5]FlyingPlayer")) {
            return "1";
        }
        return null;
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20190715170400if_/https://forum.minecraftuser.jp/download/file.php?id=10630";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20190716004858/https://forum.minecraftuser.jp/viewtopic.php?f=13&t=2592&start=0";
    }

    @Override
    public String getName() {
        return "FlyingPlayer";
    }

    @Override
    public String getShortName() {
        return "FlyingPlayer";
    }

    @Override
    public String getAuthor() {
        return "masa";
    }

    @Override
    public String getVersion() {
        return "1";
    }

    @Override
    public String getDownloadFileName() {
        return "[1.2.5]FlyingPlayer.zip";
    }
}
