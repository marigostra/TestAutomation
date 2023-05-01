package BuckLuckDuck.commands;

import BuckLuckDuck.options.Options;
import BuckLuckDuck.services.ElementManager;

public class VisibleNextPageCommand extends Command{
    private final ElementManager elementManager;

    public VisibleNextPageCommand(Options options, ElementManager elementManager) {
        super(options);
        this.elementManager = elementManager;
    }

    @Override
    public void execute() {
        elementManager.nextPage();
    }
}
