package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class GroupGuiLib125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "groupGuiLib125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=1NGpY2QojJpsHhBPP1mPbKgVL7rv0zSrB";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://scrapbox.io/deerMods/";
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods(Forge125.class, MCAPI125.class);
    }

    @Override
    public TYPE getType() {
        return TYPE.REQUIREMENTS;
    }

    @Override
    public String getName() {
        return "GroupGuiLib";
    }

    @Override
    public String getShortName() {
        return "GroupGuiLib";
    }

    @Override
    public String getAuthor() {
        return "Deer05";
    }

    @Override
    public String getVersion() {
        return "3.1";
    }

    @Override
    public String getDownloadFileName() {
        return "groupGuiLib125-3.1.zip";
    }
}
