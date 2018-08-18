	package Utils;
// ������Դ��Ϣ������ʾ��The source information of the password encript class has listed in following����
// ������������ò��Ϲ�������ң��ҿ�����ἰʱ������лл��
// If the way of citing this class is not in compliance (or not allowed), please tells me, I will correct (or delete) it in time after seeing your suggestion(s), thank you!
// Password encript class get from: http://www.appsdeveloperblog.com/encrypt-user-password-example-java/ 
// Its author is Sergey Kargopolov.
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
 
public class PasswordUtils {
    
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    //�ҵ�������ޣ������������һ����������ַ�����
    public static String getSalt(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }
    //��������ǻ��������������ֵ��salt����֪����ô���룩�����ɶ�Ӧ��hashֵ����ӭָ��
    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }
    //��������ǻ������ɵ�hashֵ������Base64������������ֵ
    public static String generateSecurePassword(String password, String salt) {
        String returnValue = null;
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
 
        returnValue = Base64.getEncoder().encodeToString(securePassword);
 
        return returnValue;
    }
    // ���ڸ�������ֵ��salt�����ȶ�������������������Ƿ�ƥ��
    public static boolean verifyUserPassword(String providedPassword, String securedPassword, String salt)
    {
        boolean returnValue = false;
        
        // Generate New secure password with the same salt
        String newSecurePassword = generateSecurePassword(providedPassword, salt);
        
        // Check if two passwords are equal
        returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);
        
        return returnValue;
    }
}