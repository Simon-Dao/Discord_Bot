package secretsanta;

import java.util.ArrayList;

public class ChristmasGiftBuilder {

    ArrayList<Santa> random = new ArrayList<>();

    public void build(ArrayList<Santa> santas) {

        for (int i = 0; i < santas.size(); i++) {
            createFile(santas.get(i));
            writeToFile(santas.get(i));
        }
    }

    private void createFile(Santa santa) {
    }

    //todo make everyone have a person to send presents to
    private void writeToFile(Santa santa) {
        StringBuilder message = new StringBuilder();

        message.append("Hello Santa, your child this year is Simon\n");
        message.append("This is their wishlist:\n");
        santa.wishlist.forEach(s -> message.append(s+"\n"));

        System.out.println(message.toString());
    }
}
