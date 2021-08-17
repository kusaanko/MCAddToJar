package io.github.kusaanko.modmanager.mod125;

public class Portalgun125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "portalgun1.2.5v");
    }

    @Override
    public String getDownloadURL() {
        //return "https://drive.google.com/uc?id=0BzZbvRtrx7SsWVc1VVdaQm5Ga3c";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods/raw/main/takanasayo/portalgun_MCAPI%E5%AF%BE%E5%BF%9C%E7%89%88%20(%E4%BA%8C%E9%87%8Dzip).zip";
    }

    @Override
    public String getDownloadPageURL() {
        //return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods";
    }

    @Override
    public PROCESS_TYPE getProcessType() {
        return PROCESS_TYPE.UNZIP;
    }

    @Override
    public String[] getUnzipFiles() {
        return new String[]{"portalgun", "portalgun1.2.5v3.zip"};
    }

    @Override
    public String getUnzipCharset() {
        return "Shift-JIS";
    }

    @Override
    public String getName() {
        return "Portalgun";
    }

    @Override
    public String getShortName() {
        return "Portalgun";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "3";
    }

    @Override
    public String getDownloadFileName() {
        return "portalgun1.2.5v3.zip";
    }
}
