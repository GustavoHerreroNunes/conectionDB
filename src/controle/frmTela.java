package controle;

import conexao.Conexao;//Importando classe de conexão (MySQL)
import javax.swing.table.DefaultTableModel;//Importando classe para reconhecimento de Jtable
import java.sql.*;//Importando biblioteca para o uso de comandos SQL no ambiente Java

import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;

public class frmTela extends JFrame {

	Conexao con_cliente;//Instância da classe de conexão
	private JPanel contentPane;
	private JTextField txbDtNasc;
	private JTextField txbNome;
	private JTextField txbCod;
	private JTextField txbTelef;
	private JTextField txbEmail;
	private JTable tblClientes;

	/**
	 * Launch the application.
	 */
	
	/*Método para preencher a Jtable com os dados da tabela "tblclientes" do banco de dados*/
	public void preencherTabela() {
		tblClientes.getColumnModel().getColumn(0).setPreferredWidth(4);
		tblClientes.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblClientes.getColumnModel().getColumn(2).setPreferredWidth(11);
		tblClientes.getColumnModel().getColumn(3).setPreferredWidth(14);
		tblClientes.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
		modelo.setNumRows(0);
		
		try {
			con_cliente.resultSet.beforeFirst();
			while(con_cliente.resultSet.next()) {
				modelo.addRow(new Object[] {
						con_cliente.resultSet.getString("cod"),
						con_cliente.resultSet.getString("nome"),
						con_cliente.resultSet.getString("dt_nasc"),
						con_cliente.resultSet.getString("telefone"),
						con_cliente.resultSet.getString("email")
				});
			}
			
		}catch(SQLException erro) {
			JOptionPane.showMessageDialog(null, "\nErro ao listar dados da tabela:\n\n" + erro, con_cliente.getTitJOptionPane(), 0);
		}
	}
	
	/*Método para mostrar os dados da tabela "tbclientes" nas JTextField do formulário*/
	public void mostrar_Dados() {
		try {
			txbCod.setText(con_cliente.resultSet.getString("cod"));//Código
			txbNome.setText(con_cliente.resultSet.getString("nome"));//Nome
			txbDtNasc.setText(con_cliente.resultSet.getString("dt_nasc"));//Data de Nascimento
			txbTelef.setText(con_cliente.resultSet.getString("telefone"));//Telefone
			txbEmail.setText(con_cliente.resultSet.getString("email"));//E-mail
			
		}catch(SQLException erro) {
			JOptionPane.showMessageDialog(null, "Não localizou dados:\n\n" + erro, con_cliente.getTitJOptionPane(), 0);
		}
	}
	
	/*Método para mostrar os dados do 1º registro da tabela "tbclientes" nas JTextField do formulário*/
	public void posicionarRegistro() {
		try {
			con_cliente.resultSet.first();//posiciona no 1º registro
			mostrar_Dados();//buscando e mostrando os dados
			
		}catch(SQLException erro) {
			JOptionPane.showMessageDialog(null, "Não foi possível posicionar no primeiro registro:\n\n" + erro, con_cliente.getTitJOptionPane(), 0);
		}
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmTela frame = new frmTela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmTela() {
		setResizable(false);
		setTitle("Cadastro de Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 950, 750);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCod = new JLabel("C\u00F3digo:");
		lblCod.setFont(new Font("Arial", Font.PLAIN, 19));
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 19));
		
		JLabel lblDtNasc = new JLabel("Data de Nascimento:");
		lblDtNasc.setFont(new Font("Arial", Font.PLAIN, 19));
		
		JLabel lblTelef = new JLabel("Telefone:");
		lblTelef.setFont(new Font("Arial", Font.PLAIN, 19));
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 19));
		
		txbDtNasc = new JTextField();
		txbDtNasc.setFont(new Font("Arial", Font.PLAIN, 16));
		txbDtNasc.setColumns(10);
		
		txbNome = new JTextField();
		txbNome.setFont(new Font("Arial", Font.PLAIN, 16));
		txbNome.setColumns(10);
		
		txbCod = new JTextField();
		txbCod.setFont(new Font("Arial", Font.PLAIN, 16));
		txbCod.setColumns(10);
		
		txbTelef = new JTextField();
		txbTelef.setFont(new Font("Arial", Font.PLAIN, 16));
		txbTelef.setColumns(10);
		
		txbEmail = new JTextField();
		txbEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		txbEmail.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(64)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDtNasc)
								.addComponent(lblNome)
								.addComponent(lblCod)
								.addComponent(lblTelef)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txbEmail, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
								.addComponent(txbTelef, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
								.addComponent(txbCod, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
								.addComponent(txbNome, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
								.addComponent(txbDtNasc, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 886, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(113)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCod)
						.addComponent(txbCod, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(txbNome, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDtNasc, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(txbDtNasc, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelef, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(txbTelef, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(txbEmail, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
		);
		
		tblClientes = new JTable();
		tblClientes.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nome", "Data de Nascimento", "Telefone", "Email"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblClientes.getColumnModel().getColumn(2).setPreferredWidth(137);
		scrollPane.setViewportView(tblClientes);
		contentPane.setLayout(gl_contentPane);
		
		/*Estabelecendo Conexão*/
		con_cliente = new Conexao();//Iniciando instância
		con_cliente.conecta();//Realizando conexão com a fonte de dados (MySQL)
		con_cliente.executaSQL("select * from tbclientes order by cod");
		preencherTabela();
		posicionarRegistro();
		tblClientes.setAutoCreateRowSorter(true);//ativando a classificação ordenada da tabela
	}
}
