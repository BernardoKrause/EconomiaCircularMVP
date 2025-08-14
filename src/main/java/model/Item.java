package model;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private String idC;
    private String tipo;
    private String subcategoria;
    private String tamanho;
    private String cor;
    private Double peso;
    private String composicao;
    private Double precoBase;
    private Double precoFinal;
    private Integer GPWEvitado;
    private Double MCIItem;
    private Integer numeroCiclo;
    private List<Defeito> defeitos;
    
    private Vendedor vendedor;

    public Item(String tipo, String subcategoria, String tamanho, String cor, Double peso, String composicao,Double precoBase) {
        if (tipo.isEmpty()){
            throw new IllegalArgumentException("Tipo é obrigatorio!");
        }
        if (subcategoria.isEmpty()){
            throw new IllegalArgumentException("Subcategoria é obrigatorio!");
        }
        if (tamanho.isEmpty()){
            throw new IllegalArgumentException("Tamanho é obrigatorio!");
        }
        if (cor.isEmpty()){
            throw new IllegalArgumentException("Cor é obrigatorio!");
        }
        if (peso==null || peso<0){
            throw new IllegalArgumentException("Peso é obrigatorio!");
        }
        if (composicao.isEmpty()){
            throw new IllegalArgumentException("Composicao é obrigatorio!");
        }
        if (precoBase == null || precoBase<0){
            throw new IllegalArgumentException("Preco Base é obrigatorio!");
        }
        this.tipo = tipo;
        this.subcategoria = subcategoria;
        this.tamanho = tamanho;
        this.cor = cor;
        this.peso = peso;
        this.composicao = composicao;
        this.precoBase = precoBase;
        this.precoFinal = precoBase;
        this.GPWEvitado = 0;
        this.MCIItem=0.0;
        this.numeroCiclo=0;
        this.defeitos = new ArrayList<Defeito>();
        this.vendedor = null;
    }

    public Item(String tipo, String subcategoria, String tamanho, String cor, Double peso, String composicao,Double precoBase, List<Defeito> defeitos) {
        if (tipo.isEmpty()){
            throw new IllegalArgumentException("Tipo é obrigatorio!");
        }
        if (subcategoria.isEmpty()){
            throw new IllegalArgumentException("Subcategoria é obrigatorio!");
        }
        if (tamanho.isEmpty()){
            throw new IllegalArgumentException("Tamanho é obrigatorio!");
        }
        if (cor.isEmpty()){
            throw new IllegalArgumentException("Cor é obrigatorio!");
        }
        if (peso==null || peso<0){
            throw new IllegalArgumentException("Peso é obrigatorio!");
        }
        if (composicao.isEmpty()){
            throw new IllegalArgumentException("Composicao é obrigatorio!");
        }
        if (precoBase == null || precoBase<0){
            throw new IllegalArgumentException("Preco Base é obrigatorio!");
        }
        this.tipo = tipo;
        this.subcategoria = subcategoria;
        this.tamanho = tamanho;
        this.cor = cor;
        this.peso = peso;
        this.composicao = composicao;
        this.precoBase = precoBase;
        this.precoFinal = precoBase;
        this.GPWEvitado = 0;
        this.MCIItem=0.0;
        this.numeroCiclo=0;
        this.defeitos = defeitos;
    }

    public Item(String idC, String tipo, String subcategoria, String tamanho, String cor, Double peso, String composicao, Double precoBase, Double precoFinal, Integer gpwEvitado, Double mciItem, Integer numeroCiclo) {
        this.idC = idC;
        this.tipo = tipo;
        this.subcategoria = subcategoria;
        this.tamanho = tamanho;
        this.cor = cor;
        this.peso = peso;
        this.composicao = composicao;
        this.precoBase = precoBase;
        this.precoFinal = precoFinal;
        this.GPWEvitado = gpwEvitado;
        this.MCIItem = mciItem;
        this.numeroCiclo = numeroCiclo;
    }
    
    public String getIdC() {
        return idC;
    }

    public String getTipo() {
        return tipo;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public String getTamanho() {
        return tamanho;
    }

    public String getCor() {
        return cor;
    }

    public Double getPeso() {
        return peso;
    }

    public String getComposicao() {
        return composicao;
    }

    public Double getPrecoBase() {
        return precoBase;
    }

    public Double getPrecoFinal() {
        return precoFinal;
    }

    public Integer getGPWEvitado() {
        return GPWEvitado;
    }

    public Double getMCIItem() {
        return MCIItem;
    }

    public Integer getNumeroCiclo() {
        return numeroCiclo;
    }

    public List<Defeito> getDefeitos() {
        return defeitos;
    }
    
    public Vendedor getVendedor(){
        return vendedor;
    }

    public void setPrecoFinal(Double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public void setGPWEvitado(Integer GPWEvitado) {
        this.GPWEvitado = GPWEvitado;
    }

    public void setMCIItem(Double MCIItem) {
        this.MCIItem = MCIItem;
    }

    public void setNumeroCiclo(Integer numeroCiclo) {
        this.numeroCiclo = numeroCiclo;
    }
    
    public void setVendedor(Vendedor vendedor){
        if(vendedor==null){
            throw new RuntimeException("Vendedor informado não existe!");
        }
        if(this.vendedor!=null){
            throw new RuntimeException("Esse Item ja possui um Vendedor!");
        }
        this.vendedor=vendedor;
    }

    public void gerarIdC(Integer codigo) {
        String idC = String.valueOf(codigo);
        //codigo
        if (this.idC != null){
            throw new IllegalArgumentException("O ID-C desse item ja foi gerado!");
        }
        this.idC=idC;
    }

    public void calcularPrecoFinal(){
        //codigo
    }

    public void calcularIndicadoresAmbientais(){
        //codigo
    }

    public void addDefeito(Defeito defeito){
        this.defeitos.add(defeito);
    }

    @Override
    public String toString() {
        return "Item{" +
                "idC='" + idC + '\'' +
                ", tipo='" + tipo + '\'' +
                ", subcategoria='" + subcategoria + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", cor='" + cor + '\'' +
                ", peso=" + peso +
                ", composicao='" + composicao + '\'' +
                ", precoBase=" + precoBase +
                ", precoFinal=" + precoFinal +
                ", GPWEvitado=" + GPWEvitado +
                ", MCIItem=" + MCIItem +
                ", numeroCiclo=" + numeroCiclo +
                ", defeitos=" + defeitos +
                ", vendedor=" + vendedor +
                '}';
    }
}
