package BuckLuckDuck.commands;

import BuckLuckDuck.options.Options;
import BuckLuckDuck.services.ElementManager;

public class ShowClickableAllCommand extends Command {
    private final ElementManager elementManager;

    public ShowClickableAllCommand(Options options, ElementManager elementManager) {
        super(options);
        this.elementManager = elementManager;
    }

    @Override
    public void execute() {
        elementManager.setClickableFilter(0);
    }
}
