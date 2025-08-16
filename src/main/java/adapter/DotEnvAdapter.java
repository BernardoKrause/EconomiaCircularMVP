/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapter;

import io.github.cdimascio.dotenv.Dotenv;
/**
 *
 * @author caiof
 */
public final class DotEnvAdapter {
    private static Dotenv dotenv = Dotenv.load();
    
    private DotEnvAdapter(){
    }
    
    public static String getEnv(String chave){
        return dotenv.get(chave);
    }
}
