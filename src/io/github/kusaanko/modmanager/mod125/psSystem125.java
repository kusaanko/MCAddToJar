package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class psSystem125 extends Mod125 {

    public String is(String fileName) {
        return startsWith(fileName, "psSystem");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsX0VtOVBIUTJPVjQ";
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
        return "psSystem";
    }

    @Override
    public String getShortName() {
        return "psSystem";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "2.0.33.170405";
    }

    @Override
    public String getDownloadFileName() {
        return "psSystem2.0.33.170405.zip";
    }
}
