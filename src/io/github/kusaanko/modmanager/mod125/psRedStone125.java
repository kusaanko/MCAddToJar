package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class psRedStone125 extends Mod125 {

    public String is(String fileName) {
        return startsWith(fileName, "psRedStone");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsVkpXY1l0ei0xTk0";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SsYmR1Mk1SbFlmanM";
    }

    @Override
    public String getReferer() {
        return "http://minecraft125user.nisfan.net/forum/viewtopic.php?f=19&t=20";
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods(Forge125.class, MCAPI125.class);
    }

    @Override
    public String getName() {
        return "psRedStone";
    }

    @Override
    public String getShortName() {
        return "psRedStone";
    }

    @Override
    public String getAuthor() {
        return "A.K.";
    }

    @Override
    public String getVersion() {
        return "2.0.11.150626_sayo";
    }

    @Override
    public String getDownloadFileName() {
        return "psRedStone2.0.11.150626_sayo.zip";
    }
}
