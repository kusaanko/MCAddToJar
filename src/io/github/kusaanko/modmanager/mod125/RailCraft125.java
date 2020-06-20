package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class RailCraft125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "Railcraft_Client_");
    }

    @Override
    public String getDownloadURL() {
        return "https://github.com/TechnicPack/Technic/raw/master/mods/rc/rc-v5.4.7b.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20130126213115/http://railcraft.wikispaces.com/Version+5.4.7";
    }

    @Override
    public PROCESS_TYPE getProcessType() {
        return PROCESS_TYPE.UNZIP;
    }

    @Override
    public String getUnzipFile() {
        return "mods/LogisticsPipes-BC3-0.2.5B.zip";
    }

    @Override
    public String getName() {
        return "RailCraft";
    }

    @Override
    public String getShortName() {
        return "RailCraft";
    }

    @Override
    public String getAuthor() {
        return "CovertJaguar";
    }

    @Override
    public String getVersion() {
        return "5.4.7b";
    }

    @Override
    public String getDownloadFileName() {
        return "Railcraft_Client_5.4.7b.zip";
    }
}
