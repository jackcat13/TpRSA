import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;

public class RSA {

    private String name;
    private int size;
    private BigInteger n;
    private BigInteger b;
    private BigInteger a;

    public RSA(String name) {
        this.name = name;
    }

    public void initKey(){
        try (BufferedReader br = new BufferedReader(new FileReader(name+".priv")))
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] resources = sCurrentLine.split(":");
                if (resources[0].equals("n")){
                    n = new BigInteger(resources[1]);
                }else if(resources[0].equals("b")){
                    b = new BigInteger(resources[1]);
                }else if(resources[0].equals("a")){
                    a = new BigInteger(resources[1]);
                }else if(resources[0].equals("size")){
                    size = Integer.parseInt(resources[1]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BigInteger> crypt(String toCrypt) throws UnsupportedEncodingException {
        ArrayList<BigInteger> crypted = new ArrayList<>();
        int nbBits = toCrypt.length()*16;
        int nbBlocks = nbBits/size;
        //Si on ne peut pas faire de block de la taille voulue => block de la taille de la chaine
        if (nbBlocks == 0){
            crypted.add(new BigInteger(toCrypt.getBytes("US-ASCII")).modPow(b,n));
        }//Sinon, Plusieurs blocks selon l'algorithme ECB
        else {
            for (int i = 0; i < nbBlocks; i++) {
                //Création d'un nouveau block
                crypted.add(new BigInteger(toCrypt.substring((i * size)/16, (i * size + size)/16).getBytes("US-ASCII")).modPow(b,n));
            }
            //Création du dernier block en fonction des caractères restants
            crypted.add(new BigInteger(toCrypt.substring((nbBlocks * size)/16, toCrypt.length()).getBytes("US-ASCII")).modPow(b,n));
        }
        return crypted;
    }

    public String decrypt(ArrayList<BigInteger> crypted){
        String decrypted = "";
        for (BigInteger block : crypted){
            BigInteger decryptedBigInt = block.modPow(a,n);
            decrypted += new String(decryptedBigInt.toByteArray());
        }
        return decrypted;
    }

    public ArrayList<BigInteger> sign(ArrayList<BigInteger> crypted){
        ArrayList<BigInteger> signed = new ArrayList<>();
        for (BigInteger block : crypted){
            signed.add(block.modPow(a,n));
        }
        return signed;
    }

    public boolean verify(ArrayList<BigInteger> crypted, ArrayList<BigInteger> signed){
        boolean isVerified = false;
        ArrayList<BigInteger> unsigned = new ArrayList<>();
        for (BigInteger blockSigned : signed){
            unsigned.add(blockSigned.modPow(b,n));
        }
        if (unsigned.equals(crypted)){
            isVerified = true;
        }
        return isVerified;
    }
}
