package BuckLuckDuck.services;

import BuckLuckDuck.options.Options;
import com.microsoft.playwright.Page;

public class ElementUpdater {
    private final Page page;
    private PageState pageState;
    private final Options options;

    public ElementUpdater(Page page, Options options) {
        this.page = page;
        this.options = options;
    }

    public void updateAndPrintElements() {
        pageState.updateElements(page);
        pageState.printElements(options);
    }

    public void setPageState(PageState pageState) {
        this.pageState = pageState;
    }
}
