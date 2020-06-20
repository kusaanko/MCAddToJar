package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class SinglePlayerCommands125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "SinglePlayerCommands-MC1.2.5_V");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20141222052604if_/https://dl.dropboxusercontent.com/u/17317177/SinglePlayerCommands-MC1.2.5_V3.2.2.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1272348-single-player-commands-v4-9-official-download-spc#downloads";
    }

    @Override
    public INSTALLATION_TYPE getInstallationType() {
        return INSTALLATION_TYPE.IN_JAR;
    }

    @Override
    public String getName() {
        return "SinglePlayerCommands";
    }

    @Override
    public String getShortName() {
        return "SPC";
    }

    @Override
    public String getAuthor() {
        return "simo_415";
    }

    @Override
    public String getVersion() {
        return "3.2.2";
    }

    @Override
    public String getDownloadFileName() {
        return "SinglePlayerCommands-MC1.2.5_V3.2.2.zip";
    }
}
