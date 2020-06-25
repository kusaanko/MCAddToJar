package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class OptiFineHDRendererFix125 extends Mod125 {
    @Override
    public String is(String fileName) {
        if(fileName.startsWith("OptiFine_1.2.5_HD_U_C7_HDRendererFix-") && fileName.contains("(jar)")) {
            return fileName.substring("OptiFine_1.2.5_HD_U_C7_HDRendererFix-".length(), fileName.lastIndexOf("(jar)"));
        }
        return null;
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsNEhrVjFMem13UkU";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SsX0lGQlh1UTc2cVE";
    }

    @Override
    public INSTALLATION_TYPE getInstallationType() {
        return INSTALLATION_TYPE.IN_JAR;
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods(OptiFine125.class);
    }

    @Override
    public String getName() {
        return "OptiFineHDRendererFix";
    }

    @Override
    public String getShortName() {
        return "OptiFineHDRendererFix";
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
        return "OptiFine_1.2.5_HD_U_C7_HDRendererFix-3(jar).zip";
    }
}
