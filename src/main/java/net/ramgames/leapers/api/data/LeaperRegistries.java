package net.ramgames.leapers.api.data;

import net.ramgames.leapers.api.modules.*;

public interface LeaperRegistries {

    LeaperRegistry<Core> CORES = new LeaperRegistry<>();
    LeaperRegistry<Handle> HANDLES = new LeaperRegistry<>();
    LeaperRegistry<Fixture> FIXTURES = new LeaperRegistry<>();
    LeaperRegistry<Crystal> CRYSTALS = new LeaperRegistry<>();

    LeaperRegistry<CrystalInspectorTooltip> CRYSTAL_INSPECTOR_TOOLTIP = new LeaperRegistry<>();

}



