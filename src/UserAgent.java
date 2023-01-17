public class UserAgent {
    private final PlatformEnum platform; // по ощущениям логично использовать ENUM
    private final BrowserEnum browser;  // по ощущениям логично использовать ENUM

    public UserAgent(String str) {
        str = str.replaceAll("\\s+","");
        this.platform = PlatformEnum.setPlatform(str);
        this.browser = BrowserEnum.setBrowser(str);
        //System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return  this.platform.toString() + " " +
                this.browser.toString();
    }

    public UserAgent getUserAgent() {return this;}

    public String getPlatform() {return platform.name();}

    public String getBrowser() {return browser.name();}

}
