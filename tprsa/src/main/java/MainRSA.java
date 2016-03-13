import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;

public class MainRSA {

    private static BufferedReader inputReader;

    public static void main(String[] args) throws IOException {

        inputReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Veuillez saisir le nom du destinataire :");
        String name = inputReader.readLine();
        System.out.println("Veuillez saisir le message à crypter :");
        String toCrypt = inputReader.readLine();
        System.out.println("# Message clair : \n"+toCrypt);
        RSA rsa = new RSA(name);
        rsa.initKey();
        ArrayList<BigInteger> crypted = rsa.crypt(toCrypt);
        System.out.println("# Texte Chiffré :");
        for (BigInteger block : crypted){
            System.out.println(block);
        }
        ArrayList<BigInteger> signed = rsa.sign(crypted);
        System.out.println("# Texte signé :");
        for (BigInteger blockSigned : signed){
            System.out.println(blockSigned);
        }
        System.out.println("# On déchiffre le texte chiffré, le résultat devrait être égal à la première chaine en clair:");
        System.out.println(rsa.decrypt(crypted));
        System.out.println("#On vérifie que la signature fournie est bien correcte :");
        if (rsa.verify(crypted, signed)){
            System.out.println("Signature OK");
        }else{
            System.out.println("Mauvaise signature");
        }
    }

}
