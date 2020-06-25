package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class InvTweaks125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_InvTweaks125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=0BzZbvRtrx7SsRmt6dk9xN2JnMDg";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods(Forge125.class, MCAPI125.class);
    }

    @Override
    public String getName() {
        return "InvTweaks";
    }

    @Override
    public String getShortName() {
        return "InvTweaks";
    }

    @Override
    public String getAuthor() {
        return "takanasayo";
    }

    @Override
    public String getVersion() {
        return "1.160907";
    }

    @Override
    public String getDownloadFileName() {
        return "sy_InvTweaks125-1.160907.zip";
    }
}
