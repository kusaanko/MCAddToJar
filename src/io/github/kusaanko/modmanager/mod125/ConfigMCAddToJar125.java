package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

import java.io.InputStream;

public class ConfigMCAddToJar125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "config_MCAddToJar_");
    }

    @Override
    public String getDownloadURL() {
        return "";
    }

    @Override
    public String getDownloadPageURL() {
        return "";
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods();
    }

    @Override
    public TYPE getType() {
        return TYPE.CONFIG;
    }

    @Override
    public INSTALLATION_TYPE getInstallationType() {
        return INSTALLATION_TYPE.OTHER_FOLDER;
    }

    @Override
    public String getInstallationFolder() {
        return "";
    }

    @Override
    public InputStream getConfigInputStream() {
        return getClass().getResourceAsStream("/io/github/kusaanko/configs/config_1.2.5.archive");
    }

    @Override
    public String getName() {
        return "Config by MCAddToJar";
    }

    @Override
    public String getShortName() {
        return "Config by MCAddToJar";
    }

    @Override
    public String getAuthor() {
        return "kusaanko";
    }

    @Override
    public String getVersion() {
        return "1";
    }

    @Override
    public String getDownloadFileName() {
        return "mods/config_MCAddToJar_1.zip";
    }
}
