package net.ramgaming.leapers.api.data;

import net.ramgaming.leapers.api.modules.CoreEntry;
import net.ramgaming.leapers.api.modules.CrystalEntry;
import net.ramgaming.leapers.api.modules.FixtureEntry;
import net.ramgaming.leapers.api.modules.HandleEntry;

public interface LeaperRegistries {

    LeaperRegistry<CoreEntry> CORES = new LeaperRegistry<>();
    LeaperRegistry<HandleEntry> HANDLES = new LeaperRegistry<>();
    LeaperRegistry<FixtureEntry> FIXTURES = new LeaperRegistry<>();
    LeaperRegistry<CrystalEntry> CRYSTALS = new LeaperRegistry<>();

}



