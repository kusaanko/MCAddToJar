package io.github.kusaanko.modmanager.mod125;

public class EE2125 extends Mod125 {
    @Override
    public String is(String fileName) {
        if(fileName.startsWith("EE2ClientV")) {
            return fileName.substring("EE2ClientV".length());
        }
        return null;
    }

    @Override
    public String getDownloadURL() {
        return "https://web.archive.org/web/20130607083244if_/https://dl.dropboxusercontent.com/u/25591134/EE2/MC%201.2.5/1.4.6.7/EE2ClientV1.4.6.7.jar";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1282288-1-2-5-equivalent-exchange-2-v1-4-6-7";
    }

    @Override
    public String getName() {
        return "EquivalentExchange2";
    }

    @Override
    public String getShortName() {
        return "EE2";
    }

    @Override
    public String getAuthor() {
        return "Pahimar";
    }

    @Override
    public String getVersion() {
        return "1.4.6.7";
    }

    @Override
    public String getDownloadFileName() {
        return "EE2ClientV1.4.6.7.jar";
    }
}
