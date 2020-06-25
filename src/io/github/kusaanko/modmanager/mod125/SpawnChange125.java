package io.github.kusaanko.modmanager.mod125;

public class SpawnChange125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_SpawnChange125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsRU9rNG5ud0MtbnM";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
    }

    @Override
    public String getName() {
        return "SpawnChange";
    }

    @Override
    public String getShortName() {
        return "SpawnChange";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "4";
    }

    @Override
    public String getDownloadFileName() {
        return "sy_SpawnChange125-4.zip";
    }
}
