package io.github.kusaanko.modmanager.mod125;

public class TurboModelThingy125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "turbomodelthingy-v");
    }

    @Override
    public String getDownloadURL() {
        return "http://www.multiverseworks.com/minecraft/turbomodelthingy-v2.3.7-1.2.5.zip";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1272395-1-2-5-you-know-what-time-it-is-its-garycxjk-time";
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
        return "Turbo Model Thingy";
    }

    @Override
    public String getShortName() {
        return "Turbo Model Thingy";
    }

    @Override
    public String getAuthor() {
        return "GaryCXJk";
    }

    @Override
    public String getVersion() {
        return "2.3.7-1.2.5";
    }

    @Override
    public String getDownloadFileName() {
        return "turbomodelthingy-v2.3.7-1.2.5.zip";
    }
}
