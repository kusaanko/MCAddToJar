package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class TinyTeleporter125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "sy_TinyTeleporter125-");
    }

    @Override
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=1EnNerSUxCNCKfPyRnF6606oKANWCg7gg";
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
        return "TinyTeleporter";
    }

    @Override
    public String getShortName() {
        return "TinyTeleporter";
    }

    @Override
    public String getAuthor() {
        return "さよ";
    }

    @Override
    public String getVersion() {
        return "2.191014";
    }

    @Override
    public String getDownloadFileName() {
        return "sy_TinyTeleporter125-2.191014.zip";
    }
}
