package BuckLuckDuck.commands;

import BuckLuckDuck.options.Options;
import BuckLuckDuck.services.ElementManager;

public class ShowClickableOthersCommand extends Command {
    private final ElementManager elementManager;

    public ShowClickableOthersCommand(Options options, ElementManager elementManager) {
        super(options);
        this.elementManager = elementManager;
    }

    @Override
    public void execute() {
        elementManager.setClickableFilter(4);
    }
}
