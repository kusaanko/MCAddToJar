package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class MoreFire125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_MoreFire125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsaGNvZE9MMUZhVUE";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
    }

    @Override
    public String getName() {
        return "MoreFire";
    }

    @Override
    public String getShortName() {
        return "MoreFire";
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
        return "sy_MoreFire125-1.zip";
    }
}
