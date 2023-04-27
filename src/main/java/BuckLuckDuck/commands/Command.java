package BuckLuckDuck.commands;

import BuckLuckDuck.options.Options;

public abstract class Command {
    protected Options options;

    public Command(Options options) {
        this.options = options;
    }

    public abstract void execute();
}


