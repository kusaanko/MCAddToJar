package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class BCICCrossover125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "C_BCIC2crossover.IB.v");
    }

    @Override
    public String getDownloadURL() {
        return "http://download940.mediafire.com/ocbixyhdcmhg/g1t11hpthkwgedw/BCIC2crossover.IB.v0.08.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1279370-1-4-6-bc-ic2-crossover-mod-v2-0-for-bc-3-3-0-and";
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(BCx125.class, IC2125.class);
    }

    @Override
    public String getName() {
        return "BuildCraft-IndustrialCraft2 Crossover Mod";
    }

    @Override
    public String getShortName() {
        return "BC-IC Crossover";
    }

    @Override
    public String getAuthor() {
        return "silentdeth";
    }

    @Override
    public String getVersion() {
        return "0.08";
    }

    //C_ to avoid conflict with BCx
    @Override
    public String getDownloadFileName() {
        return "C_BCIC2crossover.IB.v0.08.zip";
    }
}
