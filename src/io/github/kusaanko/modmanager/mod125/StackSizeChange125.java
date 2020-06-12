package io.github.kusaanko.modmanager.mod125;

public class StackSizeChange125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mod_StackSizeChange125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsUUxLOHF2Y0NpRGs";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
    }

    @Override
    public String getName() {
        return "StackSizeChange";
    }

    @Override
    public String getShortName() {
        return "StackSizeChange";
    }

    @Override
    public String getAuthor() {
        return "さよ";
    }

    @Override
    public String getVersion() {
        return "13c";
    }

    @Override
    public String getDownloadFileName() {
        return "mod_StackSizeChange125-13c.zip";
    }
}
