package utils;

import java.util.HashMap;

public class ID {

    public Guild GARFIELD_ROBOTICS_SERVER = new Guild("754079612019146836");
    public Guild BOT_TEST_SERVER          = new Guild("737894260765884470");

    public ID() {
        GARFIELD_ROBOTICS_SERVER.channels.put("utils","754079612451160177");
        GARFIELD_ROBOTICS_SERVER.channels.put("lesson-chat","754390204101558343");
        GARFIELD_ROBOTICS_SERVER.channels.put("announcements","754389773388480532");
        GARFIELD_ROBOTICS_SERVER.channels.put("bot-test","754406137755074570");

        BOT_TEST_SERVER.channels.put("bot-test", "738131303693680660");
    }
}
