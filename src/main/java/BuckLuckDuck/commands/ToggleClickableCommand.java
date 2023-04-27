package BuckLuckDuck.commands;

import BuckLuckDuck.options.Options;

public class ToggleClickableCommand extends Command {
    public ToggleClickableCommand(Options options) {
        super(options);
    }

    @Override
    public void execute() {
        options.setShowClickable(!options.isShowClickable());
    }
}
