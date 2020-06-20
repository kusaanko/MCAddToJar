package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class MultiPageChest125 extends Mod125 {
    @Override
    public String is(String fileName) {
        if(fileName.startsWith("multiPageChest_") && fileName.contains("Client")) {
            return fileName.substring("multiPageChest_".length(), fileName.lastIndexOf("_Client"));
        }
        return null;
    }

    @Override
    public String getDownloadURL() {
        return "http://download1584.mediafire.com/00yrma6a89vg/ba4m4fau4547lt8/multiPageChest_1.0.12_Client.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1275500-cubex2s-mods-all-mods-available-for-1-8-9";
    }

    @Override
    public String getName() {
        return "MultiPageChest";
    }

    @Override
    public String getShortName() {
        return "MultiPageChest";
    }

    @Override
    public String getAuthor() {
        return "CubeX2";
    }

    @Override
    public String getVersion() {
        return "1.0.12";
    }

    @Override
    public String getDownloadFileName() {
        return "multiPageChest_1.0.12_Client.zip";
    }
}
