package model;

public class Defeito {
    private String descricao;
    private Integer percentualDesconto;
    private Double valorDesconto;

    public Defeito(String descricao, Integer percentualDesconto) {
        if(descricao.isEmpty()){
            throw new IllegalArgumentException("descricao deve ser preenchida!");
        }
        if(percentualDesconto==null || percentualDesconto < 0){
            throw new IllegalArgumentException("percentual deve ser preenchida!");
        }
        this.descricao = descricao;
        this.percentualDesconto = percentualDesconto;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getPercentualDesconto() {
        return percentualDesconto;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }
}
