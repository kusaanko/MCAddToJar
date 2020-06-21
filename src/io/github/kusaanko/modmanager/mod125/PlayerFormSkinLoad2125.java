package io.github.kusaanko.modmanager.mod125;

public class PlayerFormSkinLoad2125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "PlayerFormSkinLoad2-1_2_5-");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20190715150847if_/https://forum.minecraftuser.jp/download/file.php?id=9423";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20150127075650/http://forum.minecraftuser.jp/viewtopic.php?f=13&t=176";
    }

    @Override
    public String getName() {
        return "PlayerFormSkinLoad2";
    }

    @Override
    public String getShortName() {
        return "PlayerFormSkinLoad2";
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
        return "PlayerFormSkinLoad2-1_2_5-1.zip";
    }
}
