package io.github.kusaanko.modmanager.mod125;

public class SelectablePaintings125 extends Mod125 {
    @Override
    public String is(String fileName) {
        if(fileName.startsWith("[125]-mod_SelectablePaintings_v")) {
            return fileName.substring("[125]-mod_SelectablePaintings_v".length(), fileName.indexOf("_forge_for_client"));
        }
        return null;
    }

    @Override
    public String getDownloadURL() {
        return "https://blog.wolfs.jp/downloads/minecraft/125/mod_SelectablePaintings_v106b_forge_for_client.zip";
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
        return "Selectable Paintings";
    }

    @Override
    public String getShortName() {
        return "Selectable Paintings";
    }

    @Override
    public String getAuthor() {
        return "REIMA";
    }

    @Override
    public String getVersion() {
        return "106b";
    }

    @Override
    public String getDownloadFileName() {
        return "[125]-mod_SelectablePaintings_v106b_forge_for_client.zip";
    }
}
