package io.github.kusaanko.modmanager.mod125;

public class HalogenLight125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "[125]-mod_HalogenLight_v");
    }

    @Override
    public String getDownloadURL() {
        return "https://blog.wolfs.jp/downloads/minecraft/125/mod_HalogenLight_v103.zip";
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
        return "Halogen Light";
    }

    @Override
    public String getShortName() {
        return "Halogen Light";
    }

    @Override
    public String getAuthor() {
        return "REIMA";
    }

    @Override
    public String getVersion() {
        return "103";
    }

    @Override
    public String getDownloadFileName() {
        return "[125]-mod_HalogenLight_v103.zip";
    }
}
