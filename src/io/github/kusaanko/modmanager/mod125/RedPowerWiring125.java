package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class RedPowerWiring125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "RedPowerWiring-");
    }

    @Override
    public String getDownloadURL() {
        return "http://www.eloraam.com/files/RedPowerWiring-2.0pr5b2.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://www.eloraam.com/download-redpower/";
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(RedPowerCore125.class);
    }

    @Override
    public TYPE getType() {
        return TYPE.ADDON;
    }

    @Override
    public String getName() {
        return "RedPower 2 Wiring";
    }

    @Override
    public String getShortName() {
        return "RP Wiring";
    }

    @Override
    public String getAuthor() {
        return "Eloraam";
    }

    @Override
    public String getVersion() {
        return "2.0pr5b2";
    }

    @Override
    public String getDownloadFileName() {
        return "RedPowerWiring-2.0pr5b2.zip";
    }
}
