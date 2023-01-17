public enum BrowserEnum {
    FIREFOX, SEAMONKEY, CHROME, CHROMIUM, OPERA, SAFARI, IE, UNKNOWN;

    public static BrowserEnum setBrowser(String str) {
        str = str.toLowerCase();

        if (str.contains("firefox") && !str.contains("seamonkey")) {
            return BrowserEnum.FIREFOX;
        } else if (str.contains("seamonkey")) {
            return BrowserEnum.SEAMONKEY;
        } else if (str.contains("safari") && !str.contains("chrome") && !str.contains("chromium")) {
            return BrowserEnum.SAFARI;
        } else if (str.contains("chrome") && !str.contains("chromium") && !str.contains("edg"))  {
            return BrowserEnum.CHROME;
        } else if (str.contains("chromium")) {
            return BrowserEnum.CHROMIUM;
        }  else if (str.contains("opera") || str.contains("opr")) {
            return BrowserEnum.OPERA;
        } else if (str.contains("msie") || str.contains("trident")) {
            return BrowserEnum.IE;
        }
        else return BrowserEnum.UNKNOWN;
    }
}

