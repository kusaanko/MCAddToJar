package io.github.kusaanko.modmanager.mod125;

public class GlowstonePlus125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "[124]-mod_GlowstonePlus_v");
    }

    @Override
    public String getDownloadURL() {
        return "https://blog.wolfs.jp/downloads/minecraft/124/mod_GlowstonePlus_v100.zip";
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
        return "Glowstone Plus";
    }

    @Override
    public String getShortName() {
        return "Glowstone Plus";
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
        return "[124]-mod_GlowstonePlus_v100.zip";
    }
}
