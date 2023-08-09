package fprogramming;

@FunctionalInterface
public interface EmailSender {
    void send();
    /*
    * Una interfaz funcional solo puede llevar un método abstracto, es decir, una firma o un método sin cuerpo como el de arriba
    * es por ello que colocamos la anotación correspondiente para interfaces funcionales
    * */
}
