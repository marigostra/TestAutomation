package BuckLuckDuck.services;

import BuckLuckDuck.model.ClickableElement;
import com.microsoft.playwright.ElementHandle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementManager {

    public void printVisible(List<ElementHandle> list) {
        int elementCounter = 0;
        System.out.println("Visible elements:");
        for (ElementHandle element : list) {
            System.out.println(elementCounter++ + ": " +
                    " Element is: " +
                    element.evaluate("el => el.tagName.toLowerCase()") +
                    ". Text content is: " +
                    element.textContent());
        }
    }

    public void printClickable(List<ClickableElement> clickableElements) {
        int elementCounter = 0;

        Map<String, List<ClickableElement>> elementsByType = groupElementsByType(clickableElements);

        System.out.println("\nClickable elements:");

        elementCounter = printElementsOfType("Buttons", elementsByType.get("button"), elementCounter);
        elementCounter = printElementsOfType("Links", elementsByType.get("link"), elementCounter);
        elementCounter = printElementsOfType("Inputs", elementsByType.get("input"), elementCounter);
        elementCounter = printElementsOfType("Others", elementsByType.get("other"), elementCounter);
    }

    public List<String> getElementTypes(List<ClickableElement> clickableElements) {
        List<String> elementTypes = new ArrayList<>();
        for (ClickableElement element : clickableElements)
            elementTypes.add(element.getType());

        return elementTypes;
    }

    private Map<String, List<ClickableElement>> groupElementsByType(List<ClickableElement> clickableElements) {
        Map<String, List<ClickableElement>> elementsByType = new HashMap<>();
        elementsByType.put("button", new ArrayList<>());
        elementsByType.put("link", new ArrayList<>());
        elementsByType.put("input", new ArrayList<>());
        elementsByType.put("other", new ArrayList<>());

        for (ClickableElement clickableElement : clickableElements)
            elementsByType.get(clickableElement.getType()).add(clickableElement);

        return elementsByType;
    }

    private int printElementsOfType(String title, List<ClickableElement> elements, int elementCounter) {
        System.out.println(title + ":");
        for (ClickableElement element : elements) {
            if ("input".equals(element.getType())) {
                System.out.print(elementCounter++ + ": ");
                System.out.println("Input with ID: " + element.getElement().getAttribute("id") + ". With placeholder: " + element.getElement().getAttribute("placeholder"));
            } else
                System.out.println(elementCounter++ + ": " + element.getElement().innerText());
        }
        return elementCounter;
    }
}