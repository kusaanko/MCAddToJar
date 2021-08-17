package io.github.kusaanko.modmanager.mod125;

public class DirectionAdjustment125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_DirectionAdjustment125-");
    }

    @Override
    public String getDownloadURL() {
        //return "https://drive.google.com/uc?id=0BzZbvRtrx7SsVW1YMzk5Zlh1M0k";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods/raw/main/takanasayo/sy_DirectionAdjustment125-1.zip";
    }

    @Override
    public String getDownloadPageURL() {
        //return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods";
    }

    @Override
    public String getName() {
        return "DirectionAdjustment";
    }

    @Override
    public String getShortName() {
        return "DirectionAdjustment";
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
        return "sy_DirectionAdjustment125-1.zip";
    }
}
