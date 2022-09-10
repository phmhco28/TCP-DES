import java.nio.charset.StandardCharsets;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "Chào cậu";
//        byte[] arr =  a.getBytes(StandardCharsets.UTF_8);
//        for (byte s : arr) {
//            System.out.println(s);
//            System.out.println(String.format("%8s", Integer.toBinaryString(s)).replaceAll(" ", "0"));
//        }
//        //String.format("%8s", Integer.toBinaryString(s)).replaceAll(" ", "0")
//        String bString = new String(arr, StandardCharsets.UTF_8);
//        System.out.println(bString);
        
        DES des = new DES();
        String encode = des.encodeBase64(a);
        System.out.println(encode);
        System.out.println("DES to Bin: " + des.textToBin(encode));
        System.out.print("ENCRYPTION: ");
        String encryption = des.encryption(encode, "Hellohel");
        System.out.println(des.binToText(encryption));
        
        System.out.println(des.decodeBase64(encode));
        System.out.print("DECRYPTION: ");
        String decryption = des.decryption(encryption, "Hellohel");
        System.out.println(decryption);
        System.out.println("decode base64: " + des.decodeBase64(decryption));
        
        
	}

}
