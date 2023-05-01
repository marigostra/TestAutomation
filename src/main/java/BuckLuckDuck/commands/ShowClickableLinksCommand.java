package BuckLuckDuck.commands;

import BuckLuckDuck.options.Options;
import BuckLuckDuck.services.ElementManager;

public class ShowClickableLinksCommand extends Command {
    private final ElementManager elementManager;

    public ShowClickableLinksCommand(Options options, ElementManager elementManager) {
        super(options);
        this.elementManager = elementManager;
    }

    @Override
    public void execute() {
        elementManager.setClickableFilter(3);
    }
}
