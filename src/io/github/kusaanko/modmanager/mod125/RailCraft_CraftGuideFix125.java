package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class RailCraft_CraftGuideFix125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return null;
    }

    @Override
    public String getDownloadURL() {
        return "";
    }

    @Override
    public String getDownloadPageURL() {
        return "";
    }

    @Override
    public Class<? extends Mod>[] getRequireMods() {
        return genRequireMods(RailCraft125.class);
    }

    @Override
    public TYPE getType() {
        return TYPE.PATCH;
    }

    @Override
    public PATCH_TYPE getPatchType() {
        return PATCH_TYPE.DELETE_FILES;
    }

    @Override
    public Class<? extends Mod> getPatchMod() {
        return RailCraft125.class;
    }

    @Override
    public String[] getPatchDeleteFiles() {
        return new String[]{"CraftGuide"};
    }

    @Override
    public String getName() {
        return "RailCraft CraftGuide Fix";
    }

    @Override
    public String getShortName() {
        return "RailCraft CraftGuide Fix";
    }

    @Override
    public String getAuthor() {
        return "Kusaanko";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getDownloadFileName() {
        return "";
    }
}
