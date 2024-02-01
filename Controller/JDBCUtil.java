/*
 * Autor: Lucas Costa Fuganti
 * V360 - ToDo List
 */
package Controller;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 * A simple data source for getting database connections.
 */
public class JDBCUtil {

    private static String url;
    private static String username;
    private static String password;
    private static String consultaTodos;
    private static String consultaEspecifica;
    private static String inserir;
    private static String alterar;
    private static String excluir;
    private static String mudarStatus;

    /**
     * Initializes the data source.
     *
     * @param fileName the name of the property file that contains the database
     * driver, URL, username, and password
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void init(File fileName)
            throws IOException, ClassNotFoundException {

        Properties props = new Properties();
        FileInputStream in = new FileInputStream(fileName);
        props.load(in);
        String driver = props.getProperty("jdbc.driver");
        url = props.getProperty("jdbc.url");
        username = props.getProperty("jdbc.username");
        if (username == null) {
            username = "";
        }
        password = props.getProperty("jdbc.password");
        if (password == null) {
            password = "";
        }
        if (driver != null) {
            Class.forName(driver);
        }
    }
    
    // Carrega as operações para cada entidade, recuperando
    // do respectivo arquivo properties
    public static void initEntidade(String fileName)
            throws IOException, ClassNotFoundException {

        Properties props = new Properties();
        FileInputStream in = new FileInputStream(fileName);
        props.load(in);
        
        consultaTodos = props.getProperty("sqlConsultaTodos");
        consultaEspecifica = props.getProperty("sqlConsultaEspecifico");
        inserir = props.getProperty("sqlInserir");
        alterar = props.getProperty("sqlAlterar");
        excluir = props.getProperty("sqlExcluir");
        mudarStatus = props.getProperty("sqlMudarStatus");
        
    }
    
    /**
     * Gets a connection to the database.
     *
     * @return the database connection
     *
     * @throws java.sql.SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static boolean MovProximo(ResultSet rsdados) {
        try {
            if (rsdados != null) {
                if (!rsdados.isLast()) {
                    rsdados.next();
                    return true;
                }               
            }
        } catch (SQLException erro) {
            System.out.println(erro);
            return false;
        }
        return false;
    }

    public static boolean MovAnterior(ResultSet rsdados) {
        try {
            if (rsdados != null) {
                if (!rsdados.isFirst()) {
                    rsdados.previous();
                    return true;
                }
            }
        } catch (SQLException erro) {
            System.out.println(erro);
            return false;
        }
        return false;
    }

    public static boolean MovUltimo(ResultSet rsdados) {
        try {
            if (rsdados != null) {
                if (!rsdados.isLast()) {
                    rsdados.last();
                    return true;
                }
            }
        } catch (SQLException erro) {
            System.out.println(erro);
            return false;
        }
        return false;
    }

    public static boolean MovPrimeiro(ResultSet rsdados) {
        try {
            if (rsdados != null) {
                if (!rsdados.isFirst()) {
                    rsdados.first();
                    return true;
                }
            }
        } catch (SQLException erro) {
            System.out.println(erro);
            return false;
        }
        return false;
    }
    
    public static String getConsultaTodos() {
        return consultaTodos;
    }
    
    public static String getConsultaEspecifica() {
        return consultaEspecifica;
    }
    
    public static String getInserir() {
        return inserir;
    }
    
    public static String getAlterar() {
        return alterar;
    }
    
    public static String getExcluir() {
        return excluir;
    }
    
    public static String getMudarStatus() {
        return mudarStatus;
    }
}
