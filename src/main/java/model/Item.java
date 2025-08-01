package model;

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

    protected void gerarIdC(Integer codigo) {
        String idC = String.valueOf(codigo);
        //codigo
        if (!idC.isEmpty()){
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
}
