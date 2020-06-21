package io.github.kusaanko.modmanager.mod125;

public class PickupWidely125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "[1.2.5]PickupWidely v");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20190715170406if_/https://forum.minecraftuser.jp/download/file.php?id=9647";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20190716004858/https://forum.minecraftuser.jp/viewtopic.php?f=13&t=2592&start=0";
    }

    @Override
    public String getName() {
        return "PickupWidely";
    }

    @Override
    public String getShortName() {
        return "PickupWidely";
    }

    @Override
    public String getAuthor() {
        return "masa";
    }

    @Override
    public String getVersion() {
        return "1.2";
    }

    @Override
    public String getDownloadFileName() {
        return "[1.2.5]PickupWidely v1.2.zip";
    }
}
