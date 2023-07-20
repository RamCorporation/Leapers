package net.ramgaming.leapers.api.data;

import net.ramgaming.leapers.api.modules.CoreModule;
import net.ramgaming.leapers.api.modules.CrystalModule;
import net.ramgaming.leapers.api.modules.FixtureModule;
import net.ramgaming.leapers.api.modules.HandleModule;

public interface LeaperRegistries {

    LeaperRegistry<CoreModule> CORES = new LeaperRegistry<>();
    LeaperRegistry<HandleModule> HANDLES = new LeaperRegistry<>();
    LeaperRegistry<FixtureModule> FIXTURES = new LeaperRegistry<>();
    LeaperRegistry<CrystalModule> CRYSTALS = new LeaperRegistry<>();

}



