package BuckLuckDuck.commands;

import BuckLuckDuck.services.ElementAction;
import BuckLuckDuck.options.Options;

public class NextCommand extends Command {
    private final ElementAction elementAction;

    public NextCommand(Options options, ElementAction elementAction) {
        super(options);
        this.elementAction = elementAction;
    }

    @Override
    public void execute() {
        elementAction.goForward();
    }
}

