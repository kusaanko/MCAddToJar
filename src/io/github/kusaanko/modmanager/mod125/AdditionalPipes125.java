package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class AdditionalPipes125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "buildcraft-client-DA-additionalpipes-");
    }

    @Override
    public String getDownloadURL() {
        return "http://download1586.mediafire.com/2ihnob717sgg/qb5otfg63pbr09z/buildcraft-client-DA-additionalpipes-3.1.0.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1279281-1-2-5-bc2-2-14-bc3-1-5-rev2-1-3-rev3-1-0";
    }

    @Override
    public TYPE getType() {
        return TYPE.ADDON;
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(BCx125.class);
    }

    @Override
    public String getName() {
        return "Additional Pipes";
    }

    @Override
    public String getShortName() {
        return "Additional Pipes";
    }

    @Override
    public String getAuthor() {
        return "Zombie Killer";
    }

    @Override
    public String getVersion() {
        return "3.1.0";
    }

    @Override
    public String getDownloadFileName() {
        return "buildcraft-client-DA-additionalpipes-3.1.0.zip";
    }
}
