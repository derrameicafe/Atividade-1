/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto (ProdutosDTO produto) {
        int status;
        
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("insert into produtos (nome, valor, status) values(?,?,?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, "A Venda");
            status = prep.executeUpdate();
            if(status == 1) {
                return true;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar cadastrar produto: " + e.getMessage());
        }
        return false;
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        try {
            
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("SELECT * FROM produtos");
            ResultSet rs = prep.executeQuery();
            
            while(rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                
                listagem.add(produto);
            }
            
            return listagem;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar obter lista de produtos: " + e.getMessage());
            return null;
        }
    }
    
        
}

