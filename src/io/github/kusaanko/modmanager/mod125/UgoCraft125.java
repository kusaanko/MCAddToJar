package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class UgoCraft125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "UgoCraft_Client_MC1.2.5-");
    }

    @Override
    public String getDownloadURL() {
        return "http://www.maocat.net/?download=10457";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://www.maocat.net/?page_id=5973";
    }

    @Override
    public boolean needUserDownload() {
        return true;
    }

    @Override
    public String getReferer() {
        return "http://www.maocat.net/?page_id=5973";
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods(Forge125.class, ModLoaderMP125.class);
    }

    @Override
    public String getName() {
        return "UgoCraft";
    }

    @Override
    public String getShortName() {
        return "UgoCraft";
    }

    @Override
    public String getAuthor() {
        return "Maocat";
    }

    @Override
    public String getVersion() {
        return "2.3.1";
    }

    @Override
    public String getDownloadFileName() {
        return "UgoCraft_Client_MC1.2.5-2.3.1.zip";
    }
}
