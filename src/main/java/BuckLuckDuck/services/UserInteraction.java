package BuckLuckDuck.services;

import BuckLuckDuck.commands.*;
import BuckLuckDuck.options.Options;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserInteraction {

    private final Scanner scanner;
    private final Options options;
    private final Map<String, Command> commands;
    private final ElementUpdater elementUpdater;
    private final ElementManager elementManager;

    private UserInteraction(Builder builder) {
        this.scanner = new Scanner(System.in);
        this.options = builder.options;
        this.elementUpdater = builder.elementUpdater;
        this.elementManager = builder.elementManager;
        this.commands = new HashMap<>();
        initializeCommands(builder.elementAction);
    }

    private void initializeCommands(ElementAction elementAction) {
        commands.put("-v", new ToggleVisibleCommand(options));
        commands.put("-c", new ToggleClickableCommand(options));
        commands.put("-a", new ToggleAskForClickCommand(options));
        commands.put("-back", new BackCommand(options, elementAction));
        commands.put("-next", new NextCommand(options, elementAction));
        commands.put("-reload", new ReloadCommand(options, elementAction));
        commands.put("-vn", new VisibleNextPageCommand(options, elementManager));
        commands.put("-vp", new VisiblePrevPageCommand(options, elementManager));
        commands.put("-ci", new ShowClickableInputsCommand(options, elementManager));
        commands.put("-cb", new ShowClickableButtonsCommand(options, elementManager));
        commands.put("-cl", new ShowClickableLinksCommand(options, elementManager));
        commands.put("-co", new ShowClickableOthersCommand(options, elementManager));
        commands.put("-ca", new ShowClickableAllCommand(options, elementManager));
    }

    public String[] askForClick(List<String> elementTypes) {
        // Ввод номера элемента для клика
        System.out.println("\nEnter number of element for click imitation:");
        String input = scanner.next();

        // Проверка на команду
        while (input.charAt(0) == '-') {
            boolean isCorrectCommand = parseAndExecuteCommand(input);
            System.out.println(isCorrectCommand);
            if(isCorrectCommand)
                elementUpdater.updateAndPrintElements();

            input = scanner.next();
        }

        String elementType = elementTypes.get(Integer.parseInt(input));
        String inputText = "";
        if ("input".equals(elementType)) {
            System.out.println("Enter the text you want to input:");
            scanner.next();
            inputText = scanner.nextLine();
        }

        return new String[]{input, inputText};
    }

    public Boolean parseAndExecuteCommand(String input) {
        // Регистрация команды с параметром -vpage=x
        if (input.startsWith("-vpage=")) {
            int pageNumber = Integer.parseInt(input.substring("-vpage=".length()));
            new VisibleSetPageCommand(options, elementManager, pageNumber).execute();
        }
        // Регистрация команды с параметром -ve=x
        else if (input.startsWith("-ve=")) {
            int elementsPerPage = Integer.parseInt(input.substring("-ve=".length()));
            new SetElementsPerPageCommand(options, elementManager, elementsPerPage).execute();
        }
        // Выполнение команд без параметров
        else if (commands.containsKey(input))
            commands.get(input).execute();
        // Если ввод не соответствует ни одной команде, выводим сообщение об ошибке
        else {
            System.out.println("\nWrong command. Try again.");
            return false;
        }
        System.out.println("\nEnter number of element for click imitation:");
        return true;
    }

    public static class Builder {
        private Options options;
        private ElementAction elementAction;
        private ElementUpdater elementUpdater;
        private ElementManager elementManager;

        public Builder() {
        }

        public Builder options(Options options) {
            this.options = options;
            return this;
        }

        public Builder elementAction(ElementAction elementAction) {
            this.elementAction = elementAction;
            return this;
        }

        public Builder elementUpdater(ElementUpdater elementUpdater) {
            this.elementUpdater = elementUpdater;
            return this;
        }

        public Builder elementManager(ElementManager elementManager) {
            this.elementManager = elementManager;
            return this;
        }

        public UserInteraction build() {
            if (options == null || elementAction == null || elementUpdater == null || elementManager == null) {
                throw new IllegalStateException("All required fields must be set");
            }
            return new UserInteraction(this);
        }
    }
}

