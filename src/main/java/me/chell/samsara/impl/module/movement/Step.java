package me.chell.samsara.impl.module.movement;

import me.chell.samsara.api.event.PlayerUpdateEvent;
import me.chell.samsara.api.module.Module;
import me.chell.samsara.api.value.Value;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Step extends Module {
    public Step() {
        super("Step", Category.MOVEMENT);
    }

    private final Value<Mode> mode = builder("Mode", Mode.VANILLA).build();
    private final Value<Float> height = builder("Height", 2.1f).bounds(.1f, 3f).build();

    @SubscribeEvent
    public void onPlayerUpdate(PlayerUpdateEvent event) {
        getPlayer().stepHeight = height.getValue();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if(getPlayer() != null)
            getPlayer().stepHeight = .6f;
    }

    private enum Mode {
        VANILLA
    }
}