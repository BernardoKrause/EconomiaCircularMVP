/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import model.Comprador;
import model.Perfil;
import model.Vendedor;

/**
 *
 * @author berna
 */
public class PerfilRepository {
    
    private List<Perfil> perfis; 
    
    public PerfilRepository() {
        perfis = new ArrayList<>();
        perfis.add(new Comprador("v-001"));
        perfis.add(new Vendedor("c-001"));
    }
}
