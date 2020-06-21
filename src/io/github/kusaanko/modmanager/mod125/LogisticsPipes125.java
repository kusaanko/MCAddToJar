package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class LogisticsPipes125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "LogisticsPipes-BC3-");
    }

    @Override
    public String getDownloadURL() {
        return "https://github.com/TechnicPack/Technic/raw/master/mods/buildcraft-logisticspipes/buildcraft-logisticspipes-v0.2.5b.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://github.com/TechnicPack/Technic/blob/master/mods/buildcraft-logisticspipes/buildcraft-logisticspipes-v0.2.5b.zip";
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(BCx125.class);
    }

    @Override
    public PROCESS_TYPE getProcessType() {
        return PROCESS_TYPE.UNZIP;
    }

    @Override
    public String[] getUnzipFiles() {
        return new String[]{"mods/LogisticsPipes-BC3-0.2.5B.zip"};
    }

    @Override
    public String getName() {
        return "LogisticsPipes";
    }

    @Override
    public String getShortName() {
        return "LogisticsPipes";
    }

    @Override
    public String getAuthor() {
        return "Krapht";
    }

    @Override
    public String getVersion() {
        return "0.2.5B";
    }

    @Override
    public String getDownloadFileName() {
        return "LogisticsPipes-BC3-0.2.5B.zip";
    }
}
