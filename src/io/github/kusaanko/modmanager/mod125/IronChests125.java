package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class IronChests125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mod_ironchests-client-");
    }

    @Override
    public String getDownloadURL() {
        return "http://download1583.mediafire.com/hd8r5de0v5lg/bmm52o2lwvbz0z2/mod_ironchests-client-3.8.0.40.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1280827-1-5-and-up-forge-universal-ironchests-5-0";
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(BCx125.class);
    }

    @Override
    public String getName() {
        return "Iron Chests";
    }

    @Override
    public String getShortName() {
        return "Iron Chests";
    }

    @Override
    public String getAuthor() {
        return "cpw";
    }

    @Override
    public String getVersion() {
        return "3.8.0.40";
    }

    @Override
    public String getDownloadFileName() {
        return "mod_ironchests-client-3.8.0.40.zip";
    }
}
