import java.math.BigInteger;
import java.util.Random;

public class PrimeNumbersKeyPair {

    private BigInteger p;
    private BigInteger q;

    public PrimeNumbersKeyPair(){}

    public PrimeNumbersKeyPair(BigInteger p, BigInteger q) {
        this.p = p;
        this.q = q;
    }

    public void generateRandomKeyPairOfPrimeNumbers(int t){
        Random randomGenerator = new Random();
        BigInteger b1; BigInteger b2;
        do {
            b1 = new BigInteger(t/2, randomGenerator);
            b2 = new BigInteger(t/2, randomGenerator);
        } while (!b1.isProbablePrime(50) || !b2.isProbablePrime(50));
        this.p = b1;
        this.q = b2;
    }

    public BigInteger product(){
        return p.multiply(q);
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }
}
