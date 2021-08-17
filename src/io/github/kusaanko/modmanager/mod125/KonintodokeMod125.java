package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class KonintodokeMod125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mod_konintodokemod_");
    }

    @Override
    public INSTALLATION_TYPE getInstallationType() {
        return INSTALLATION_TYPE.MODS_FOLDER;
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods(Forge125.class);
    }

    @Override
    public String getDownloadURL() {
        return null;
    }

    @Override
    public String getDownloadPageURL() {
        return "https://ux.getuploader.com/syuryo_mod/download/2/mod_konintodokemod_beta9.zip";
    }

    @Override
    public boolean needUserDownload() {
        return true;
    }

    @Override
    public String getName() {
        return "婚姻届MOD";
    }

    @Override
    public String getShortName() {
        return "婚姻届MOD";
    }

    @Override
    public String getAuthor() {
        return "秋涼";
    }

    @Override
    public String getVersion() {
        return "beta9";
    }

    @Override
    public String getDownloadFileName() {
        return "mod_konintodokemod_beta9.zip";
    }
}
