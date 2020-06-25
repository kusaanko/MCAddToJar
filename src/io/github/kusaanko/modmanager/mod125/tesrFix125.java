package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class tesrFix125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "tesrFix125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=16jAGcflSGJBZmRn7ElxdyMEvsAJPLCPd";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://scrapbox.io/deerMods/";
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods(Forge125.class, MCAPI125.class);
    }

    @Override
    public INSTALLATION_TYPE getInstallationType() {
        return INSTALLATION_TYPE.IN_JAR;
    }

    @Override
    public String getName() {
        return "tesrFix";
    }

    @Override
    public String getShortName() {
        return "tesrFix";
    }

    @Override
    public String getAuthor() {
        return "Deer05";
    }

    @Override
    public String getVersion() {
        return "4";
    }

    @Override
    public String getDownloadFileName() {
        return "tesrFix125-4.zip";
    }
}
