package chatCompletion;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        var client = new Client();
        var sc = new Scanner(System.in);
        var text = sc.nextLine();

        while (!text.equals("exit")) {
            client.chat(text);
            System.out.println("---");
            text = sc.nextLine();
        }
    }
}
