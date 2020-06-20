package io.github.kusaanko.modmanager.mod125;

public class RedPowerCore125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "RedPowerCore-");
    }

    @Override
    public String getDownloadURL() {
        return "http://www.eloraam.com/files/RedPowerCore-2.0pr5b2.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://www.eloraam.com/download-redpower/";
    }

    @Override
    public String getName() {
        return "RedPower 2 Core";
    }

    @Override
    public String getShortName() {
        return "RP Core";
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
        return "RedPowerCore-2.0pr5b2.zip";
    }
}
