package com.fragmento.popularbutton;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by IntelliJ IDEA.
 * User: enmanuelreyes
 * Date: 8/30/20
 * Time: 1:09 AM
 */
public class Signature {
    private static final String HMAC_SHA512 = "HmacSHA512";

    protected static String encrypt(String data, String key, boolean encodearData) {
        String output = "";

        try {
            Mac sha512Hmac = Mac.getInstance(HMAC_SHA512);

            byte[] keybyte = key.getBytes(StandardCharsets.UTF_8);
            //Definimos el HMAC con la key
            SecretKeySpec keySpec = new SecretKeySpec(keybyte, HMAC_SHA512);
            sha512Hmac.init(keySpec);
            //pasamos el texto a encriptar a bytes
            byte[] hashbytes = data.getBytes(StandardCharsets.UTF_8);
            //Encriptamos
            byte[] hashmac = sha512Hmac.doFinal(hashbytes);
            //Pasamos lo encriptado a Hexa
            String dataFirma = byteArrayToString(hashmac); // VS 2008 hacia adelante
            //string dataFirma = ByteToString(hashmac); // Otras versiones

            //pasamos a bytes el texto en HEX en minuscula (por alguna razon C# los deja con mayuscula)
            hashbytes = dataFirma.toLowerCase().getBytes(StandardCharsets.UTF_8);


            byte[] databytes = data.getBytes(StandardCharsets.UTF_8);
            //String hashmac = obj.ComputeHash(bytes);
            if (encodearData) //encodear data directo
            {
                output =  Base64.getEncoder().encodeToString(databytes);
            } else { //encodear data resumen con hmac
                output = Base64.getEncoder().encodeToString(hashbytes);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return output;
    }

    private static String byteArrayToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
