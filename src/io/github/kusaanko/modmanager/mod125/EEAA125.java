package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class EEAA125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mod_EEAddon-AdvancedAlchemy");
    }

    @Override
    public String getDownloadURL() {
        return "https://dl.dropboxusercontent.com/s/ycp8hx7qwizzgyz/mod_EEAddon-AdvancedAlchemy1.6y.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://minecraft125user.nisfan.net/forum/viewtopic.php?f=12&t=27";
    }

    @Override
    public TYPE getType() {
        return TYPE.ADDON;
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(EE2125.class);
    }

    @Override
    public String getName() {
        return "EEAddon Advanced Alchemy";
    }

    @Override
    public String getShortName() {
        return "EEAA";
    }

    @Override
    public String getAuthor() {
        return "A.K.";
    }

    @Override
    public String getVersion() {
        return "1.6y";
    }

    @Override
    public String getDownloadFileName() {
        return "mod_EEAddon-AdvancedAlchemy1.6y.zip";
    }
}
