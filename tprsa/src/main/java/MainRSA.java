public class MainRSA {

    public static void main(String[] args){

        String name = "chr";
        String toCrypt = "# Message clair : \nBonjour, je suis un message qui va etre crypté en RSA :D";
        RSA rsa = new RSA(name);
        rsa.initPublicKey();
        System.out.println("# Texte Chiffré :\n" + rsa.chiffre(toCrypt));
    }

}
