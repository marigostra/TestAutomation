package BuckLuckDuck.options;

public class OptionsService {
    private static final String LINK_COMMAND = "-l";
    private static final String SHOW_VISIBLE = "-v";
    private static final String SHOW_CLICKABLE = "-c";
    private static final String ASK_FOR_CLICK = "-a";

    public static Options setUserOptions(String[] args) {
        Options options = new Options();

        for (String arg : args) {
            if (arg.contains(LINK_COMMAND))
                setLink(arg, options);

            if (arg.equals(SHOW_VISIBLE))
                options.setShowVisible(!options.isShowVisible());

            if (arg.equals(SHOW_CLICKABLE))
                options.setShowClickable(!options.isShowClickable());

            if (arg.equals(ASK_FOR_CLICK))
                options.setAskForClick(!options.isAskForClick());
        }

        return options;
    }

    private static void setLink(String arg, Options options) {
        String link = arg.substring(arg.indexOf("=") + 1);
        if (!link.matches("https:\\/\\/+"))
            link = "https://".concat(link);

        options.setNavigateLink(link);
    }
}
