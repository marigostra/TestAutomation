package BuckLuckDuck.services;

import BuckLuckDuck.model.ClickableElement;
import com.microsoft.playwright.ElementHandle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementManager {
    private int elementsPerPage = 10;
    private int visiblePage = 0;
    private int clickableFilter = 0; // 0 - all, 1 - input, 2 - button, 3 - link, 4 - other

    public void visibleSetPage(int pageNumber) {
        visiblePage = pageNumber;
//        printVisible(PageState.getVisibleElements());
    }

    public void nextPage() {
        visiblePage++;
//        printVisible(PageState.getVisibleElements());
    }

    public void prevPage() {
        if (visiblePage > 0)
            visiblePage--;
//        printVisible(PageState.getVisibleElements());
    }

    public void setElementsPerPage(int elementsPerPage) {
        this.elementsPerPage = elementsPerPage;
//        printVisible(PageState.getVisibleElements());
//        printClickable(PageState.getClickableElements());
    }

    public void setClickableFilter(int filter) {
        this.clickableFilter = filter;
        // printClickable(PageState.getClickableElements());
    }

    public void printVisible(List<ElementHandle> list) {

        int start = visiblePage * elementsPerPage;
        int end = Math.min(start + elementsPerPage, list.size());

        int totalPages = (int) Math.ceil((double) list.size() / elementsPerPage);

        System.out.println("Visible elements (page " + (visiblePage + 1) + "/" + totalPages + "):");
        for (int i = start; i < end; i++) {
            ElementHandle element = list.get(i);
            System.out.println(list.indexOf(element) + 1 + ": " +
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

        if (clickableFilter == 0 || clickableFilter == 1)
            elementCounter = printElementsOfType("Inputs", elementsByType.get("input"), elementCounter);
        if (clickableFilter == 0 || clickableFilter == 2)
            elementCounter = printElementsOfType("Buttons", elementsByType.get("button"), elementCounter);
        if (clickableFilter == 0 || clickableFilter == 3)
            elementCounter = printElementsOfType("Links", elementsByType.get("link"), elementCounter);
        if (clickableFilter == 0 || clickableFilter == 4)
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