package me.chell.samsara.api.module;

import me.chell.samsara.api.Loadable;
import me.chell.samsara.impl.module.combat.*;
import me.chell.samsara.impl.module.misc.*;
import me.chell.samsara.impl.module.movement.*;
import me.chell.samsara.impl.module.render.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager implements Loadable {
    private List<Module> modules;

    @Override
    public void load() {
        MinecraftForge.EVENT_BUS.register(this);

        modules = new ArrayList<>();
        modules.add(new TestModule());
        modules.add(new CombatModule());
        modules.add(new MovementModule());
        modules.add(new RenderModule());

        for(Module m : modules) {
            m.load();
        }
    }

    @Override
    public void unload() {
        MinecraftForge.EVENT_BUS.unregister(this);

        for(Module m : modules) {
            m.unload();
        }

        modules = new ArrayList<>();
    }

    public List<Module> getModules() {
        return modules;
    }

    public Module getModule(String name) {
        for(Module m : modules) {
            if(m.getName().equalsIgnoreCase(name)) return m;
        }
        return null;
    }

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event) {
        if(!Keyboard.getEventKeyState() || Keyboard.isRepeatEvent()) return;
        for(Module m : modules) {
            if(m.getBind() == Keyboard.getEventKey()) m.toggle();
        }
    }
}
