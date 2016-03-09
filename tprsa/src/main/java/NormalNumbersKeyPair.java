import java.math.BigInteger;
import java.util.Random;

public class NormalNumbersKeyPair {

    private BigInteger a;
    private BigInteger b;
    private BigInteger phi;

    public NormalNumbersKeyPair(){}

    public NormalNumbersKeyPair(BigInteger a, BigInteger b) {
        this.a = a;
        this.b = b;
    }

    public void generateRandomKeyPair(int t, PrimeNumbersKeyPair primeKeyPair){
        Random randomGenerator = new Random();
        phi = primeKeyPair.getP().subtract(new BigInteger("1")).multiply( primeKeyPair.getQ().subtract(new BigInteger("1")) );
        b = new BigInteger(t/2, randomGenerator).mod(phi);
        a = b.modInverse( phi );
    }

    public boolean checkAAndBValues(){
        boolean result = false;
        if (a.multiply(b).mod(phi).equals(new BigInteger("1"))){
            result = true;
        }
        return result;
    }

    public BigInteger getA() {
        return a;
    }

    public BigInteger getB() {
        return b;
    }
}
