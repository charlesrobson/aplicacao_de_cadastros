package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.Conexao;
import model.Cliente;

public class ClienteDao {
    private Connection conexao; // conexao
    private PreparedStatement prepararSql; // preparar o sql
    private ResultSet resultado; // resultado de uso do select
    private String sql;

    public ClienteDao() throws ClassNotFoundException, SQLException {
        conexao = new Conexao().getConexao();        
    }
         
    public boolean inserir(Cliente cliente) throws SQLException{
        sql="INSERT INTO cliente(nome,rg,cpf) VALUES (?, ?, ?)";
        
        prepararSql = conexao.prepareStatement(sql);
        prepararSql.setString(1,cliente.getNome());
        prepararSql.setInt(2, cliente.getRg());
        prepararSql.setInt(3, cliente.getCpf());
        prepararSql.execute();
        prepararSql.close();
        return true;       
    }
    
    public boolean alterar(Cliente cliente) throws SQLException{
        sql = "update cliente set nome=?,rg=?,cpf=? where registro=?";
        prepararSql = conexao.prepareStatement(sql);
        prepararSql.setString(1, cliente.getNome());
        prepararSql.setInt(2, cliente.getRg());
        prepararSql.setInt(3, cliente.getCpf());
        prepararSql.setInt(4,cliente.getRegistro());
        prepararSql.execute();
        prepararSql.close();     
        return true;        
    }
    
    public boolean excluir(Cliente cliente) throws SQLException {
        sql = "delete from cliente where registro=?";
        prepararSql = conexao.prepareStatement(sql);
        prepararSql.setInt(1, cliente.getRegistro());
        prepararSql.execute();
        prepararSql.close();
        return true;
    }
    
    
    public ArrayList<Cliente> listar() throws SQLException{
        sql = "select * from cliente order by nome";
        prepararSql = conexao.prepareStatement(sql);
        ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
        resultado = prepararSql.executeQuery();
        
        while (resultado.next()){
            Cliente cliente = new Cliente();
            cliente.setRegistro(resultado.getInt("registro"));
            cliente.setNome(resultado.getString("nome"));
            cliente.setRg(resultado.getInt("rg"));
            cliente.setCpf(resultado.getInt("cpf"));
            listaCliente.add(cliente);
        }      
        prepararSql.close();
        return listaCliente;
    }
    
    
    
    
    
    
    
}
