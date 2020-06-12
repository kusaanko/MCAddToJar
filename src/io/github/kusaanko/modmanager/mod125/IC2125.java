package io.github.kusaanko.modmanager.mod125;

public class IC2125 extends Mod125 {
    @Override
    public String is(String fileName) {
        return startsWith(fileName, "industrialcraft-2-client_");
    }

    @Override
    public String getDownloadURL() {
        return "https://wiki.industrial-craft.net/Downloadfiles/IndustrialCraft%C2%B2/industrialcraft-2-client_1.95b.jar";
    }

    @Override
    public String getDownloadPageURL() {
        return "https://forum.industrial-craft.net/thread/5896-mc-1-2-5-ic%C2%B2-v1-95b-ssp-smp/";
    }

    @Override
    public String getName() {
        return "IndustrialCraft2";
    }

    @Override
    public String getShortName() {
        return "IC2";
    }

    @Override
    public String getAuthor() {
        return "Alblaka";
    }

    @Override
    public String getVersion() {
        return "1.95b";
    }

    @Override
    public String getDownloadFileName() {
        return "industrialcraft-2-client_1.95b.jar";
    }
}
