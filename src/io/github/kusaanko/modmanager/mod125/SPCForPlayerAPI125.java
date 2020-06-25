package io.github.kusaanko.modmanager.mod125;

public class SPCForPlayerAPI125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "SPC for PlayerAPI Patch 6.0 for SPC ");
    }

    @Override
    public String getDownloadURL() {
        return "http://download1646.mediafire.com/xrsgq55vj3qg/a1kpvbdob0d0ll3/SPC+for+PlayerAPI+Patch+6.0+for+SPC+3.2.2.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://web.archive.org/web/20170816112715/http://www.minecraftforum.net:80/forums/mapping-and-modding/minecraft-mods/1276865-1-5-2-single-player-commands-for-player-api";
    }

    @Override
    public INSTALLATION_TYPE getInstallationType() {
        return INSTALLATION_TYPE.IN_JAR;
    }

    @Override
    public TYPE getType() {
        return TYPE.ADDON;
    }

    @Override
    public String getName() {
        return "SPC for Player API";
    }

    @Override
    public String getShortName() {
        return "SPC for Player API";
    }

    @Override
    public String getAuthor() {
        return "Divisor";
    }

    @Override
    public String getVersion() {
        return "3.2.2";
    }

    @Override
    public String getDownloadFileName() {
        return "SPC for PlayerAPI Patch 6.0 for SPC 3.2.2.zip";
    }
}
