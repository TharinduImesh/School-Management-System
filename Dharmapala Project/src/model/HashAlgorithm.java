/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Sandu
 */
public class HashAlgorithm {
    
    public String generateStorngPasswordHash(String password){
        String hashed_pw = "";
        try {
            int iterations = 1000;
            char[] chars = password.toCharArray();
            byte[] salt = getSalt().getBytes();

            PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            hashed_pw = iterations + ":" + toHex(salt) + ":" + toHex(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(HashAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hashed_pw;
    }
     
    private String getSalt(){
        String salt_value = "";
         try {
             SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
             byte[] salt = new byte[16];
             sr.nextBytes(salt);
             salt_value = salt.toString();
         } catch (NoSuchAlgorithmException ex) {
             Logger.getLogger(HashAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
         }
         return salt_value;
    }
     
    private String toHex(byte[] array){
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
    
    public boolean validatePassword(String originalPassword, String storedPassword){
        int diff = 0;
        try {
            String[] parts = storedPassword.split(":");
            int iterations = Integer.parseInt(parts[0]);
            byte[] salt = fromHex(parts[1]);
            byte[] hash = fromHex(parts[2]);
            
            PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] testHash = skf.generateSecret(spec).getEncoded();
            
            diff = hash.length ^ testHash.length;
            for(int i = 0; i < hash.length && i < testHash.length; i++)
            {
                diff |= hash[i] ^ testHash[i];
            }
            
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(HashAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return diff == 0;
    }
    private byte[] fromHex(String hex){
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
//    public static void main(String[] args) {
//        HashAlgorithm h = new HashAlgorithm();
//        System.out.println(h.generateStorngPasswordHash("1234"));
//    }
}

