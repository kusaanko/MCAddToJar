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
        mods125.put(IC2Fix125.class, new IC2Fix125());
        mods125.put(LilypadBreed125.class, new LilypadBreed125());
        mods125.put(MineAllFix125.class, new MineAllFix125());
        mods125.put(OreBlockLight125.class, new OreBlockLight125());
        mods125.put(ReplaceBlock125.class, new ReplaceBlock125());
        mods125.put(StackSizeChange125.class, new StackSizeChange125());
        mods125.put(TorchAttack125.class, new TorchAttack125());
        mods125.put(AccessChest125.class, new AccessChest125());
        mods125.put(AlternativeRecipes125.class, new AlternativeRecipes125());
        mods125.put(AzukiBar125.class, new AzukiBar125());
        mods125.put(BedrockTools125.class, new BedrockTools125());
        mods125.put(BigTree125.class, new BigTree125());
        mods125.put(Cardboard125.class, new Cardboard125());
        mods125.put(CraftGuide125.class, new CraftGuide125());
        mods125.put(DirectionAdjustment125.class, new DirectionAdjustment125());
        mods125.put(EraserBomb125.class, new EraserBomb125());
        mods125.put(ForestryTMI125.class, new ForestryTMI125());//
        mods125.put(IDwakander125.class, new IDwakander125());
        mods125.put(InvTweaks125.class, new InvTweaks125());
        mods125.put(Lamp125.class, new Lamp125());
        mods125.put(littleMaidMob125.class, new littleMaidMob125());
        mods125.put(Mob2Egg125.class, new Mob2Egg125());
        mods125.put(MoreFire125.class, new MoreFire125());
        mods125.put(RailPlus125.class, new RailPlus125());
        mods125.put(SmallStairs125.class, new SmallStairs125());
        mods125.put(SolomonsRod125.class, new SolomonsRod125());
        mods125.put(SpawnChange125.class, new SpawnChange125());
        mods125.put(StackableTools125.class, new StackableTools125());
        mods125.put(StorageBox125.class, new StorageBox125());
        mods125.put(FururinMod125.class, new FururinMod125());
        mods125.put(OptiFineHDRendererFix125.class, new OptiFineHDRendererFix125());
        mods125.put(OptiFineLocalizeFix125.class, new OptiFineLocalizeFix125());
        mods125.put(DiamondHell125.class, new DiamondHell125());
        mods125.put(ThinArm125.class, new ThinArm125());
        mods125.put(VillagersSpawnBan125.class, new VillagersSpawnBan125());

        //A.K.
        mods125.put(ChainDestruction125.class, new ChainDestruction125());
        mods125.put(EEAA125.class, new EEAA125());
        mods125.put(EnchantChanger125.class, new EnchantChanger125());
        mods125.put(MergeEnchantment125.class, new MergeEnchantment125());
        mods125.put(MultiToolHolders125.class, new MultiToolHolders125());

        //RedPower
        mods125.put(RedPowerCore125.class, new RedPowerCore125());
        mods125.put(RedPowerLogic125.class, new RedPowerLogic125());
        mods125.put(RedPowerWiring125.class, new RedPowerWiring125());
        mods125.put(RedPowerLighting125.class, new RedPowerLighting125());
        mods125.put(RedPowerWorld125.class, new RedPowerWorld125());
        mods125.put(RedPowerMachine125.class, new RedPowerMachine125());
        mods125.put(RedPowerControl125.class, new RedPowerControl125());

        //Others
        mods125.put(AdditionalPipes125.class, new AdditionalPipes125());
        mods125.put(AdditionalPipesFix125.class, new AdditionalPipesFix125());
        mods125.put(SneakyPipes125.class, new SneakyPipes125());
        mods125.put(BCICCrossover125.class, new BCICCrossover125());
        mods125.put(kuukaiCrossConverter125.class, new kuukaiCrossConverter125());
        mods125.put(IC2125.class, new IC2125());
        mods125.put(CompactSolars125.class, new CompactSolars125());
        mods125.put(EE2125.class, new EE2125());
        mods125.put(IronChest125.class, new IronChest125());
        mods125.put(MultiPageChest125.class, new MultiPageChest125());
        mods125.put(SinglePlayerCommands125.class, new SinglePlayerCommands125());
        mods125.put(TooManyItems125.class, new TooManyItems125());
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

    @Override
    public String getDownloadFolder()  {
        return "mods/1.2.5/";
    }
}
