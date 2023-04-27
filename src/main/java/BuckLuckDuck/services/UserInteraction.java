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
    private ElementAction elementAction;
    private final ElementUpdater elementUpdater;

    public UserInteraction(Options options, ElementAction elementAction, ElementUpdater elementUpdater) {
        this.scanner = new Scanner(System.in);
        this.options = options;
        this.commands = new HashMap<>();
        this.elementUpdater = elementUpdater;
        initializeCommands(elementAction);
    }

    private void initializeCommands(ElementAction elementAction) {
        commands.put("-v", new ToggleVisibleCommand(options));
        commands.put("-c", new ToggleClickableCommand(options));
        commands.put("-a", new ToggleAskForClickCommand(options));
        commands.put("-back", new BackCommand(options, elementAction));
        commands.put("-next", new NextCommand(options, elementAction));
        commands.put("-reload", new ReloadCommand(options, elementAction));
    }

    public String[] askForClick(List<String> elementTypes) {
        // Ввод номера элемента для клика
        System.out.println("\nEnter number of element for click imitation:");
        String index = scanner.next();

        // Проверка на команду
        while (index.charAt(0) == '-') {
            if (commands.containsKey(index)) {
                commands.get(index).execute();
                elementUpdater.updateAndPrintElements(); // вызываем метод после выполнения команды
                System.out.println("\nEnter number of element for click imitation:");
            } else {
                System.out.println("\nWrong command. Try again.");
            }
            index = scanner.next();
        }

        String elementType = elementTypes.get(Integer.parseInt(index));
        String inputText = "";
        if ("input".equals(elementType)) {
            System.out.println("Enter the text you want to input:");
            scanner.next();
            inputText = scanner.nextLine();
        }

        return new String[]{index, inputText};
    }
}

