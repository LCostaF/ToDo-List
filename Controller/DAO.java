/*
 * Autor: Lucas Costa Fuganti
 * V360 - ToDo List
 */
package Controller;

import Model.Lista;
import Model.Item;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lucas
 */
public class DAO {
    
    private Connection connection = null;
    private PreparedStatement pstdados = null;
    private ResultSet rsdados = null;
    private static final String path = System.getProperty("user.dir")+"/src/main/java/";
    private static final File config_file = new File(path + "/Controller/ConfigBD.properties");
    private static final File prop_lista = new File(path + "/Controller/ListaProperties.properties");
    private static final File prop_item = new File(path + "/Controller/ItemProperties.properties");
    
    // Conectar com o BD
    public boolean CriaConexao() {
        try {
            JDBCUtil.init(config_file);
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);//configuracao necessaria para confirmacao ou nao de alteracoes no banco de dados.

            DatabaseMetaData dbmt = connection.getMetaData();
            System.out.println("Nome do BD: " + dbmt.getDatabaseProductName());
            System.out.println("Versao do BD: " + dbmt.getDatabaseProductVersion());
            System.out.println("URL: " + dbmt.getURL());
            System.out.println("Driver: " + dbmt.getDriverName());
            System.out.println("Versao Driver: " + dbmt.getDriverVersion());
            System.out.println("Usuario: " + dbmt.getUserName());

            return true;
        } catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC." + erro);
        } catch (IOException erro) {
            System.out.println("Falha ao carregar o arquivo de configuração." + erro);
        } catch (SQLException erro) {
            System.out.println("Falha na conexao, comando sql = " + erro);
        }
        return false;
    }
    
    // Encerrar conexão
    public boolean FechaConexao() {
        if (connection != null) {
            try {
                connection.close();
                return true;
            } catch (SQLException erro) {
                System.err.println("Erro ao fechar a conexão = " + erro);
                return false;
            }
        } else {
            return false;
        }
    }
    
    // Método para recuperar todos os membros de alguma tabela
    public boolean consultarTodos(File opcao) {
        try {
            JDBCUtil.initEntidade(opcao.getAbsolutePath());
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(JDBCUtil.getConsultaTodos(), tipo, concorrencia);
            rsdados = pstdados.executeQuery();
            return true;
        } catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC." + erro);
        } catch (IOException erro) {
            System.out.println("Falha ao carregar o arquivo de configuração." + erro);
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        }
        return false;
    }
    
    // Método para buscar por membros específicos de uma tabela
    public boolean consultarEspecifico(Object objeto) {
        String classe = objeto.getClass().getSimpleName();
        try {
            // Listas
            if(classe.equals("Lista")) {
                Lista lista = (Lista) objeto;
                JDBCUtil.initEntidade(prop_lista.getAbsolutePath());
                int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
                int concorrencia = ResultSet.CONCUR_UPDATABLE;
                pstdados = connection.prepareStatement(JDBCUtil.getConsultaEspecifica(), tipo, concorrencia);
                pstdados.setInt(1, lista.getIdLista());
            // Items
            } else {
                Item item = (Item) objeto;
                JDBCUtil.initEntidade(prop_item.getAbsolutePath());
                int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
                int concorrencia = ResultSet.CONCUR_UPDATABLE;
                pstdados = connection.prepareStatement(JDBCUtil.getConsultaEspecifica(), tipo, concorrencia);
                pstdados.setInt(1, item.getIdItem());
            }
            rsdados = pstdados.executeQuery();
            return true;
        } catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC." + erro);
        } catch (IOException erro) {
            System.out.println("Falha ao carregar o arquivo de configuração." + erro);
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        }
        return false;
    }
    
    // Método para inserir um novo registro em uma tabela
    public boolean inserir(Object objeto) {
        String classe = objeto.getClass().getSimpleName();
        try {
            // Listas
            if(classe.equals("Lista")) {
                Lista lista = (Lista) objeto;
                JDBCUtil.initEntidade(prop_lista.getAbsolutePath());
                int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
                int concorrencia = ResultSet.CONCUR_UPDATABLE;
                pstdados = connection.prepareStatement(JDBCUtil.getInserir(), tipo, concorrencia);
                pstdados.setString(1, lista.getNomeLista());
            // Items
            } else {
                Item item = (Item) objeto;
                JDBCUtil.initEntidade(prop_item.getAbsolutePath());
                int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
                int concorrencia = ResultSet.CONCUR_UPDATABLE;
                pstdados = connection.prepareStatement(JDBCUtil.getInserir(), tipo, concorrencia);
                pstdados.setString(1, item.getDescItem());
            }
            int i = pstdados.executeUpdate();
            if(i == 1) {
                connection.commit();
                return true;
            }
        } catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC." + erro);
        } catch (IOException erro) {
            System.out.println("Falha ao carregar o arquivo de configuração." + erro);
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        }
        return false;
    }
    
    // Método para alterar valores de algum registro
    public boolean alterar(Object objeto) {
        String classe = objeto.getClass().getSimpleName();
        try {
            // Listas
            if(classe.equals("Lista")) {
                Lista lista = (Lista) objeto;
                JDBCUtil.initEntidade(prop_lista.getAbsolutePath());
                int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
                int concorrencia = ResultSet.CONCUR_UPDATABLE;
                pstdados = connection.prepareStatement(JDBCUtil.getAlterar(), tipo, concorrencia);
                pstdados.setString(1, lista.getNomeLista());
                pstdados.setInt(2, lista.getIdLista());
            // Items
            } else {
                Item item = (Item) objeto;
                JDBCUtil.initEntidade(prop_item.getAbsolutePath());
                int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
                int concorrencia = ResultSet.CONCUR_UPDATABLE;
                pstdados = connection.prepareStatement(JDBCUtil.getAlterar(), tipo, concorrencia);
                pstdados.setString(1, item.getDescItem());
                pstdados.setInt(2, item.getIdItem());
            }
            int i = pstdados.executeUpdate();
            if(i == 1) {
                connection.commit();
                return true;
            }
        } catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC." + erro);
        } catch (IOException erro) {
            System.out.println("Falha ao carregar o arquivo de configuração." + erro);
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        }
        return false;
    }
    
    // Método para excluir um registro
    public boolean excluir(int id, File opcao) {
        try {
            JDBCUtil.initEntidade(opcao.getAbsolutePath());
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(JDBCUtil.getExcluir(), tipo, concorrencia);
            pstdados.setInt(1, id);
            int i = pstdados.executeUpdate();
            if(i == 1){
                connection.commit();
                return true;
            }
        } catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC." + erro);
        } catch (IOException erro) {
            System.out.println("Falha ao carregar o arquivo de configuração." + erro);
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        }
        return false;
    }
    
    // Método para recuperar o ResultSet
    public ResultSet getResultSet() {
        return rsdados;
    }
    
    // Métodos para recuperar os paths de cada arquivo properties
    public File getPropLista() {
        return prop_lista;
    }
    
    public File getPropItem() {
        return prop_item;
    }
    
}
