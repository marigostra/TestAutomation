package BuckLuckDuck.commands;

import BuckLuckDuck.options.Options;

public class ToggleAskForClickCommand extends Command {
    public ToggleAskForClickCommand(Options options) {
        super(options);
    }

    @Override
    public void execute() {
        options.setAskForClick(!options.isAskForClick());
    }
}
