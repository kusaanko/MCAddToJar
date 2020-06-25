package io.github.kusaanko.modmanager.mod125;

public class TorchAttack125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mod_TorchAttack125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsWWdyN216RHhyNEk";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
    }

    @Override
    public String getName() {
        return "TorchAttack";
    }

    @Override
    public String getShortName() {
        return "TorchAttack";
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
        return "mod_TorchAttack125-1.zip";
    }
}
