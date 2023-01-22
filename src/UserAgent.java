public class UserAgent {
    private final PlatformEnum platform; // по ощущениям логично использовать ENUM
    private final BrowserEnum browser;  // по ощущениям логично использовать ENUM
    private final boolean isBot;
    public UserAgent(String str) {
        str = str.replaceAll("\\s+","");
        this.platform = PlatformEnum.setPlatform(str);
        this.browser = BrowserEnum.setBrowser(str);
        isBot = isBot(str);
        //System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return  this.platform.toString() + " " +
                this.browser.toString() +  " " +
                this.isBot;
    }

    public UserAgent getUserAgent() {return this;}

    public String getPlatform() {return platform.name();}

    public String getBrowser() {return browser.name();}

    private boolean isBot(String str){
        if (str.toLowerCase().contains("bot"))
        return true;
        else return false;
    }

    public boolean getIsBot(){return this.isBot;}
}
