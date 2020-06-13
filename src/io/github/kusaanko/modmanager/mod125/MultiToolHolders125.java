package io.github.kusaanko.modmanager.mod125;

public class MultiToolHolders125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "[1.2.5]MultiToolHolders");
    }

    @Override
    public String getDownloadURL() {
        return "https://dl.dropboxusercontent.com/s/ucrb5m8wt86c6u8/[1.2.5]MultiToolHolders1.2b.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://minecraft125user.nisfan.net/forum/viewtopic.php?f=12&t=26";
    }

    @Override
    public String getName() {
        return "ツールホルダーMOD";
    }

    @Override
    public String getShortName() {
        return "ツールホルダーMOD";
    }

    @Override
    public String getAuthor() {
        return "A.K.";
    }

    @Override
    public String getVersion() {
        return "1.2b";
    }

    @Override
    public String getDownloadFileName() {
        return "[1.2.5]MultiToolHolders1.2b.zip";
    }
}
