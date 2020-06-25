package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class OptiFineLocalizeFix125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "OptiFine_1.2.5_HD_U_C7_Localize_Fix");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsZ1lWWl9DbFAxZVE";
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
        return "OptiFineLocalizeFix";
    }

    @Override
    public String getShortName() {
        return "OptiFineLocalizeFix";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "(jar)";
    }

    @Override
    public String getDownloadFileName() {
        return "OptiFine_1.2.5_HD_U_C7_Localize_Fix(jar).zip";
    }
}
