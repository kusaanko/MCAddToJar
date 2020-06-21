package io.github.kusaanko.modmanager.mod125;

public class FullcolorBlocks125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "[125]-mod_FullcolorBlocks_v");
    }

    @Override
    public String getDownloadURL() {
        return "https://blog.wolfs.jp/downloads/minecraft/125/mod_FullcolorBlocks_v102.zip";
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
        return "Fullcolor Blocks";
    }

    @Override
    public String getShortName() {
        return "Fullcolor Blocks";
    }

    @Override
    public String getAuthor() {
        return "REIMA";
    }

    @Override
    public String getVersion() {
        return "102";
    }

    @Override
    public String getDownloadFileName() {
        return "[125]-mod_FullcolorBlocks_v102.zip";
    }
}
