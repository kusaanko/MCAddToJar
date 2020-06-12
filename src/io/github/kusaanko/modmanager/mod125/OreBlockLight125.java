package io.github.kusaanko.modmanager.mod125;

public class OreBlockLight125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mod_OreBlockLight125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsUnlSeWpHa3A3NEE";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
    }

    @Override
    public String getName() {
        return "OreBlockLight";
    }

    @Override
    public String getShortName() {
        return "OreBlockLight";
    }

    @Override
    public String getAuthor() {
        return "さよ";
    }

    @Override
    public String getVersion() {
        return "2";
    }

    @Override
    public String getDownloadFileName() {
        return "mod_OreBlockLight125-2.zip";
    }
}
