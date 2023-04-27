package BuckLuckDuck.commands;

import BuckLuckDuck.services.ElementAction;
import BuckLuckDuck.options.Options;

public class ReloadCommand extends Command {
    private final ElementAction elementAction;

    public ReloadCommand(Options options, ElementAction elementAction) {
        super(options);
        this.elementAction = elementAction;
    }

    @Override
    public void execute() {
        elementAction.reload();
    }
}
