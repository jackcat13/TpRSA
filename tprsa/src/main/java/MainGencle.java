import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainGencle {

    private static BufferedReader inputReader;

    public static void main(String[] args) throws IOException {
        inputReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Veuillez saisir la taille des blocks (int) :");
        int blocSize = Integer.parseInt(inputReader.readLine());
        System.out.println("Veuillez saisir le nom du destinataire :");
        String name = inputReader.readLine();
        Gencle gencle = new Gencle("chr", blocSize);
        System.out.println("# Generating RSA keys for bloc size "+blocSize+" bits");
        gencle.generateRandomKeyPairOfPrimeNumbers();
        System.out.println("# p,q pair generated. Public n is \n" + gencle.primeKeyPairProduct());
        gencle.generateNormalKeyPair();
        System.out.println("# a,b pair generated. Public b is \n" + gencle.normalKeyPairB());
        System.out.println("# Check a*b=1 mod phi(n), a*b=1");
        if (gencle.checkNormalKeyPair()) {
            System.out.println("# OK : Paire de clé stockée dans " + name);
            gencle.storeKeysInFiles();
        }else{
            System.out.println("# FAIL : Paire de clé non stockée");
        }
    }
}
