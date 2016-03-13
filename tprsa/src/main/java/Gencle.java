import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

//Class to generate a key pair of prime numbers for 't' size blocks
public class Gencle {

    private String name;
    private int t;
    private PrimeNumbersKeyPair primeKeyPair;
    private NormalNumbersKeyPair normalKeyPair;

    public Gencle(String name, int t) {
        this.name = name;
        this.t = t;
        this.primeKeyPair = new PrimeNumbersKeyPair();
        this.normalKeyPair = new NormalNumbersKeyPair();
    }

    public void generateRandomKeyPairOfPrimeNumbers(){
        primeKeyPair.generateRandomKeyPairOfPrimeNumbers(this.t);
    }

    public BigInteger primeKeyPairProduct(){
        return primeKeyPair.product();
    }

    public void generateNormalKeyPair(){
        normalKeyPair.generateRandomKeyPair(this.t, this.primeKeyPair);
    }

    public BigInteger normalKeyPairB(){
        return normalKeyPair.getB();
    }

    public boolean checkNormalKeyPair(){
        return normalKeyPair.checkAAndBValues();
    }

    public void storeKeysInFiles(){
        String text = "size:"+t+"\nn:"+primeKeyPairProduct()+"\nb:"+normalKeyPairB();
        storeData("pub", text);
        text += "\np:"+primeKeyPair.getP()+"\nq:"+primeKeyPair.getQ()+"\na:"+normalKeyPair.getA();
        storeData("priv", text);
    }

    private void storeData(String pubOrPriv, String text){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(name+"."+pubOrPriv, "UTF-8");
            writer.println(text);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getters and Setters
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }
}
