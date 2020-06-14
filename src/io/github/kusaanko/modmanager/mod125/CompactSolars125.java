package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class CompactSolars125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "mod_compactsolars-client-");
    }

    @Override
    public String getDownloadURL() {
        return "http://download1580.mediafire.com/3mlu3tbzgpvg/bzsf5i3hd68eyty/mod_compactsolars-client-2.2.0.5.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://forum.industrial-craft.net/thread/4827-ic2-exp-1-7-10-compactsolars-4-4/";
    }

    @Override
    public TYPE getType() {
        return TYPE.ADDON;
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(IC2125.class);
    }

    @Override
    public String getName() {
        return "CompactSolars";
    }

    @Override
    public String getShortName() {
        return "CompactSolars";
    }

    @Override
    public String getAuthor() {
        return "cpw";
    }

    @Override
    public String getVersion() {
        return "2.2.0.5";
    }

    @Override
    public String getDownloadFileName() {
        return "mod_compactsolars-client-2.2.0.5.zip";
    }
}
