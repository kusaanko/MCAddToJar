package io.github.kusaanko.modmanager.mod125;

public class ReverseCraft125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "[1.2.5]mod_ReverseCrafting_v");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20190715133802if_/https://forum.minecraftuser.jp/download/file.php?id=16056";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20180413111616/http://forum.minecraftuser.jp/viewtopic.php?t=3629";
    }

    @Override
    public String getName() {
        return "ReverseCraft";
    }

    @Override
    public String getShortName() {
        return "ReverseCraft";
    }

    @Override
    public String getAuthor() {
        return "Unyuho";
    }

    @Override
    public String getVersion() {
        return "2.0.9";
    }

    @Override
    public String getDownloadFileName() {
        return "[1.2.5]mod_ReverseCrafting_v2.0.9.zip";
    }
}
