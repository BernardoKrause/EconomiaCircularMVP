/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.List;
import model.Oferta;

/**
 *
 * @author caiof
 */
public interface IOfertaRepository {

    public void adicionarOferta(Oferta oferta);
    public void atualizarOferta(Oferta oferta);
    public List<Oferta> buscarTodas();
    public List<Oferta> buscarPorItem(String idC);
}
