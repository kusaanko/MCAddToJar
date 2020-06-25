package io.github.kusaanko.modmanager.mod125;

public class HarvestLevelSetter125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mod_HarvestLevelSetter125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsWDJXeF85bExVd2c";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
    }

    @Override
    public String getName() {
        return "HarvestLevelSetter";
    }

    @Override
    public String getShortName() {
        return "HarvestLevelSetter";
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
        return "mod_HarvestLevelSetter125-1.zip";
    }
}
