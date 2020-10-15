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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class frmTela extends JFrame {

	Conexao con_cliente;//Instância da classe de conexão
	private JPanel contentPane;
	private JTextField txbDtNasc;
	private JTextField txbNome;
	private JTextField txbCod;
	private JTextField txbTelef;
	private JTextField txbEmail;
	private JTable tblClientes;
	private JTextField txbSearch;

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
		setBounds(0, 0, 950, 805);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCod = new JLabel("C\u00F3digo:");
		lblCod.setBounds(69, 82, 67, 23);
		lblCod.setFont(new Font("Arial", Font.PLAIN, 19));
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(69, 136, 56, 23);
		lblNome.setFont(new Font("Arial", Font.PLAIN, 19));
		
		JLabel lblDtNasc = new JLabel("Data de Nascimento:");
		lblDtNasc.setBounds(69, 185, 176, 23);
		lblDtNasc.setFont(new Font("Arial", Font.PLAIN, 19));
		
		JLabel lblTelef = new JLabel("Telefone:");
		lblTelef.setBounds(69, 234, 81, 23);
		lblTelef.setFont(new Font("Arial", Font.PLAIN, 19));
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(69, 276, 56, 23);
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 19));
		
		/*Caixa de texto correspondente ao campo "dt_nasc" da tabela*/
		txbDtNasc = new JTextField();
		txbDtNasc.setBounds(245, 185, 434, 24);
		txbDtNasc.setFont(new Font("Arial", Font.PLAIN, 16));
		txbDtNasc.setColumns(10);
		
		/*Caixa de texto correspondente ao campo "nome" da tabela*/
		txbNome = new JTextField();
		txbNome.setBounds(245, 136, 434, 24);
		txbNome.setFont(new Font("Arial", Font.PLAIN, 16));
		txbNome.setColumns(10);
		
		/*Caixa de texto correspondente ao campo "cod" da tabela*/
		txbCod = new JTextField();
		txbCod.setBounds(245, 82, 434, 24);
		txbCod.setFont(new Font("Arial", Font.PLAIN, 16));
		txbCod.setColumns(10);
		
		/*Caixa de texto correspondente ao campo "telefone" da tabela*/
		txbTelef = new JTextField();
		txbTelef.setBounds(245, 234, 434, 24);
		txbTelef.setFont(new Font("Arial", Font.PLAIN, 16));
		txbTelef.setColumns(10);
		
		/*Caixa de texto correspondente ao campo "email" da tabela*/
		txbEmail = new JTextField();
		txbEmail.setBounds(245, 276, 434, 24);
		txbEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		txbEmail.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 394, 886, 290);
		
		/*Botão que seleciona o primeiro registro da tabela e preenche as JTextField acima com seus respectivos dados*/
		JButton btnFirst = new JButton("");
		btnFirst.setBounds(69, 338, 33, 33);
		btnFirst.setBorder(null);
		btnFirst.setBackground(SystemColor.controlHighlight);
		btnFirst.setIcon(new ImageIcon(frmTela.class.getResource("/img/icons/first.png")));
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con_cliente.resultSet.first();
					mostrar_Dados();
				}catch(SQLException erro) {
					JOptionPane.showMessageDialog(null, "Não foi possível acessar o 1º registro:\n\n" + erro, con_cliente.getTitJOptionPane(), 0);
				}
			}
		});
		
		/*Botão que seleciona o registro anterior, da tabela, ao atual selecionado e preenche as JTextField acima com seus respectivos dados*/
		JButton btnPrevious = new JButton("");
		btnPrevious.setBounds(112, 338, 33, 33);
		btnPrevious.setIcon(new ImageIcon(frmTela.class.getResource("/img/icons/previous.png")));
		btnPrevious.setBorder(null);
		btnPrevious.setBackground(SystemColor.controlHighlight);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con_cliente.resultSet.previous();
					mostrar_Dados();
				}catch(SQLException erro) {
					JOptionPane.showMessageDialog(null, "Não foi possível acessar registro anterior:\n\n" + erro, con_cliente.getTitJOptionPane(), 0);
				}
			}
		});
		
		/*Botão que seleciona o registro seguinte, da tabela, ao atual selecionado e preenche as JTextField acima com seus respectivos dados*/
		JButton btnNext = new JButton("");
		btnNext.setBounds(153, 338, 33, 33);
		btnNext.setIcon(new ImageIcon(frmTela.class.getResource("/img/icons/next.png")));
		btnNext.setBorder(null);
		btnNext.setBackground(SystemColor.controlHighlight);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con_cliente.resultSet.next();
					mostrar_Dados();
				}catch(SQLException erro) {
					JOptionPane.showMessageDialog(null, "Não foi possível acessar o proximo registro:\n\n" + erro, con_cliente.getTitJOptionPane(), 0);
				}
			}
		});
		
		/*Botão que seleciona o último registro da tabela e preenche as JTextField acima com seus respectivos dados*/
		JButton btnLast = new JButton("");
		btnLast.setBounds(196, 338, 33, 33);
		btnLast.setIcon(new ImageIcon(frmTela.class.getResource("/img/icons/last.png")));
		btnLast.setBorder(null);
		btnLast.setBackground(SystemColor.controlHighlight);
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con_cliente.resultSet.last();
					mostrar_Dados();
				}catch(SQLException erro) {
					JOptionPane.showMessageDialog(null, "Não foi possível acessar o último registro:\n\n" + erro, con_cliente.getTitJOptionPane(), 0);
				}
			}
		});
		
		tblClientes = new JTable();
		tblClientes.addKeyListener(new KeyAdapter() {
			@Override
			/*Evento para linha selecionada da tabela pelas Setas do Teclado*/
			public void keyPressed(KeyEvent e) {
				int linha_selecionada = tblClientes.getSelectedRow();
				txbCod.setText(tblClientes.getValueAt(linha_selecionada, 0).toString());
				txbNome.setText(tblClientes.getValueAt(linha_selecionada, 1).toString());
				txbDtNasc.setText(tblClientes.getValueAt(linha_selecionada, 2).toString());
				txbTelef.setText(tblClientes.getValueAt(linha_selecionada, 3).toString());
				txbEmail.setText(tblClientes.getValueAt(linha_selecionada, 4).toString());
			}
		});
		tblClientes.addMouseListener(new MouseAdapter() {
			@Override
			/*Evento para linha selecionada da tabela pelo Clique do Mouse*/
			public void mouseClicked(MouseEvent e) {
				int linha_selecionada = tblClientes.getSelectedRow();
				txbCod.setText(tblClientes.getValueAt(linha_selecionada, 0).toString());
				txbNome.setText(tblClientes.getValueAt(linha_selecionada, 1).toString());
				txbDtNasc.setText(tblClientes.getValueAt(linha_selecionada, 2).toString());
				txbTelef.setText(tblClientes.getValueAt(linha_selecionada, 3).toString());
				txbEmail.setText(tblClientes.getValueAt(linha_selecionada, 4).toString());
			}
		});
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
		
		/*Estabelecendo Conexão*/
		con_cliente = new Conexao();//Iniciando instância
		con_cliente.conecta();//Realizando conexão com a fonte de dados (MySQL)
		con_cliente.executaSQL("select * from tbclientes order by cod");
		preencherTabela();
		posicionarRegistro();
		tblClientes.setAutoCreateRowSorter(true);
		
		/*Botão que limpa os campos para digitação de novo registro*/
		JButton btnNew = new JButton("");
		btnNew.setBounds(519, 338, 33, 33);
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNew.setBackground(SystemColor.controlHighlight);
		
		/*Botão que cadastra novo registro digitado*/
		JButton btnSubmit = new JButton("");
		btnSubmit.setBounds(562, 338, 33, 33);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSubmit.setBackground(SystemColor.controlHighlight);
		
		
		/*Botão que altera registro existente, ou cadastra novo em caso de código (cod) não preenchido*/
		JButton btnChange = new JButton("");
		btnChange.setBounds(603, 338, 33, 33);
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnChange.setBackground(SystemColor.controlHighlight);
		
		/*Botão quer deleta registro existente*/
		JButton btnDelete = new JButton("");
		btnDelete.setBounds(646, 338, 33, 33);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnDelete.setBackground(SystemColor.controlHighlight);
		
		/*Botão que fecha a aplicação*/
		JButton btnExit = new JButton("");
		btnExit.setBounds(882, 338, 33, 33);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnExit.setBackground(SystemColor.controlHighlight);
		
		/*Caixa de texto para fazer a busca de registros pelo campo "nome"*/
		txbSearch = new JTextField();
		txbSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		txbSearch.setBounds(309, 729, 319, 24);
		txbSearch.setText((String) null);
		txbSearch.setFont(new Font("Arial", Font.PLAIN, 14));
		txbSearch.setColumns(10);
		
		JLabel lblSearch = new JLabel("Pesquise pelo nome do cliente:");
		lblSearch.setBounds(309, 700, 203, 23);
		lblSearch.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.setLayout(null);
		contentPane.add(lblCod);
		contentPane.add(txbCod);
		contentPane.add(lblNome);
		contentPane.add(txbNome);
		contentPane.add(lblDtNasc);
		contentPane.add(txbDtNasc);
		contentPane.add(lblTelef);
		contentPane.add(txbTelef);
		contentPane.add(lblEmail);
		contentPane.add(txbEmail);
		contentPane.add(btnFirst);
		contentPane.add(btnPrevious);
		contentPane.add(btnNext);
		contentPane.add(btnLast);
		contentPane.add(btnNew);
		contentPane.add(btnSubmit);
		contentPane.add(btnChange);
		contentPane.add(btnDelete);
		contentPane.add(btnExit);
		contentPane.add(scrollPane);
		contentPane.add(txbSearch);
		contentPane.add(lblSearch);
	}
}
