package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class SimpleGameInfo125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "simpleGameInfo125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=1unxB4DmTDvN0K-wOE1wbqloDoeRiLzvX";
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
    public String getName() {
        return "SimpleGameInfo";
    }

    @Override
    public String getShortName() {
        return "SimpleGameInfo";
    }

    @Override
    public String getAuthor() {
        return "Deer05";
    }

    @Override
    public String getVersion() {
        return "1";
    }

    @Override
    public String getDownloadFileName() {
        return "simpleGameInfo125-1.zip";
    }
}
