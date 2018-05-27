


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Font;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.text.MaskFormatter;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.Rectangle;
import java.awt.SplashScreen;
import java.awt.Insets;
import javax.swing.border.EtchedBorder;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Cursor;

@SuppressWarnings({ "serial", "unused" })
public class Main extends JFrame implements ActionListener {

	public void actionPerformed(ActionEvent e) {
	}

	private JTextField CampoId;
	private static JTextField CampoNome;
	private static JTextField CampoEndereco;
	private static JLabel LabelCpf;
	private static JFormattedTextField CampoCpf_1 = new JFormattedTextField();
	private static JTextField CampoEmail;
	private static JFormattedTextField CampoTelefone_1;
	static JFormattedTextField CampoCelular = new JFormattedTextField((setMascara("(##)###-####")));

	private static MaskFormatter setMascara(String mascara) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(mascara);
		} catch (java.text.ParseException ex) {
		}
		return mask;
	}
	// MATRIZ CLIENTES

	static int id = 0;
	static int clientes = 100;
	static int dados = 9;

	static String[][] DB = new String[clientes][dados];

	private static JTable TabelaClientes;

	public void ConsultaClientes() {

		setVisible(true);
		setSize(980, 528);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("CONSULTA DE CLIENTES");
		getContentPane().setLayout(null);

		JLabel ImagemCadastro = new JLabel(new ImageIcon(getClass().getResource("cadastro.png")));
		ImagemCadastro.setBounds(287, 32, 65, 57);
		getContentPane().add(ImagemCadastro);

		final JButton BotaoExcluir = new JButton("EXCLUIR");
		BotaoExcluir.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		BotaoExcluir.setEnabled(true);
		BotaoExcluir.setBounds(33, 420, 121, 40);
		getContentPane().add(BotaoExcluir);
		BotaoExcluir.setIcon(
				new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/collapsed-rtl.gif")));
		BotaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == BotaoExcluir) {

					if (TabelaClientes.getSelectedRow() != -1) {

						final DefaultTableModel valores = (DefaultTableModel) TabelaClientes.getModel();

						((DefaultTableModel) TabelaClientes.getModel()).removeRow(TabelaClientes.getSelectedRow());

						id--;

						JOptionPane.showMessageDialog(null, "O CLIENTE FOI EXCLUIDO COM SUCESSO");

						JPanel TrocarTela = null;
						TrocarTela = new JPanel();
						getContentPane().removeAll();
						getContentPane().add(TrocarTela);
						repaint();
						revalidate();
						ConsultaClientes();

					} else {

						JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada.");

					}

				}
			}
		}

		);

		final JButton BotaoVoltar = new JButton("VOLTAR");
		BotaoVoltar.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		BotaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel TrocarTela = null;
				if (e.getSource() == BotaoVoltar) {
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					revalidate();
					repaint();
					TelaInicial();
				}
			}
		});
		BotaoVoltar.setBounds(299, 419, 111, 40);
		getContentPane().add(BotaoVoltar);

		final JButton BotaoEditar = new JButton("ATUALIZAR");
		BotaoEditar.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		BotaoEditar.setIcon(new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/hardDrive.gif")));
		BotaoEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TabelaClientes.getSelectedRow() != -1) {
					JPanel TrocarTela = null;
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					revalidate();
					validate();
					repaint();
					AtualizarClientes();
					repaint();

				} else {

					JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada.");

				}
			}

		});
		BotaoEditar.setBounds(166, 420, 121, 40);
		getContentPane().add(BotaoEditar);

		JLabel lblConsultarClientes = new JLabel("CONSULTAR CLIENTES");
		lblConsultarClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultarClientes.setFont(new Font("Roboto Condensed", Font.PLAIN, 21));
		lblConsultarClientes.setBounds(382, 50, 210, 30);
		getContentPane().add(lblConsultarClientes);

		JScrollPane ScrolTabelaClientes = new JScrollPane();
		ScrolTabelaClientes.setBounds(33, 115, 897, 288);
		getContentPane().add(ScrolTabelaClientes);

		TabelaClientes = new JTable();
		TabelaClientes.setShowGrid(false);
		TabelaClientes.setShowHorizontalLines(false);
		ScrolTabelaClientes.setViewportView(TabelaClientes);
		TabelaClientes.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "NOME", "CPF", "ENDERECO",
				"EMAIL", "TEL. RESIDENCIAL", "TEL. CELULAR", "TEL. COMERCIAL", "TEL. FAX" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});

		TabelaClientes.getTableHeader().setReorderingAllowed(false);
		TabelaClientes.getColumnModel().getColumn(0).setPreferredWidth(28);
		TabelaClientes.getColumnModel().getColumn(1).setPreferredWidth(207);
		TabelaClientes.getColumnModel().getColumn(2).setPreferredWidth(107);
		TabelaClientes.getColumnModel().getColumn(3).setPreferredWidth(134);
		TabelaClientes.getColumnModel().getColumn(4).setPreferredWidth(123);
		TabelaClientes.getColumnModel().getColumn(5).setPreferredWidth(116);
		TabelaClientes.getColumnModel().getColumn(6).setPreferredWidth(116);
		TabelaClientes.getColumnModel().getColumn(7).setPreferredWidth(116);
		TabelaClientes.getColumnModel().getColumn(8).setPreferredWidth(116);

		TabelaClientes.getColumnModel().getColumn(0).setResizable(false);
		TabelaClientes.getColumnModel().getColumn(1).setResizable(false);
		TabelaClientes.getColumnModel().getColumn(2).setResizable(false);
		TabelaClientes.getColumnModel().getColumn(3).setResizable(false);
		TabelaClientes.getColumnModel().getColumn(4).setResizable(false);
		TabelaClientes.getColumnModel().getColumn(5).setResizable(false);
		TabelaClientes.getColumnModel().getColumn(6).setResizable(false);
		TabelaClientes.getColumnModel().getColumn(7).setResizable(false);
		TabelaClientes.getColumnModel().getColumn(8).setResizable(false);

		for (int i = 0; i < id; i++) {
			final DefaultTableModel valores = (DefaultTableModel) TabelaClientes.getModel();
			valores.addRow(new Object[] { i + 1, DB[i][0], DB[i][1], DB[i][2], DB[i][3], DB[i][4], DB[i][5], DB[i][6],
					DB[i][7] });
		}

	}

	public void CadastroCliente() {

		JLabel LabelId = new JLabel("ID");
		LabelId.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		LabelId.setVerticalAlignment(SwingConstants.BOTTOM);
		LabelId.setBounds(176, 165, 23, 17);
		getContentPane().add(LabelId);

		LabelCpf = new JLabel("CPF*");
		LabelCpf.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		LabelCpf.setBounds(629, 166, 40, 14);
		getContentPane().add(LabelCpf);

		JLabel LabelEndereco = new JLabel("ENDERE\u00C7O*");
		LabelEndereco.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		LabelEndereco.setBounds(176, 218, 79, 14);
		getContentPane().add(LabelEndereco);

		final JFormattedTextField CampoTelefone_1 = new JFormattedTextField((setMascara("(##)####-####")));
		CampoTelefone_1.setToolTipText("");
		CampoTelefone_1.setBounds(300, 327, 114, 26);
		getContentPane().add(CampoTelefone_1);
		CampoTelefone_1.setColumns(10);

		CampoEmail = new JTextField();
		CampoEmail.setBounds(607, 212, 192, 26);
		getContentPane().add(CampoEmail);
		CampoEmail.setColumns(10);

		CampoId = new JTextField();
		CampoId.setForeground(Color.RED);
		CampoId.setHorizontalAlignment(SwingConstants.CENTER);
		CampoId.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		CampoId.setEditable(false);
		CampoId.setBounds(new Rectangle(3, 0, 0, 0));
		CampoId.setBounds(209, 159, 29, 26);
		getContentPane().add(CampoId);
		CampoId.setColumns(10);
		CampoId.setText(String.valueOf(id + 1));

		JLabel LabelEmail = new JLabel("EMAIL*");
		LabelEmail.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		LabelEmail.setBounds(544, 218, 57, 14);
		getContentPane().add(LabelEmail);

		CampoEndereco = new JTextField();
		CampoEndereco.setBounds(258, 212, 275, 26);
		getContentPane().add(CampoEndereco);
		CampoEndereco.setColumns(10);

		JLabel LabelNome = new JLabel("NOME*");
		LabelNome.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		LabelNome.setBounds(256, 166, 56, 14);
		getContentPane().add(LabelNome);

		CampoNome = new JTextField();
		CampoNome.setBounds(310, 159, 301, 26);
		getContentPane().add(CampoNome);
		CampoNome.setColumns(10);

		JLabel LabelTelefoneFixo = new JLabel("TEL. RESIDENCIAL*");
		LabelTelefoneFixo.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		LabelTelefoneFixo.setHorizontalAlignment(SwingConstants.LEFT);
		LabelTelefoneFixo.setBounds(176, 333, 121, 14);
		getContentPane().add(LabelTelefoneFixo);

		final JFormattedTextField CampoCpf_1 = new JFormattedTextField((setMascara("###.###.###-##")));
		CampoCpf_1.setToolTipText("");
		CampoCpf_1.setBounds(675, 160, 124, 26);
		getContentPane().add(CampoCpf_1);
		CampoCpf_1.setColumns(10);

		JLabel ImagemCadastro = new JLabel(new ImageIcon(getClass().getResource("cadastro.png")));
		ImagemCadastro.setBounds(300, 18, 65, 57);
		getContentPane().add(ImagemCadastro);

		JLabel LabelCadastroDeCliente = new JLabel("CADASTRO DE CLIENTE");
		LabelCadastroDeCliente.setHorizontalAlignment(SwingConstants.CENTER);
		LabelCadastroDeCliente.setFont(new Font("Roboto Condensed", Font.PLAIN, 21));
		LabelCadastroDeCliente.setBounds(377, 38, 224, 31);
		getContentPane().add(LabelCadastroDeCliente);

		final JButton BotaoSalvar = new JButton("SALVAR");
		BotaoSalvar.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		BotaoSalvar.setIcon(new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		BotaoSalvar.setBounds(43, 430, 114, 40);
		getContentPane().add(BotaoSalvar);

		setTitle("CADASTRE UM CLIENTE");
		getContentPane().setLayout(null);

		final JButton BotaoVoltar2 = new JButton("VOLTAR");
		BotaoVoltar2.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		BotaoVoltar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel TrocarTela = null;
				if (e.getSource() == BotaoVoltar2) {
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					revalidate();
					repaint();
					TelaInicial();
				}
			}
		});
		BotaoVoltar2.setBounds(169, 430, 106, 40);
		getContentPane().add(BotaoVoltar2);

		final JFormattedTextField CampoTelefone_2 = new JFormattedTextField(((setMascara("(##)####-####"))));
		CampoTelefone_2.setText("(  )    -    ");
		CampoTelefone_2.setToolTipText("");
		CampoTelefone_2.setColumns(10);
		CampoTelefone_2.setBounds(539, 327, 114, 26);
		getContentPane().add(CampoTelefone_2);

		JLabel LabelTelefoneCelular = new JLabel("TEL. CELULAR");
		LabelTelefoneCelular.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		LabelTelefoneCelular.setHorizontalAlignment(SwingConstants.CENTER);
		LabelTelefoneCelular.setBounds(427, 333, 106, 14);
		getContentPane().add(LabelTelefoneCelular);

		JLabel LabelTelefoneComercial = new JLabel("TEL. COMERCIAL");
		LabelTelefoneComercial.setHorizontalAlignment(SwingConstants.LEFT);
		LabelTelefoneComercial.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		LabelTelefoneComercial.setBounds(176, 375, 121, 14);
		getContentPane().add(LabelTelefoneComercial);

		final JFormattedTextField CampoTelefone_3 = new JFormattedTextField((((setMascara("(##)####-####")))));
		CampoTelefone_3.setText("(  )    -    ");
		CampoTelefone_3.setToolTipText("");
		CampoTelefone_3.setColumns(10);
		CampoTelefone_3.setBounds(300, 369, 114, 26);
		getContentPane().add(CampoTelefone_3);

		JLabel lblDadosPessoais = new JLabel("DADOS PESSOAIS");
		lblDadosPessoais.setHorizontalAlignment(SwingConstants.LEFT);
		lblDadosPessoais.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		lblDadosPessoais.setBounds(176, 111, 136, 16);
		getContentPane().add(lblDadosPessoais);

		JLabel LabelTelefoneFax = new JLabel("TEL. FAX");
		LabelTelefoneFax.setHorizontalAlignment(SwingConstants.LEFT);
		LabelTelefoneFax.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		LabelTelefoneFax.setBounds(437, 375, 85, 14);
		getContentPane().add(LabelTelefoneFax);

		final JFormattedTextField CampoTelefone_4 = new JFormattedTextField((((setMascara("(##)####-####")))));
		CampoTelefone_4.setToolTipText("");
		CampoTelefone_4.setText("(  )    -    ");
		CampoTelefone_4.setColumns(10);
		CampoTelefone_4.setBounds(537, 369, 114, 26);
		getContentPane().add(CampoTelefone_4);

		JLabel lblDadosObrigatrios = new JLabel("* Dados Obrigat\u00F3rios");
		lblDadosObrigatrios.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDadosObrigatrios.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		lblDadosObrigatrios.setBounds(642, 111, 136, 16);
		getContentPane().add(lblDadosObrigatrios);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		separator_2.setBounds(43, 92, 889, 170);
		getContentPane().add(separator_2);

		JSeparator separator = new JSeparator();
		separator.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		separator.setBounds(43, 273, 889, 145);
		getContentPane().add(separator);

		JLabel lblCadastroDeTelefones = new JLabel("TELEFONE");
		lblCadastroDeTelefones.setHorizontalAlignment(SwingConstants.LEFT);
		lblCadastroDeTelefones.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		lblCadastroDeTelefones.setBounds(176, 285, 155, 16);
		getContentPane().add(lblCadastroDeTelefones);

		setSize(980, 528);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);

		BotaoSalvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == BotaoSalvar) {

					if (CampoNome != null && CampoNome.getText().equals("") == true) {
						JOptionPane.showMessageDialog(null, "Digite o nome");
					} else if (CampoCpf_1 != null && CampoCpf_1.getText().equals("   .   .   -  ") == true) {
						JOptionPane.showMessageDialog(null, "Digite o CPF");
					} else if (CampoEndereco != null && CampoEndereco.getText().equals("") == true) {
						JOptionPane.showMessageDialog(null, "Digite o endereço");
					} else if (CampoEmail != null && CampoEmail.getText().equals("") == true) {
						JOptionPane.showMessageDialog(null, "Digite o email");
					} else if (CampoTelefone_1 != null && CampoTelefone_1.getText().equals("(  )    -    ") == true) {
						JOptionPane.showMessageDialog(null, "Digite o telefone residencial");
					} else {

						DB[id][0] = CampoNome.getText();
						DB[id][1] = CampoCpf_1.getText();
						DB[id][2] = CampoEndereco.getText();
						DB[id][3] = CampoEmail.getText();
						DB[id][4] = CampoTelefone_1.getText();
						DB[id][5] = CampoTelefone_2.getText();
						DB[id][6] = CampoTelefone_3.getText();
						DB[id][7] = CampoTelefone_4.getText();

						id++;

						JOptionPane.showMessageDialog(null, "CLIENTE CADASTRADO COM SUCESSO");
						JPanel TrocarTela = null;
						TrocarTela = new JPanel();
						getContentPane().removeAll();
						getContentPane().add(TrocarTela);
						repaint();
						revalidate();
						CadastroCliente();

					}
				}
			}

		});

	}

	static int ContarLinhas() {

		int Linha = TabelaClientes.getSelectedRow();
		int MatrizSalvarTelefone = (int) TabelaClientes.getValueAt(Linha, 0);

		return MatrizSalvarTelefone;
	}

	public void AtualizarClientes() {

		JLabel LabelId = new JLabel("ID");
		LabelId.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		LabelId.setVerticalAlignment(SwingConstants.BOTTOM);
		LabelId.setBounds(176, 165, 23, 17);
		getContentPane().add(LabelId);

		LabelCpf = new JLabel("CPF*");
		LabelCpf.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		LabelCpf.setBounds(629, 166, 40, 14);
		getContentPane().add(LabelCpf);

		JLabel LabelEndereco = new JLabel("ENDERE\u00C7O*");
		LabelEndereco.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		LabelEndereco.setBounds(176, 218, 79, 14);
		getContentPane().add(LabelEndereco);

		final JFormattedTextField CampoTelefone_1 = new JFormattedTextField((setMascara("(##)####-####")));
		CampoTelefone_1.setToolTipText("");
		CampoTelefone_1.setBounds(300, 327, 114, 26);
		getContentPane().add(CampoTelefone_1);
		CampoTelefone_1.setColumns(10);
		CampoTelefone_1.setText(DB[Integer.parseInt(CampoId.getText()) - 1][4]);

		int LinhaTabela = TabelaClientes.getSelectedRow();
		final int EditarMatriz = (int) TabelaClientes.getModel().getValueAt(LinhaTabela, 0);

		CampoEmail = new JTextField();
		CampoEmail.setBounds(607, 212, 192, 26);
		getContentPane().add(CampoEmail);
		CampoEmail.setColumns(10);
		CampoEmail.setText(DB[Integer.parseInt(CampoId.getText()) - 1][3]);

		CampoId = new JTextField();
		CampoId.setForeground(Color.RED);
		CampoId.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		CampoId.setHorizontalAlignment(SwingConstants.CENTER);
		CampoId.setEditable(false);
		CampoId.setBounds(new Rectangle(3, 0, 0, 0));
		CampoId.setBounds(209, 159, 29, 26);
		getContentPane().add(CampoId);
		CampoId.setColumns(10);
		CampoId.setText("1");

		JLabel LabelEmail = new JLabel("EMAIL*");
		LabelEmail.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		LabelEmail.setBounds(544, 218, 57, 14);
		getContentPane().add(LabelEmail);

		CampoEndereco = new JTextField();
		CampoEndereco.setBounds(258, 212, 275, 26);
		getContentPane().add(CampoEndereco);
		CampoEndereco.setColumns(10);
		CampoEndereco.setText(DB[Integer.parseInt(CampoId.getText()) - 1][3]);

		JLabel LabelNome = new JLabel("NOME*");
		LabelNome.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		LabelNome.setBounds(256, 166, 56, 14);
		getContentPane().add(LabelNome);

		CampoNome = new JTextField();
		CampoNome.setBounds(310, 159, 301, 26);
		getContentPane().add(CampoNome);
		CampoNome.setColumns(10);
		CampoNome.setText(DB[Integer.parseInt(CampoId.getText()) - 1][0]);

		final JFormattedTextField CampoCpf_1 = new JFormattedTextField((setMascara("###.###.###-##")));
		CampoCpf_1.setToolTipText("");
		CampoCpf_1.setBounds(675, 160, 124, 26);
		getContentPane().add(CampoCpf_1);
		CampoCpf_1.setColumns(10);
		CampoCpf_1.setText(DB[Integer.parseInt(CampoId.getText()) - 1][1]);

		JLabel ImagemCadastro = new JLabel(new ImageIcon(getClass().getResource("cadastro.png")));
		ImagemCadastro.setBounds(266, 30, 65, 57);
		getContentPane().add(ImagemCadastro);

		JLabel LabelCadastroDeCliente = new JLabel("ATUALIZAR DADOS CADASTRAIS");
		LabelCadastroDeCliente.setHorizontalAlignment(SwingConstants.CENTER);
		LabelCadastroDeCliente.setFont(new Font("Roboto Condensed", Font.PLAIN, 21));
		LabelCadastroDeCliente.setBounds(334, 49, 306, 31);
		getContentPane().add(LabelCadastroDeCliente);

		final JButton BotaoSalvar = new JButton("SALVAR");
		BotaoSalvar.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		BotaoSalvar.setIcon(
				new ImageIcon(Main.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		BotaoSalvar.setBounds(43, 433, 114, 33);
		getContentPane().add(BotaoSalvar);

		setTitle("CADASTRE UM CLIENTE");
		getContentPane().setLayout(null);

		final JButton BotaoVoltar2 = new JButton("VOLTAR");
		BotaoVoltar2.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		BotaoVoltar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel TrocarTela = null;
				if (e.getSource() == BotaoVoltar2) {
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					revalidate();
					repaint();
					ConsultaClientes();
				}
			}
		});
		BotaoVoltar2.setBounds(166, 433, 99, 33);
		getContentPane().add(BotaoVoltar2);

		setSize(980, 528);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);

		final JFormattedTextField CampoTelefone_2 = new JFormattedTextField(((setMascara("(##)####-####"))));
		CampoTelefone_2.setText(DB[Integer.parseInt(CampoId.getText()) - 1][5]);
		CampoTelefone_2.setToolTipText("");
		CampoTelefone_2.setColumns(10);
		CampoTelefone_2.setBounds(539, 327, 114, 26);
		getContentPane().add(CampoTelefone_2);

		JLabel LabelTelefoneCelular = new JLabel("TEL. CELULAR");
		LabelTelefoneCelular.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		LabelTelefoneCelular.setHorizontalAlignment(SwingConstants.CENTER);
		LabelTelefoneCelular.setBounds(427, 333, 106, 14);
		getContentPane().add(LabelTelefoneCelular);

		JLabel LabelTelefoneComercial = new JLabel("TEL. COMERCIAL");
		LabelTelefoneComercial.setHorizontalAlignment(SwingConstants.LEFT);
		LabelTelefoneComercial.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		LabelTelefoneComercial.setBounds(176, 375, 121, 14);
		getContentPane().add(LabelTelefoneComercial);

		final JFormattedTextField CampoTelefone_3 = new JFormattedTextField((((setMascara("(##)####-####")))));
		CampoTelefone_3.setText(DB[Integer.parseInt(CampoId.getText()) - 1][6]);
		CampoTelefone_3.setToolTipText("");
		CampoTelefone_3.setColumns(10);
		CampoTelefone_3.setBounds(300, 369, 114, 26);
		getContentPane().add(CampoTelefone_3);

		JLabel lblDadosPessoais = new JLabel("DADOS PESSOAIS");
		lblDadosPessoais.setHorizontalAlignment(SwingConstants.LEFT);
		lblDadosPessoais.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		lblDadosPessoais.setBounds(176, 111, 136, 16);
		getContentPane().add(lblDadosPessoais);

		JLabel LabelTelefoneFax = new JLabel("TEL. FAX");
		LabelTelefoneFax.setHorizontalAlignment(SwingConstants.LEFT);
		LabelTelefoneFax.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		LabelTelefoneFax.setBounds(437, 375, 85, 14);
		getContentPane().add(LabelTelefoneFax);

		final JFormattedTextField CampoTelefone_4 = new JFormattedTextField((((setMascara("(##)####-####")))));
		CampoTelefone_4.setText(DB[Integer.parseInt(CampoId.getText()) - 1][7]);
		CampoTelefone_4.setColumns(10);
		CampoTelefone_4.setBounds(537, 369, 114, 26);
		getContentPane().add(CampoTelefone_4);

		JLabel lblDadosObrigatrios = new JLabel("* Dados Obrigat\u00F3rios");
		lblDadosObrigatrios.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDadosObrigatrios.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		lblDadosObrigatrios.setBounds(642, 111, 136, 16);
		getContentPane().add(lblDadosObrigatrios);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		separator_2.setBounds(43, 92, 889, 170);
		getContentPane().add(separator_2);

		JSeparator separator = new JSeparator();
		separator.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		separator.setBounds(43, 273, 889, 145);
		getContentPane().add(separator);

		JLabel lblCadastroDeTelefones = new JLabel("TELEFONE");
		lblCadastroDeTelefones.setHorizontalAlignment(SwingConstants.LEFT);
		lblCadastroDeTelefones.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		lblCadastroDeTelefones.setBounds(176, 285, 155, 16);
		getContentPane().add(lblCadastroDeTelefones);

		JLabel LabelTelefoneResidencial = new JLabel("TEL. RESIDENCIAL*");
		LabelTelefoneResidencial.setHorizontalAlignment(SwingConstants.LEFT);
		LabelTelefoneResidencial.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		LabelTelefoneResidencial.setBounds(176, 332, 121, 14);
		getContentPane().add(LabelTelefoneResidencial);

		BotaoSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == BotaoSalvar) {

				}
				if (CampoNome != null && CampoNome.getText().equals("") == true) {
					JOptionPane.showMessageDialog(null, "Digite o nome");
				} else if (CampoCpf_1 != null && CampoCpf_1.getText().equals(null) == true) {
					JOptionPane.showMessageDialog(null, "Digite o CPF");
				} else if (CampoEndereco != null && CampoEndereco.getText().equals("") == true) {
					JOptionPane.showMessageDialog(null, "Digite o endereço");
				} else if (CampoEmail != null && CampoEmail.getText().equals("") == true) {
					JOptionPane.showMessageDialog(null, "Digite o email");
				} else if (CampoTelefone_1 != null && CampoTelefone_1.getText().equals(null) == true) {
					JOptionPane.showMessageDialog(null, "Digite o telefone");
				} else {

					DB[EditarMatriz - 1][0] = CampoNome.getText();
					DB[EditarMatriz - 1][1] = CampoCpf_1.getText();
					DB[EditarMatriz - 1][2] = CampoEndereco.getText();
					DB[EditarMatriz - 1][3] = CampoEmail.getText();
					DB[EditarMatriz - 1][4] = CampoTelefone_1.getText();
					DB[EditarMatriz - 1][5] = CampoTelefone_2.getText();
					DB[EditarMatriz - 1][6] = CampoTelefone_3.getText();
					DB[EditarMatriz - 1][7] = CampoTelefone_4.getText();

					JOptionPane.showMessageDialog(null, "CLIENTE ATUALIZADO COM SUCESSO.");
					JPanel TrocarTela = null;
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					revalidate();
					repaint();
					ConsultaClientes();

				}

			}

		});

	}

	public void Informacao() {

		setTitle("SOFTWARE - CADASTRO DE CLIENTES - v.1.0 - JAVA SWING");
		setSize(980, 528);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JTextArea txtrDisciplinaAlgoritmoE = new JTextArea();
		txtrDisciplinaAlgoritmoE.setEnabled(false);
		txtrDisciplinaAlgoritmoE.setEditable(false);
		txtrDisciplinaAlgoritmoE.setText(
				"\t\r\n\r\n\tEXERCICIO 51 - CADASTRO DE CLIENTES\r\n\r\n\tDISCIPLINA: ALGORITMO E LOGICA DE PROGRAMA\u00C7\u00C3O II\r\n\r\n\tCURSO: CI\u00CANCIA DA COMPUTA\u00C7\u00C3O\r\n\r\n\tIES: FACULDADE ALVORADA DE EDUCA\u00C7\u00C3O E TECNOLOGIA DE MARING\u00C1\r\n\r\n\tPROF.\u00BA ALTIERES DE MATOS\r\n\r\n\tAPLICA\u00C7\u00C3O DESENVOLVIDA EM JAVA SE, NO SOFTWARE ECLIPSE. 2.0 MARS\r\n\t ");
		txtrDisciplinaAlgoritmoE.setBackground(SystemColor.text);
		txtrDisciplinaAlgoritmoE.setRows(10);
		txtrDisciplinaAlgoritmoE.setBounds(111, 126, 769, 255);
		getContentPane().add(txtrDisciplinaAlgoritmoE);

		JLabel lblCadastroDeClientes = new JLabel("CADASTRO DE CLIENTES  - INFORMA\u00C7\u00C3O");
		lblCadastroDeClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeClientes.setFont(new Font("Roboto Condensed", Font.PLAIN, 20));
		lblCadastroDeClientes.setBounds(308, 43, 358, 85);
		getContentPane().add(lblCadastroDeClientes);

		JButton btnVoltar3 = new JButton("VOLTAR");
		btnVoltar3.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		btnVoltar3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JPanel TrocarTela = null;
				TrocarTela = new JPanel();
				getContentPane().removeAll();
				getContentPane().add(TrocarTela);
				validate();
				TelaInicial();

			}
		});
		btnVoltar3.setBounds(780, 405, 100, 40);
		getContentPane().add(btnVoltar3);
		setResizable(false);
		setVisible(true);

	}

	public void Loading() {

		JLabel LOGO = new JLabel(new ImageIcon(getClass().getResource("cs.png")));
		LOGO.setBounds(423, 130, 128, 128);
		getContentPane().add(LOGO);

		final JLabel car = new JLabel("CASA");
		setTitle("SOFTWARE - CADASTRO DE CLIENTES - v.1.0 - JAVA SWING");
		setSize(980, 528);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);
		setVisible(true);

		final JLabel lblCarregandoAguarde = new JLabel("CARREGANDO");
		lblCarregandoAguarde.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarregandoAguarde.setFont(new Font("Roboto Condensed", Font.PLAIN, 21));
		lblCarregandoAguarde.setBounds(325, 345, 323, 40);
		getContentPane().add(lblCarregandoAguarde);

		final JProgressBar progressBar = new JProgressBar();
		progressBar.setBorderPainted(false);
		progressBar.setFont(new Font("Roboto Condensed", Font.BOLD, 16));
		progressBar.setStringPainted(true);
		progressBar.setBounds(325, 284, 323, 35);
		getContentPane().add(progressBar);

		new Thread() {

			public void run() {

				for (int i = 0; i < 101; i++) {
					try {
						sleep(60);
						progressBar.setValue(i);

						if (progressBar.getValue() <= 40) {
							car.setText("CARREGANDO DADOS");

						} else if (progressBar.getValue() <= 70) {
							lblCarregandoAguarde.setText("CARREGANDO TABELAS");
						} else {
							lblCarregandoAguarde.setText("CARREGANDO SISTEMA");
						}

					} catch (InterruptedException ex) {
						Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);

					}

				}

				JPanel TrocarTela = null;
				TrocarTela = new JPanel();
				getContentPane().removeAll();
				getContentPane().add(TrocarTela);
				validate();

				TelaInicial();
				repaint();
			}

		}.start();

	}

	public void TelaInicial() {

		setTitle("SOFTWARE - CADASTRO DE CLIENTES - v.1.0 - JAVA SWING");
		setSize(980, 528);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);

		JPanel PainelCadastro = new JPanel();
		PainelCadastro.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		PainelCadastro.setBounds(38, 34, 892, 425);
		getContentPane().add(PainelCadastro);
		PainelCadastro.setLayout(null);

		JLabel LOGO = new JLabel(new ImageIcon(getClass().getResource("cs.png")));
		LOGO.setBounds(382, 68, 128, 128);
		PainelCadastro.add(LOGO);

		JLabel LabelCadastroDeClientes = new JLabel("CADASTRO DE CLIENTES");
		LabelCadastroDeClientes.setHorizontalAlignment(SwingConstants.CENTER);
		LabelCadastroDeClientes.setBounds(320, 225, 251, 26);
		PainelCadastro.add(LabelCadastroDeClientes);
		LabelCadastroDeClientes.setFont(new Font("Roboto Condensed", Font.PLAIN, 22));

		final JButton BotaoCadastrar = new JButton("CADASTRAR");
		BotaoCadastrar.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		BotaoCadastrar.setBounds(152, 313, 130, 40);
		PainelCadastro.add(BotaoCadastrar);

		final JButton BotaoSair = new JButton("SAIR");
		BotaoSair.setIcon(new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		BotaoSair.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		BotaoSair.setBounds(614, 313, 130, 40);
		PainelCadastro.add(BotaoSair);
		BotaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == BotaoSair) {
					System.exit(0);
				}
			}
		});

		final JButton BotaoConsultar = new JButton("CONSULTAR");
		BotaoConsultar.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		BotaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel TrocarTela = null;
				if (e.getSource() == BotaoConsultar) {
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					revalidate();
					repaint();
					ConsultaClientes();
				}
			}
		});
		BotaoConsultar.setBounds(305, 313, 130, 40);
		PainelCadastro.add(BotaoConsultar);

		JButton btnINFO = new JButton("INFORMA\u00C7\u00D5ES");
		btnINFO.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		btnINFO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel TrocarTela = null;
				TrocarTela = new JPanel();
				getContentPane().removeAll();
				getContentPane().add(TrocarTela);
				revalidate();
				repaint();
				Informacao();

			}
		});
		btnINFO.setBounds(458, 313, 130, 40);
		PainelCadastro.add(btnINFO);

		JMenuBar BarraSuperior = new JMenuBar();
		BarraSuperior.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		BarraSuperior.setBounds(650, 0, 70, 35);
		getContentPane().add(BarraSuperior);

		JMenuItem ItemHelp = new JMenuItem("AJUDA");
		ItemHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						" " + "Cadastro de Clientes | v.1.2" + "\n" + "\n * Para cadastrar um cliente"
								+ "\n Vá até a opção CADASTRAR, e preencha todos os campos. Clique no botão SALVAR"
								+ "\n Após o cadastro, os campos ficarão livre para um proximo cadastro."
								+ "\n * Para Consultar um cliente" + ""
								+ "\n Vá ao Menu Inicial, Clique na opção CONSULTAR, nele abrirá uma tabela com todos "
								+ "dados inseridos." + "\n * Para Excluir um cliente"
								+ "\n Clique no cliente desejado na tabela, e clique no botão EXCLUIR"
								+ "\n *  Para  Atualizar um cliente"
								+ "\n Clique no cliente desejado na tabela, e clique no botão ATUALIZAR");
			}
		});
		BarraSuperior.add(ItemHelp);

		Date data = new Date();
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formatar.format(data);

		Date hora = new Date();
		SimpleDateFormat formatarHora = new SimpleDateFormat("hh:mm");
		String horaFormatada = formatarHora.format(hora);

		JLabel DataAqui = new JLabel(dataFormatada);
		DataAqui.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		DataAqui.setBounds(90, 12, 74, 16);
		getContentPane().add(DataAqui);
		DataAqui.setBounds(90, 12, 111, 16);

		JLabel HoraAqui = new JLabel(horaFormatada);
		HoraAqui.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		HoraAqui.setBounds(174, 12, 55, 16);
		getContentPane().add(HoraAqui);

		BotaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel TrocarTela = null;
				if (e.getSource() == BotaoCadastrar) {
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					revalidate();
					repaint();
					CadastroCliente();
				}
			}
		});
		setVisible(true);
	}

	public Main() {

		// Informacao();
		 Loading();
		// TelaInicial();
		// ConsultaClientes();
		// CadastroCliente();
		// AtualizarClientes();
		// CadastrarTelefone();

	}

	public static void main(String[] args) {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {

		}

		new Main();
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}