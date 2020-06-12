package io.github.kusaanko.modmanager.mod125;

public class AccessChest125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_AccessChest125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsZ0hzSkdwR042dW8";
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
        return "さよ";
    }

    @Override
    public String getVersion() {
        return "4.160608";
    }

    @Override
    public String getDownloadFileName() {
        return "sy_AccessChest125-4.160608.zip";
    }
}
