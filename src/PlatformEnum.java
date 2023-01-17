public enum PlatformEnum {
    WINDOWS, MACOS, LINUX, ANDROID, IPHONE, IPAD, UNKNOWN;

    public static PlatformEnum setPlatform(String str) {
        str = str.toLowerCase();
        /* ОП больше, чем 3 - MacOS, Win, Linux, но по ТЗ нужно 3 эти
            Iphone, Ipad отнесла к MacOs, Android к Linux - по ТЗ непонятно, стоит ли их классифицировть
            или же четко по 3, а мобильные и др. к приемру относить к неопознанным
        */
        /*if (str.contains("android")) {
           return PlatformEnum.ANDROID;
        } else if (str.contains("iphone")) {
            return PlatformEnum.IPHONE;
        } else if (str.contains("ipad")) {
            return PlatformEnum.IPAD;
        }
        else
         */
        if (str.contains("ubuntu")) {
            return PlatformEnum.LINUX;
        } else if (str.contains("macos") && !str.contains("iphone")) {
            return PlatformEnum.MACOS;
        } else if (str.contains("win")) {
            return PlatformEnum.WINDOWS;
        }
        else return PlatformEnum.UNKNOWN;
    }
}
