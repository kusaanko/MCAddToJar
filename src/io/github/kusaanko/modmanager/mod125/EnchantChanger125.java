package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

public class EnchantChanger125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "[1.2.5]EnchantChanger");
    }

    @Override
    public String getDownloadURL() {
        return "https://dl.dropboxusercontent.com/s/kvhk8ijmcmhnzhr/%5B1.2.5%5DEnchantChanger1.6r.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://minecraft125user.nisfan.net/forum/viewtopic.php?f=12&t=25";
    }

    @Override
    public String getName() {
        return "エンチャント交換MOD";
    }

    @Override
    public String getShortName() {
        return "EnchantChanger";
    }

    @Override
    public String getAuthor() {
        return "A.K.";
    }

    @Override
    public String getVersion() {
        return "1.6r";
    }

    @Override
    public String getDownloadFileName() {
        return "[1.2.5]EnchantChanger1.6r.zip";
    }
}
