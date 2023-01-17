package br.com.rodrigo.frete.api.desconto;

import br.com.rodrigo.frete.api.models.Frete;

abstract class Verificador {
    
    protected Verificador proximo;

    public void setProximo(Verificador proximo) {
        this.proximo = proximo;
    }

    public abstract boolean verifica(String cepOrigem, String cepDestino);

    public Frete calcular(Frete frete, String cepOrigem, String cepDestino) {
        if(verifica(cepOrigem, cepDestino)) {
            return calcular(frete);
        }
        else if(proximo != null) {
            return proximo.calcular(frete, cepOrigem, cepDestino);
        }
        return frete;
    }

    public abstract Frete calcular(Frete frete);
}

