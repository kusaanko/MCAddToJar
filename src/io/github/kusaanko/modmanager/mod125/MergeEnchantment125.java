package io.github.kusaanko.modmanager.mod125;

public class MergeEnchantment125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "[1.2.5]MergeEnchantment");
    }

    @Override
    public String getDownloadURL() {
        return "https://dl.dropboxusercontent.com/s/4g9og5lfx08eqdn/%5B1.2.5%5DMergeEnchantment1.1.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "http://minecraft125user.nisfan.net/forum/viewtopic.php?f=12&t=28";
    }

    @Override
    public String getName() {
        return "MergeEnchantment";
    }

    @Override
    public String getShortName() {
        return "MergeEnchantment";
    }

    @Override
    public String getAuthor() {
        return "A.K.";
    }

    @Override
    public String getVersion() {
        return "1.1";
    }

    @Override
    public String getDownloadFileName() {
        return "[1.2.5]MergeEnchantment1.1.zip";
    }
}
