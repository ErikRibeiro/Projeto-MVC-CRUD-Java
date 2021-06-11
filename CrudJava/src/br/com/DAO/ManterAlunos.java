/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.DAO;

import br.com.control.Aluno;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author erikr
 */
public class ManterAlunos extends Conexao{
    public void inserirAluno(Aluno a) throws Exception {
        abrirBanco();
        String query = "INSERT INTO alunos(codigo,nome,email)"+ "values(null,?,?)";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setString(1, a.getNome());
        pst.setString(2, a.getEmail());
        pst.execute();
        JOptionPane.showMessageDialog(null, "Aluno Inserido com sucesso!");
        fecharBanco();
    }
    public void deletarAluno(Aluno a) throws Exception {
        abrirBanco();
        String query = "delete from alunos where codigo=?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, a.getCodigo());
        pst.execute();
        JOptionPane.showMessageDialog(null, "Aluno deletado com sucesso!");
        fecharBanco();
}
    public ArrayList<Aluno> PesquisarTudo() throws Exception {
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        try {
            abrirBanco();
            String query = "select * FROM alunos";
            pst = (PreparedStatement) con.prepareStatement(query);
            ResultSet tr = pst.executeQuery();
            Aluno a;
            while (tr.next()) {
                a = new Aluno();
                a.setCodigo(tr.getInt("codigo"));
                a.setNome(tr.getString("nome"));
                alunos.add(a);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return alunos;
    }
    public void PesquisarRegistro(Aluno a) throws Exception {
        try {
            abrirBanco();
            String query = "select * FROM alunos where codigo=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, a.getCodigo());
            ResultSet tr = pst.executeQuery();
            if (tr.next()) {
                a.setCodigo(tr.getInt("codigo"));
                a.setNome(tr.getString("nome"));
                a.setEmail(tr.getString("email"));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
    public void editarAluno(Aluno a) throws Exception{
        abrirBanco();
        String query = "UPDATE alunos set nome = ?, email = ? where codigo = ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setString(1, a.getNome());
        pst.setString(2, a.getEmail());
        pst.setInt(3, a.getCodigo());
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Aluno Alterado com sucesso!");
        fecharBanco();
    }
}