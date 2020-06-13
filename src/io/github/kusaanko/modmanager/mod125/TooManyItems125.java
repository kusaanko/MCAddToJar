package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class TooManyItems125 extends Mod125 {

    public String is(String fileName) {
        return startsWith(fileName, "TooManyItems");
    }

    @Override
    public String getDownloadURL() {
        return null;
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
    }

    @Override
    public boolean needUserDownload() {
        return true;
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
