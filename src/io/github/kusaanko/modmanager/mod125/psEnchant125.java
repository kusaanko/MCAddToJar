package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class psEnchant125 extends Mod125 {

    public String is(String fileName) {
        return startsWith(fileName, "psEnchant");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsbkNPU1RQOWVjY2c";
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
        return "psEnchant";
    }

    @Override
    public String getShortName() {
        return "psEnchant";
    }

    @Override
    public String getAuthor() {
        return "A.K.";
    }

    @Override
    public String getVersion() {
        return "2.0.16.170520_sayo";
    }

    @Override
    public String getDownloadFileName() {
        return "psEnchant2.0.16.170520_sayo.zip";
    }
}
