package BuckLuckDuck.commands;

import BuckLuckDuck.options.Options;
import BuckLuckDuck.services.ElementManager;

public class VisibleSetPageCommand extends Command {
    private final ElementManager elementManager;
    private final int pageNumber;

    public VisibleSetPageCommand(Options options, ElementManager elementManager, int pageNumber) {
        super(options);
        this.elementManager = elementManager;
        this.pageNumber = pageNumber;
    }

    @Override
    public void execute() {
        elementManager.visibleSetPage(pageNumber);
    }
}

