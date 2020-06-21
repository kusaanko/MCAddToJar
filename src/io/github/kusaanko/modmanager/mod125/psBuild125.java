package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class psBuild125 extends Mod125 {

    public String is(String fileName) {
        return startsWith(fileName, "psBuild");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=1q5xJwYsnFJMLKVMbydJSLXfZ00V321j2";
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
        return "psBuild";
    }

    @Override
    public String getShortName() {
        return "psBuild";
    }

    @Override
    public String getAuthor() {
        return "Deer05";
    }

    @Override
    public String getVersion() {
        return "2.0.17.190422_sayo";
    }

    @Override
    public String getDownloadFileName() {
        return "psBuild2.0.17.190422_sayo.zip";
    }
}
