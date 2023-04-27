package BuckLuckDuck.commands;

import BuckLuckDuck.services.ElementAction;
import BuckLuckDuck.options.Options;

public class BackCommand extends Command {
    private final ElementAction elementAction;

    public BackCommand(Options options, ElementAction elementAction) {
        super(options);
        this.elementAction = elementAction;
    }

    @Override
    public void execute() {
        elementAction.goBack();
    }
}
