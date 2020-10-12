/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClienteDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;

/**
 *
 * @author Cliente
 */
public class ControllerCliente extends HttpServlet {

    private Object Int;

 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // requisições
        
        int opcao = Integer.parseInt(request.getParameter("opcao"));
        
        if (opcao == 1) { // incluir
            Integer rg, cpf;
            String nome;

            nome = request.getParameter("nome");
            rg = Integer.parseInt(request.getParameter("rg"));
            cpf = Integer.parseInt(request.getParameter("cpf"));

            // entregar os dados para o objeto da classe Lutador
            Cliente cliente = new Cliente(nome, rg, cpf);

            try {
                // Inserir no banco de dados através da classe LutadorDao.
                ClienteDao dao = new ClienteDao();
                dao.inserir(cliente);
                // envia os dados para uma view, listagem.jsp
                //RequestDispatcher view = request.getRequestDispatcher("view/listagem.jsp");
                // setar as variaveis que irão para view
                //request.setAttribute("dao", dao);
                //request.setAttribute("lutador", lutador);
                // a view recebe as variaveis request, response
                //view.forward(request, response);
                out.print("<script>alert('Gravado com sucesso!!!');location.href='index.jsp';</script>");
                //RequestDispatcher main = request.getRequestDispatcher("index.jsp");
                //main.forward(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else if(opcao==2){ // exclusão
              int registro = Integer.parseInt(request.getParameter("registro"));
              Cliente cliente = new Cliente();
              cliente.setRegistro(registro);
              ClienteDao dao = new ClienteDao();
              dao.excluir(cliente);
              out.print("<script>alert('Excluído com sucesso!!!');location.href='index.jsp';</script>");

        }
        
        
        
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
