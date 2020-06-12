package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class IC2Fix125 extends Mod125 {
    @Override
    public String is(String fileName) {
        if(fileName.startsWith("ic2_1.95b_Fix-")) {
            return fileName.substring("ic2_1.95b_Fix-".length());
        }
        return null;
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
    public String getDownloadURL() {
        return "https://drive.google.com/uc?id=1MF0BaMdi15n8M1cz8jdlEUrb6ks0y_-3";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://drive.google.com/drive/u/0/folders/0BzZbvRtrx7SscjF4b3pYamlkY1k";
    }

    @Override
    public String getName() {
        return "IC2 Fix";
    }

    @Override
    public String getShortName() {
        return "IC2Fix";
    }

    @Override
    public String getAuthor() {
        return "さよ";
    }

    @Override
    public String getVersion() {
        return "15.180905";
    }

    @Override
    public String getDownloadFileName() {
        return "ic2_1.95b_Fix-15.180905.zip";
    }
}
