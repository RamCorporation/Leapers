package net.ramgames.leapers.api.data;

import net.ramgames.leapers.api.modules.Core;
import net.ramgames.leapers.api.modules.Crystal;
import net.ramgames.leapers.api.modules.Fixture;
import net.ramgames.leapers.api.modules.Handle;

public interface LeaperRegistries {

    LeaperRegistry<Core> CORES = new LeaperRegistry<>();
    LeaperRegistry<Handle> HANDLES = new LeaperRegistry<>();
    LeaperRegistry<Fixture> FIXTURES = new LeaperRegistry<>();
    LeaperRegistry<Crystal> CRYSTALS = new LeaperRegistry<>();

}



