package fprogramming;

public class Main {
    public static void main(String[] args) {
        EmailSender emailSender = new Gmail();
        emailSender.send();

        /*EmailSender sender = new EmailSender() {
            @Override
            public void send() {
                System.out.println("Sending email from anonymous class");
            }
        };
        A esto lo conocemos como una clase anonima, sirve para implementar directamente una interfaz sin necesidad de crear una clase
        */

        EmailSender sender = () -> System.out.println("Sending email from lambda expression");
        EmailSender hotmail = () -> System.out.println("Sending email from hotmail");

        hotmail.send();
        sender.send();
    }
}
