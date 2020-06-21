package io.github.kusaanko.modmanager.mod125;

public class Zeppelin125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mod_Zeppelin-client-");
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20150112190155if_/https://dl.dropboxusercontent.com/u/64318938/Zeppelin/mod_Zeppelin-client-1.2.5.0.31.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/wip-mods/1438934-1-2-5-zeppelin-0-31-wip";
    }

    @Override
    public String getName() {
        return "Zeppelin";
    }

    @Override
    public String getShortName() {
        return "Zeppelin";
    }

    @Override
    public String getAuthor() {
        return "blakmajik";
    }

    @Override
    public String getVersion() {
        return "1.2.5.0.31";
    }

    @Override
    public String getDownloadFileName() {
        return "mod_Zeppelin-client-1.2.5.0.31.zip";
    }
}
