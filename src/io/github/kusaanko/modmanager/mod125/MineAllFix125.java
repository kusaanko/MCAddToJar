package io.github.kusaanko.modmanager.mod125;

public class MineAllFix125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mod_MineAll_Fix-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7Ssb0drODE0cmlIRzA";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
    }

    @Override
    public String getName() {
        return "MineAll Fix";
    }

    @Override
    public String getShortName() {
        return "MineAllFix";
    }

    @Override
    public String getAuthor() {
        return "さよ";
    }

    @Override
    public String getVersion() {
        return "2";
    }

    @Override
    public String getDownloadFileName() {
        return "mod_MineAll_Fix-2.zip";
    }
}
