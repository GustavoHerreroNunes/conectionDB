/* Classe responsável pela conexão do projeto com o MySQL e execução de comandos SQL */

package conexao;

import javax.swing.JOptionPane;
import java.sql.*;//Importando biblioteca para o uso de comandos SQL no ambiente Java

public class Conexao {
	
	/*Atributos*/
	public final String titJOptionPane = "Conexão MySQL - Java";//atributo que armazena o título das telas de JOptionPane
	final private String driver = "com.mysql.jdbc.Driver";//definindo driver MySQL para acesso aos dados
	final private String url = "jdbc:mysql://127.0.0.1/clientes";//acesso ao db "clientes" no servidor - easyphp
	final private String usuario = "root";//usuário do MySQL - easyphp
	final private String senha = "";//senha do MySQL - easyphp
	private Connection conexao;//objeto para armazenar a conexão aberta
	public Statement statement;//objeto que executa os comandos SQL no ambiente Java
	public ResultSet resultSet;//objeto para armazenar o resultado de uma execução de comando SQL
	
	/*Getter para "titJOptionPane"*/
	public String getTitJOptionPane() {
		return titJOptionPane;
	}
	
	/*Métodos*/
	
	/*Método para abertura da conexão com o MySQL*/
	public boolean conecta() {
		boolean result = true;
		
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, usuario, senha);
			JOptionPane.showMessageDialog(null, "Conexão estabelecida", titJOptionPane, 1);
			
		}catch(ClassNotFoundException Driver) {//Driver não encontrado
			JOptionPane.showMessageDialog(null, "Driver não localizado:\n\n"+Driver, titJOptionPane, 0);
			result = false;
			
		}catch(SQLException Fonte) {//Fonte de Dados não encotrada
			JOptionPane.showMessageDialog(null, "Fonte de Dados não localizada:\n\n"+Fonte, titJOptionPane, 0);
			System.out.println(Fonte);
			result = false;
		}
		
		return result;
	}
	
	/*Método para fechar conexão com o MySQL*/
	public void desconecta() {
		try {
			conexao.close();
			JOptionPane.showMessageDialog(null, "Conexão com a fonte de dados fechada", titJOptionPane, 1);
			
		}catch(SQLException fecha) {
			
		}
	}
	
	/*Método que executa comandos SQL*/
	public void executaSQL(String sql) {
		try {
			statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(sql);
			
		}catch(SQLException execucao) {
			JOptionPane.showMessageDialog(null, "Erro no comando SQL!\nErro: "+execucao, titJOptionPane, 0);
		}
	}
}
