package io.github.kusaanko.modmanager.mod125;

public class Portalgun125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "portalgun1.2.5v");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsWVc1VVdaQm5Ga3c";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
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
        return "さよ";
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
