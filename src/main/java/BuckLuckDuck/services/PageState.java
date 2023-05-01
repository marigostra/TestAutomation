package BuckLuckDuck.services;

import BuckLuckDuck.model.ClickableElement;
import BuckLuckDuck.options.Options;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class PageState {
    private static List<ElementHandle> visibleElements;
    private static List<ClickableElement> clickableElements;
    private List<String> elementTypes;

    private final PageScanner pageScanner;
    private final ElementManager elementManager;
    private final ElementAction elementAction;
    private final UserInteraction userInteraction;

    private PageState(Builder builder) {
        this.pageScanner = builder.pageScanner;
        this.elementManager = builder.elementManager;
        this.elementAction = builder.elementAction;
        this.userInteraction = builder.userInteraction;
        visibleElements = new ArrayList<>();
        clickableElements = new ArrayList<>();
        this.elementTypes = new ArrayList<>();
    }

    public void updateElements(Page page) {
        visibleElements = pageScanner.scanPageForVisible(page);
        clickableElements = new ArrayList<>();
        PageScanner.scanPageForClickable(page, clickableElements);
        elementTypes = elementManager.getElementTypes(clickableElements);
    }

    public void printElements(Options options) {
        if (options.isShowVisible()) {
            elementManager.printVisible(visibleElements);
        }
        if (options.isShowClickable()) {
            elementManager.printClickable(clickableElements);
        }
    }

    public void interactWithElements(Options options) {
        if (options.isAskForClick()) {
            String[] clickInfo = userInteraction.askForClick(elementTypes);
            int index = Integer.parseInt(clickInfo[0]);
            String inputText = clickInfo[1];

            if (index == -1) {
                return;
            } else {
                elementAction.clickOnElement(clickableElements, index, inputText);
            }
        }
    }

    public static List<ElementHandle> getVisibleElements() {
        return visibleElements;
    }

    public static List<ClickableElement> getClickableElements() {
        return clickableElements;
    }

    public static class Builder {
        private PageScanner pageScanner;
        private ElementManager elementManager;
        private ElementAction elementAction;
        private UserInteraction userInteraction;

        public Builder setPageScanner(PageScanner pageScanner) {
            this.pageScanner = pageScanner;
            return this;
        }

        public Builder setElementPrinter(ElementManager elementManager) {
            this.elementManager = elementManager;
            return this;
        }

        public Builder setElementAction(ElementAction elementAction) {
            this.elementAction = elementAction;
            return this;
        }

        public Builder setUserInteraction(UserInteraction userInteraction) {
            this.userInteraction = userInteraction;
            return this;
        }

        public PageState build() {
            return new PageState(this);
        }
    }
}

