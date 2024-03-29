package io.github.kusaanko.modmanager.mod125;

public class BigTree125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_BigTree125-");
    }

    @Override
    public String getDownloadURL() {
        //return "https://drive.google.com/uc?id=0BzZbvRtrx7SsU053NzNYY1l0WEU";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods/raw/main/takanasayo/sy_BigTree125-1.zip";
    }

    @Override
    public String getDownloadPageURL() {
        //return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods";
    }

    @Override
    public String getName() {
        return "BigTree";
    }

    @Override
    public String getShortName() {
        return "BigTree";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "1";
    }

    @Override
    public String getDownloadFileName() {
        return "sy_BigTree125-1.zip";
    }
}
