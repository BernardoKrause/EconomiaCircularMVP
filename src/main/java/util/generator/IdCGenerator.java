/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.generator;

import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author berna
 */
public class IdCGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int ID_LENGTH = 12;
    private static final Random RANDOM = new SecureRandom();

    public static String gerarIdC() {
        StringBuilder idC = new StringBuilder(ID_LENGTH);
        
        for (int i = 0; i < ID_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            idC.append(CHARACTERS.charAt(randomIndex));
        }
        
        return idC.toString();
    }
}