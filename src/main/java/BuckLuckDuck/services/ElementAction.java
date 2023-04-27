package BuckLuckDuck.services;

import BuckLuckDuck.model.ClickableElement;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

import java.util.List;

public class ElementAction {
    private final Page page;

    public ElementAction(Page page) {
        this.page = page;
    }

    public void clickOnElement(List<ClickableElement> clickableElements, int index, String inputText) {
        if (index >= 0 && index < clickableElements.size()) {
            ClickableElement clickableElement = clickableElements.get(index);
            ElementHandle elementToClick = clickableElement.getElement();
            String elementType = clickableElement.getType();

            // Если элемент является input, введите текст, предоставленный пользователем
            if ("input".equals(elementType) && inputText != null && !inputText.isEmpty()) {
                fill(elementToClick, inputText);
            } else {
                click(elementToClick);
            }
        } else {
            PlaywrightMain.LOGGER.warning("Element with that index - " + index + " not found.");
        }
    }

    public static void click(ElementHandle element) {
        element.click();
        PlaywrightMain.LOGGER.info("Click done");
    }

    public static void fill(ElementHandle element, String inputText) {
        element.fill(inputText);
        PlaywrightMain.LOGGER.info("Input filled with text: " + inputText);
    }

    public void goBack() {
        page.goBack();
    }

    public void goForward() {
        page.goForward();
    }

    public void reload() {
        page.reload();
    }
}