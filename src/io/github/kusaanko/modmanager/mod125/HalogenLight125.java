package io.github.kusaanko.modmanager.mod125;

public class HalogenLight125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mod_HalogenLight_v");
    }

    @Override
    public String getDownloadURL() {
        return "https://blog.wolfs.jp/downloads/minecraft/125/mod_HalogenLight_v103.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://blog.wolfs.jp/down/minecraft/oldversion/";
    }

    @Override
    public String getReferer() {
        return "https://blog.wolfs.jp/down/minecraft/oldversion/";
    }

    @Override
    public PROCESS_TYPE getProcessType() {
        return PROCESS_TYPE.UNZIP;
    }

    @Override
    public String[] getUnzipFiles() {
        return new String[]{"mod_HalogenLight_v103_forge.zip"};
    }

    @Override
    public String getName() {
        return "Halogen Light";
    }

    @Override
    public String getShortName() {
        return "Halogen Light";
    }

    @Override
    public String getAuthor() {
        return "REIMA";
    }

    @Override
    public String getVersion() {
        return "103_forge";
    }

    @Override
    public String getDownloadFileName() {
        return "mod_HalogenLight_v103_forge.zip";
    }
}
