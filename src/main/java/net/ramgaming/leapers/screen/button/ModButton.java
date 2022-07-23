package net.ramgaming.leapers.screen.button;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ModButton extends ButtonWidget {
    public ModButton(int x, int y, int width, int height, Text message, PressAction onPress) {
        super(x, y, width, height, message, onPress);
    }
}
