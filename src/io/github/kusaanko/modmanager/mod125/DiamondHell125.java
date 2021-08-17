package io.github.kusaanko.modmanager.mod125;

public class DiamondHell125 extends Mod125 {
    @Override
    public String is(String fileName) {
        if(fileName.startsWith("sy_DiamondHell 125-") && fileName.contains("(jar)")) {
            return fileName.substring("sy_DiamondHell 125-".length(), fileName.lastIndexOf("(jar)"));
        }
        return null;
    }

    @Override
    public String getDownloadURL() {
        //return "https://drive.google.com/uc?id=0BzZbvRtrx7Ssdm8ySlpfYVJ5b2M";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods/raw/main/takanasayo/jar/sy_DiamondHell%20125-2(jar).zip";
    }

    @Override
    public String getDownloadPageURL() {
        //return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SsX0lGQlh1UTc2cVE";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods";
    }

    @Override
    public INSTALLATION_TYPE getInstallationType() {
        return INSTALLATION_TYPE.IN_JAR;
    }

    @Override
    public String getName() {
        return "DiamondHell";
    }

    @Override
    public String getShortName() {
        return "DiamondHell";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "2";
    }

    @Override
    public String getDownloadFileName() {
        return "sy_DiamondHell 125-2(jar).zip";
    }
}
