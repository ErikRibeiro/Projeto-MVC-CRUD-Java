/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.control;

/**
 *
 * @author erikr
 */
public class Disciplina {
    private int codigo;
    private String descricao;
    private int cargahoraria;
    
    public int getCodigo() {
        return codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

 public int getCargahoraria() {
     return cargahoraria;
 }

 public void setCargahoraria(int cargahoraria) {
     this.cargahoraria = cargahoraria;
 }
}