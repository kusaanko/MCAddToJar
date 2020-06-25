package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class PlayerAPI125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "MC 1.2.5 - Player API client ");
    }

    @Override
    public String getDownloadURL() {
        return "http://download963.mediafire.com/b6oqcc3layig/3t4ohn1scod44rr/MC+1.2.5+-+Player+API+client+1.5.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20161125172040/http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/1277996-player-api";
    }

    @Override
    public INSTALLATION_TYPE getInstallationType() {
        return INSTALLATION_TYPE.IN_JAR;
    }

    @Override
    public TYPE getType() {
        return TYPE.REQUIREMENTS;
    }

    @Override
    public String getName() {
        return "Player API";
    }

    @Override
    public String getShortName() {
        return "Player API";
    }

    @Override
    public String getAuthor() {
        return "Divisor";
    }

    @Override
    public String getVersion() {
        return "1.5";
    }

    @Override
    public String getDownloadFileName() {
        return "MC 1.2.5 - Player API client 1.5.zip";
    }
}
