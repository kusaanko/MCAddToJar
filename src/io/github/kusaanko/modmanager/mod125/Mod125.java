package io.github.kusaanko.modmanager.mod125;

import io.github.kusaanko.modmanager.Mod;

import java.util.HashMap;

public abstract class Mod125 extends Mod {
    public static final HashMap<Class<? extends Mod>, Mod> mods125;

    static {
        mods125 = new HashMap<>();
        mods125.put(Forge125.class, new Forge125());
        mods125.put(OptiFine125.class, new OptiFine125());

        //Sayo
        mods125.put(MCAPI125.class, new MCAPI125());
        mods125.put(BCx125.class, new BCx125());
        mods125.put(BCxAddonCE125.class, new BCxAddonCE125());
        mods125.put(BCxAddonExtraTanks125.class, new BCxAddonExtraTanks125());
        mods125.put(CutAllFix125.class, new CutAllFix125());
        mods125.put(DigBedrock125.class, new DigBedrock125());
        mods125.put(FenceGateKeeper125.class, new FenceGateKeeper125());
        mods125.put(HarvestLevelSetter125.class, new HarvestLevelSetter125());
        mods125.put(LilypadBreed125.class, new LilypadBreed125());
        mods125.put(MineAllFix125.class, new MineAllFix125());
        mods125.put(OreBlockLight125.class, new OreBlockLight125());
        mods125.put(ReplaceBlock125.class, new ReplaceBlock125());
        mods125.put(StackSizeChange125.class, new StackSizeChange125());
        mods125.put(TorchAttack125.class, new TorchAttack125());
        mods125.put(AccessChest125.class, new AccessChest125());
        mods125.put(IC2125.class, new IC2125());
        mods125.put(IC2125.class, new IC2125());
        mods125.put(IC2125.class, new IC2125());
        mods125.put(IC2125.class, new IC2125());
        mods125.put(IC2125.class, new IC2125());
        mods125.put(IC2125.class, new IC2125());
        mods125.put(IC2Fix125.class, new IC2Fix125());

        //Others
        mods125.put(EE2125.class, new EE2125());
    }

    public static Mod is125(String fileName) {
        for (Mod mod : mods125.values()) {
            String ver = mod.is(fileName);
            if(ver != null) {
                Mod m = mod.clone();
                m.setFileName(fileName);
                m.setFileVersion(ver);
                return m;
            }
        }
        return null;
    }

    @Override
    public Class<? extends Mod>[] getRequireModsInJar() {
        return genRequireMods(Forge125.class);
    }
}
