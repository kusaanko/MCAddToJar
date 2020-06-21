package io.github.kusaanko.modmanager.mod125;

public class BlockLogDirectional125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "BlockLogDirectional-1_2_5-");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20160706211904if_/http://forum.minecraftuser.jp/download/file.php?id=12524";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20150127075650/http://forum.minecraftuser.jp/viewtopic.php?f=13&t=176";
    }

    @Override
    public String getName() {
        return "BlockLogDirectional";
    }

    @Override
    public String getShortName() {
        return "BlockLogDirectional";
    }

    @Override
    public String getAuthor() {
        return "MMM";
    }

    @Override
    public String getVersion() {
        return "3a";
    }

    @Override
    public String getDownloadFileName() {
        return "BlockLogDirectional-1_2_5-3a.zip";
    }
}
