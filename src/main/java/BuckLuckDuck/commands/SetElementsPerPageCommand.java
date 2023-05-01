package BuckLuckDuck.commands;

import BuckLuckDuck.options.Options;
import BuckLuckDuck.services.ElementManager;

public class SetElementsPerPageCommand extends Command{
    private final ElementManager elementManager;
    private final int elementsPerPage;

    public SetElementsPerPageCommand(Options options, ElementManager elementManager, int elementsPerPage) {
        super(options);
        this.elementManager = elementManager;
        this.elementsPerPage = elementsPerPage;
    }

    @Override
    public void execute() {
        elementManager.setElementsPerPage(elementsPerPage);
    }
}
