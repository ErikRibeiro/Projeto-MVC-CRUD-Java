/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.DAO;

import br.com.control.Disciplina;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author erikr
 */
public class ManterDisciplinas extends Conexao{
    public void inserir(Disciplina d) throws Exception {
        abrirBanco();
        String query = "INSERT INTO disciplinas(codigo,descricao,cargahoraria)"+ "values(null,?,?)";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setString(1, d.getDescricao());
        pst.setInt(2, d.getCargahoraria());
        pst.execute();
        JOptionPane.showMessageDialog(null, "Disciplina Inserida com sucesso!");
        fecharBanco();
    }
    public void deletar(Disciplina d) throws Exception {
        abrirBanco();
        String query = "delete from disciplinas where codigo=?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, d.getCodigo());
        pst.execute();
        JOptionPane.showMessageDialog(null, "Disciplina deletada com sucesso!");
        fecharBanco();
}
    public ArrayList<Disciplina> PesquisarTudo() throws Exception {
        ArrayList<Disciplina> discplinas = new ArrayList<Disciplina>();
        try {
            abrirBanco();
            String query = "select * FROM disciplinas";
            pst = (PreparedStatement) con.prepareStatement(query);
            ResultSet tr = pst.executeQuery();
            Disciplina d;
            while (tr.next()) {
                d = new Disciplina();
                d.setCodigo(tr.getInt("codigo"));
                d.setDescricao(tr.getString("descricao"));
                discplinas.add(d);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return discplinas;
    }
    public void PesquisarRegistro(Disciplina d) throws Exception {
        try {
            abrirBanco();
            String query = "select * FROM disciplinas where codigo=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, d.getCodigo());
            ResultSet tr = pst.executeQuery();
            if (tr.next()) {
                d.setCodigo(tr.getInt("codigo"));
                d.setDescricao(tr.getString("descricao"));
                d.setCargahoraria(tr.getInt("cargahoraria"));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
    public void editarDisciplina(Disciplina d) throws Exception{
        abrirBanco();
        String query = "UPDATE disciplinas set descricao = ?, cargahoraria = ? where codigo = ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setString(1, d.getDescricao());
        pst.setInt(2, d.getCargahoraria());
        pst.setInt(3, d.getCodigo());
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Disciplina Alterada com sucesso!");
        fecharBanco();
    }
}