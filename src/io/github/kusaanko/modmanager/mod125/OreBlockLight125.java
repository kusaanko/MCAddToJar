package io.github.kusaanko.modmanager.mod125;

public class OreBlockLight125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mod_OreBlockLight125-");
    }

    @Override
    public String getDownloadURL() {
        //return "https://drive.google.com/uc?id=0BzZbvRtrx7SsUnlSeWpHa3A3NEE";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods/raw/main/takanasayo/mod_OreBlockLight125-2.zip";
    }

    @Override
    public String getDownloadPageURL() {
        //return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods";
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
        return "takanasayo";
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
