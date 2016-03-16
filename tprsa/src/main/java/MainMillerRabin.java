import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Random;

public class MainMillerRabin {
    private static BufferedReader inputReader;

    public static void main(String[] args)throws IOException {
        boolean estPremier=false;

        inputReader = new BufferedReader(new InputStreamReader(System.in));
        /*System.out.println("Veuillez saisir le nombre que vous voulez tester :");
        String chaine = inputReader.readLine();
        BigInteger nb = new BigInteger(chaine);
        if (estPremier == testMillerRabin(nb)){
            System.out.println("test de MillerRabin indique que le nombre est probablement Premier");
        }
        else{
            System.out.println("test de MillerRabin indique que le nombre est composite");
        }*/
        System.out.println("Veuillez saisir le nombre que vous voulez tester :\n");
        String chaine2 = inputReader.readLine();
        BigInteger x = new BigInteger(chaine2);
        System.out.println("Veuillez saisir le nombre de fois que vous souhaitez répéter le test :\n");
        String chaine3 = inputReader.readLine();
        int k=Integer.parseInt(chaine3);
        if (estPremierRapide(x,k)){
            System.out.println("test de MillerRabin répété "+k+" fois indique que le nombre est probablement premier");
        }
        else{
            System.out.println("test de MillerRabin répété "+k+" fois indique que le nombre est composite");
        }
    }

    private static boolean testMillerRabin(BigInteger nb,BigInteger a) {

        // test si le nombre est pair
        if (nb.mod(new BigInteger("2")).equals(new BigInteger("0"))) {
            return false;
        }
        else {
            BigInteger r=null,s = null,x=null;
            r=nb.subtract(new BigInteger("1"));
            s=new BigInteger("0");
            while (r.mod(new BigInteger("2")).equals(new BigInteger("0"))) {
                r=r.divide(new BigInteger("2"));
                s=s.add(new BigInteger("1"));
            }
            
            x=a.modPow(r,nb);
            if (x.equals(new BigInteger("1")) || x.equals(nb.subtract(new BigInteger("1")))){
                return true;
            }
            BigInteger j=new BigInteger("1");
            while (j.compareTo(s)==-1){
                x=x.multiply(x).mod(nb);
                if (x.equals(nb.subtract(new BigInteger("1")))){
                    return true;
                }
                if (x.equals(new BigInteger("1"))){
                    return false;
                }
                j=j.add(new BigInteger("1"));
            }
            return true;
        }
    }

    private static boolean estPremierRapide(BigInteger n,int k){
        BigInteger rdm;
        Random randomGenerator = new Random();
        rdm = new BigInteger(n.bitLength(), randomGenerator);
        while ( k > 0 ) {
            if (testMillerRabin(n,rdm)==false)
                return false;
            k--;
        }
        return true;
    }
}