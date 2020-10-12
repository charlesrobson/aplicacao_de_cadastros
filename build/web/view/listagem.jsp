<%@page import="model.Cliente"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach" %>
<%@page import="dao.ClienteDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem</title>
    </head>
    <body>
        <h1>Listagem de Clientes</h1>

        <table border="1">
            <tr>
                <td>NOME</td>
                <td>RG</td>
                <td>CPF</td>
                <td>OPÇÃO</td>
            </tr>   
            <%
                ClienteDao dao = new ClienteDao();
                for (Cliente elemento : dao.listar()) {
                    out.print("<tr><td>" + elemento.getNome()
                            + "</td><td>" + elemento.getRg()
                            + "</td><td>" + elemento.getCpf()
                            + "</td><td><a href='ControllerCliente?opcao=2&registro="
                            + elemento.getRegistro() + "'><button>Excluir</button></a> <a href='view/alterarcliente.jsp?opcao=3&registro="
                            + elemento.getRegistro() + "'><button>Editar</button></a></td></tr>");
                }
            %>    
        </table>
    </body>
</html>
