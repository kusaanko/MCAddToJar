package io.github.kusaanko.modmanager.mod125;

public class littleMaidMob125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_littleMaidMob125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsQWJycHJNclh0cTg";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
    }

    @Override
    public String getName() {
        return "littleMaidMob";
    }

    @Override
    public String getShortName() {
        return "littleMaidMob";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "6.1008";
    }

    @Override
    public String getDownloadFileName() {
        return "sy_littleMaidMob125-6.1008.zip";
    }
}
