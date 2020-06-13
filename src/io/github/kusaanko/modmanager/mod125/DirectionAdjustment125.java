package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class DirectionAdjustment125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_DirectionAdjustment125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsVW1YMzk5Zlh1M0k";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
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
        return "さよ";
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
