/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.repository;

import adapter.DotEnvAdapter;

/**
 *
 * @author caiof
 */
public class SeletorRepositoryFactory {

    private SeletorRepositoryFactory(){
    }

    public static IRepositoryFactory obterInstancia() {

        String tipoBanco = DotEnvAdapter.getEnv("REPOSITORY");

        try {
            Class<?> nomeClasse = Class.forName(DotEnvAdapter.getEnv(tipoBanco));
            var construtor = nomeClasse.getConstructor();
            return (IRepositoryFactory) construtor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Erro na reflexão de fábrica da repository, tipo de banco: " + tipoBanco + " " + e.getMessage());
        }
    }
}
