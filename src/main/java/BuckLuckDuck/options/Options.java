package BuckLuckDuck.options;

public class Options {
    boolean showVisible = false;
    boolean showClickable = true;
    boolean askForClick = false;
    String navigateLink = "";

    public Options() {
    }

    public boolean isShowVisible() {
        return showVisible;
    }

    public void setShowVisible(boolean showVisible) {
        this.showVisible = showVisible;
    }

    public boolean isShowClickable() {
        return showClickable;
    }

    public void setShowClickable(boolean showClickable) {
        this.showClickable = showClickable;
    }

    public String getNavigateLink() {
        return navigateLink;
    }

    public void setNavigateLink(String navigateLink) {
        this.navigateLink = navigateLink;
    }

    public boolean isAskForClick() {
        return askForClick;
    }

    public void setAskForClick(boolean askForClick) {
        this.askForClick = askForClick;
    }
}
