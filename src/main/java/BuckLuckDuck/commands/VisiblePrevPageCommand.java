package BuckLuckDuck.commands;

import BuckLuckDuck.options.Options;
import BuckLuckDuck.services.ElementManager;

public class VisiblePrevPageCommand extends Command{
    private final ElementManager elementManager;

    public VisiblePrevPageCommand(Options options, ElementManager elementManager) {
        super(options);
        this.elementManager = elementManager;
    }

    @Override
    public void execute() {
        elementManager.prevPage();
    }
}
