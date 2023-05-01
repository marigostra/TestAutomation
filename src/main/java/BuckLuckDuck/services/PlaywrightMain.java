package BuckLuckDuck.services;

import BuckLuckDuck.options.Options;
import BuckLuckDuck.options.OptionsService;
import com.microsoft.playwright.*;


import java.util.logging.Logger;


public class PlaywrightMain {
    static final Logger LOGGER = Logger.getLogger(PlaywrightMain.class.getName());

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setChannel("chrome")
            );
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            Options options = OptionsService.setUserOptions(args);

            page.onFrameNavigated(event -> {
                options.setNavigateLink(page.url());
                LOGGER.info("Navigating to: " + options.getNavigateLink());
                LOGGER.info("Page title: " + page.title());
            });

            page.navigate(options.getNavigateLink());
            // page.navigate("https://marigostra.ru/");
            // page.navigate("https://vk.com/");
            // page.navigate("https://letcode.in/edit");
            // page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");

            PageScanner pageScanner = new PageScanner();
            ElementAction elementAction = new ElementAction(page);
            ElementManager elementManager = new ElementManager();
            ElementUpdater elementUpdater = new ElementUpdater(page, options);
            UserInteraction userInteraction = new UserInteraction.Builder()
                    .options(options)
                    .elementAction(elementAction)
                    .elementUpdater(elementUpdater)
                    .elementManager(elementManager)
                    .build();
            PageState pageState = new PageState.Builder()
                    .setPageScanner(pageScanner)
                    .setElementPrinter(elementManager)
                    .setElementAction(elementAction)
                    .setUserInteraction(userInteraction)
                    .build();
            elementUpdater.setPageState(pageState);

            while (true) {
                pageState.updateElements(page);
                pageState.printElements(options);

                if (options.isAskForClick()) {
                    pageState.interactWithElements(options);
                } else {
                    break;
                }
            }

            page.close();
            browser.close();
            playwright.close();
            LOGGER.info("Browser closed.");
        }
    }
}