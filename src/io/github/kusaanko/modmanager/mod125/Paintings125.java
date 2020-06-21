package io.github.kusaanko.modmanager.mod125;

public class Paintings125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "[125]-mod_Paintings_v");
    }

    @Override
    public String getDownloadURL() {
        return "https://blog.wolfs.jp/downloads/minecraft/125/mod_Paintings_v100.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://blog.wolfs.jp/down/minecraft/oldversion/";
    }

    @Override
    public String getReferer() {
        return "https://blog.wolfs.jp/down/minecraft/oldversion/";
    }

    @Override
    public String getName() {
        return "Paintings";
    }

    @Override
    public String getShortName() {
        return "Paintings";
    }

    @Override
    public String getAuthor() {
        return "REIMA";
    }

    @Override
    public String getVersion() {
        return "100";
    }

    @Override
    public String getDownloadFileName() {
        return "[125]-mod_Paintings_v100.zip";
    }
}
