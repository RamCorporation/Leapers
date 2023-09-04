package net.ramgames.leapers.blocks.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.world.World;

public class SandyClusterFallingBlockEntity extends FallingBlockEntity {
    public SandyClusterFallingBlockEntity(EntityType<? extends FallingBlockEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        if(this.age > 4) this.noClip = false;
        super.tick();
    }
}
