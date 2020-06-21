package io.github.kusaanko.modmanager.mod125;

public class Column125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "[125]-mod_Column_v");
    }

    @Override
    public String getDownloadURL() {
        return "https://blog.wolfs.jp/downloads/minecraft/125/mod_Column_v121.zip";
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
        return "Column";
    }

    @Override
    public String getShortName() {
        return "Column";
    }

    @Override
    public String getAuthor() {
        return "REIMA";
    }

    @Override
    public String getVersion() {
        return "121";
    }

    @Override
    public String getDownloadFileName() {
        return "[125]-mod_Column_v121.zip";
    }
}
