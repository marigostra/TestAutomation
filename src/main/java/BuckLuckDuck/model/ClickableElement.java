package BuckLuckDuck.model;

import com.microsoft.playwright.ElementHandle;

public class ClickableElement {
    private String type;
    private ElementHandle element;

    public ClickableElement(String type, ElementHandle element) {
        this.type = type;
        this.element = element;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ElementHandle getElement() {
        return element;
    }

    public void setElement(ElementHandle element) {
        this.element = element;
    }
}
