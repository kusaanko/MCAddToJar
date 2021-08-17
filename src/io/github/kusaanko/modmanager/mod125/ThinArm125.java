package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class ThinArm125 extends Mod125 {
    @Override
    public String is(String fileName) {
        if(fileName.startsWith("sy_ThinArm125-") && fileName.contains("(jar)")) {
            return fileName.substring("sy_ThinArm125-".length(), fileName.lastIndexOf("(jar)"));
        }
        return null;
    }

    @Override
    public String getDownloadURL() {
        //return "https://drive.google.com/uc?id=0BzZbvRtrx7SsRGFhc29XOHdlTjg";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods/raw/main/takanasayo/jar/sy_ThinArm125-3(jar).zip";
    }

    @Override
    public String getDownloadPageURL() {
        //return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SsX0lGQlh1UTc2cVE";
        return "https://github.com/kusaanko/minecraft_1.2.5_mods";
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return null;
    }

    @Override
    public INSTALLATION_TYPE getInstallationType() {
        return INSTALLATION_TYPE.IN_JAR;
    }

    @Override
    public String getName() {
        return "ThinArm";
    }

    @Override
    public String getShortName() {
        return "ThinArm";
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
        return "sy_ThinArm125-3(jar).zip";
    }
}
