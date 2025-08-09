/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import model.Perfil;

/**
 *
 * @author berna
 */
public class PerfilRepository {
    
    private List<Perfil> perfis; 
    
    public PerfilRepository() {
        perfis = new ArrayList<>();
    }
    
    public void salvarPerfil(Perfil perfil) {
        perfis.add(perfil);
    }
}
