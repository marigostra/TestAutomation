package BuckLuckDuck.commands;

import BuckLuckDuck.options.Options;

public class ToggleVisibleCommand extends Command {
    public ToggleVisibleCommand(Options options) {
        super(options);
    }

    @Override
    public void execute() {
        options.setShowVisible(!options.isShowVisible());
    }
}

