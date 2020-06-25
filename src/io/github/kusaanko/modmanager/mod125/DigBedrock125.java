package io.github.kusaanko.modmanager.mod125;

public class DigBedrock125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mod_DigBedrock125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SseEVJaVN6QjVJb1E";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
    }

    @Override
    public String getName() {
        return "DigBedrock";
    }

    @Override
    public String getShortName() {
        return "DigBedrock";
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
        return "mod_DigBedrock125-1.zip";
    }
}
