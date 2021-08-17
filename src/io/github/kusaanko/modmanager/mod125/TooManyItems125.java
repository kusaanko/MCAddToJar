package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class TooManyItems125 extends Mod125 {

    public String is(String fileName) {
        return startsWith(fileName, "TooManyItems");
    }

    @Override
    public String getDownloadURL() {
        return "https://sites.google.com/site/bimasterscsarchive/downloads/TooManyItems%201.2.5.zip?attredirects=0&d=1";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1272385-toomanyitems-the-inventory-editor-and-more-1-8";
    }

    @Override
    public INSTALLATION_TYPE getInstallationType() {
        return INSTALLATION_TYPE.IN_JAR;
    }

    @Override
    public String getName() {
        return "TooManyItems";
    }

    @Override
    public String getShortName() {
        return "TMI";
    }

    @Override
    public String getAuthor() {
        return "Marglyph";
    }

    @Override
    public String getVersion() {
        return "2012_04_13_1.2.5";
    }

    @Override
    public String getDownloadFileName() {
        return "TooManyItems2012_04_13_1.2.5.zip";
    }
}
