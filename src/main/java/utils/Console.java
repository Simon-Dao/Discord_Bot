package utils;

import listeners.PassiveEventListener;

import java.util.Scanner;

public class Console {

    Thread console;

    public void init() {
        console = new Thread(()-> {

            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                String[] args = input.split(" ");

                switch (args[0]) {
                    case "time":
                        if(args.length == 2)
                            setTime(args[1]);
                        break;
                    case "log":
                            System.out.println(PassiveEventListener.sent + " "+ PassiveEventListener.targetTime + " "+ PassiveEventListener.time+" "+ PassiveEventListener.time.startsWith(PassiveEventListener.targetTime));
                        break;
                    case "sent":
                        if(args.length == 2){
                            PassiveEventListener.sent = Boolean.parseBoolean(args[1]);
                        }
                        break;
                    case "send":

                }
            }

        });
    }

    public void start() {
        console.start();
    }

    public void setTime(String time) {
        PassiveEventListener.targetTime = time;
        PassiveEventListener.sent = false;
    }
}
