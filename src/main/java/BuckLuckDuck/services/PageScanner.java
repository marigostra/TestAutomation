package BuckLuckDuck.services;

import BuckLuckDuck.model.ClickableElement;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

import java.util.List;

public class PageScanner {

    public List<ElementHandle> scanPageForVisible(Page page) {
        return page.querySelectorAll(":visible");
    }

    public static void scanPageForClickable(Page page, List<ClickableElement> clickableElements) {
        List<ElementHandle> buttons = page.querySelectorAll(":visible:is(button, [role=button])");
        for (ElementHandle button : buttons) {
            clickableElements.add(new ClickableElement("button", button));
        }

        List<ElementHandle> links = page.querySelectorAll("a:visible");
        for (ElementHandle link : links) {
            clickableElements.add(new ClickableElement("link", link));
        }

        List<ElementHandle> inputs = page.querySelectorAll("input:visible");
        for (ElementHandle input : inputs) {
            clickableElements.add(new ClickableElement("input", input));
        }

        List<ElementHandle> others = page.querySelectorAll("[tabindex]:visible");
        for (ElementHandle other : others) {
            clickableElements.add(new ClickableElement("other", other));
        }
    }

}
