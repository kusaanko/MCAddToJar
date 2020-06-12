package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class CutAllFix125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mod_CutAll_Fix-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsOGhsRk90S2R6MHM";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
    }

    @Override
    public String getName() {
        return "CutAll Fix";
    }

    @Override
    public String getShortName() {
        return "CutAllFix";
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
        return "mod_CutAll_Fix-1.zip";
    }
}
