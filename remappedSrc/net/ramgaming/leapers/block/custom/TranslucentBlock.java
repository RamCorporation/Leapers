package net.ramgaming.leapers.block.custom;

import net.minecraft.block.Block;

public class TranslucentBlock extends Block {
    public TranslucentBlock(Settings settings) {
        super(settings.nonOpaque());
    }
}
