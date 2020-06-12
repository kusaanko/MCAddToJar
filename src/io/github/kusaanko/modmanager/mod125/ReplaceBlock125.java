package io.github.kusaanko.modmanager.mod125;

public class ReplaceBlock125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mod_ReplaceBlock125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsbXhyLUtQcmVqMEk";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
    }

    @Override
    public String getName() {
        return "ReplaceBlock";
    }

    @Override
    public String getShortName() {
        return "ReplaceBlock";
    }

    @Override
    public String getAuthor() {
        return "さよ";
    }

    @Override
    public String getVersion() {
        return "4";
    }

    @Override
    public String getDownloadFileName() {
        return "mod_ReplaceBlock125-4.zip";
    }
}
