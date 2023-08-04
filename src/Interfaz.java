import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Interfaz extends JFrame {   
	private JTextArea processArea;
	public final JButton checkButton;
	public final JButton selectButton;
	public final JPanel contentPanel;
	public JPanel subPanel;
	public final JLabel dirLabel;
	public final JLabel processLabel;
	public JScrollPane pane;    
	public final JButton selectButtonArchivo;
	private static ArrayList<String> bd;
	private static String file;

	public Interfaz() {
		this.processLabel = new JLabel("Estado:");
		this.dirLabel = new JLabel("Detector de Plagios ");
		this.selectButton = new JButton("Subir archivos a la Base de Ddatos");
		this.processArea = new JTextArea();
		this.checkButton = new JButton("Revisar");
		this.subPanel = new JPanel();
		this.contentPanel = new JPanel();
		this.selectButtonArchivo = new JButton("Seleccionar archivo");
		bd= new ArrayList<String>();
		file ="";
	}

	public void Display() {
		setBounds(50, 50, 600, 200);
		setMinimumSize(new Dimension(500, 300));
		setMaximumSize(new Dimension(999, 200));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Plagiarism Checker");

		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
		contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setContentPane(contentPanel);

		dirLabel.setPreferredSize(new Dimension(100, 20));
		processLabel.setPreferredSize(new Dimension(150, 20));
		processArea.setPreferredSize(new Dimension(0, 1000));
		processArea.setEditable(false);
		pane = new JScrollPane (processArea);
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.LINE_AXIS));
		subPanel.add(dirLabel);
		contentPanel.add(subPanel);

		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.LINE_AXIS));
		subPanel.add(Box.createRigidArea(new Dimension(5,0)));
		subPanel.add(selectButton);
		subPanel.add(selectButtonArchivo);
		contentPanel.add(subPanel);

		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.LINE_AXIS));
		subPanel.add(processLabel);
		contentPanel.add(subPanel);

		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.LINE_AXIS));
		subPanel.add(pane);
		contentPanel.add(subPanel);

		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.LINE_AXIS));
		subPanel.add(Box.createRigidArea(new Dimension(5,0)));
		subPanel.add(checkButton);
		contentPanel.add(subPanel);      

		SubidaBd sbd = new SubidaBd();
		selectButton.addActionListener(sbd);

		SubidaArchivo sa = new SubidaArchivo();
		selectButtonArchivo.addActionListener(sa);

		Ejecutar ej= new Ejecutar();
		checkButton.addActionListener(ej);

		setVisible(true);
	}

	private static String getDirectory() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("~"));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().getAbsolutePath();
		}
		return null;
	}

	static

	class SubidaBd implements ActionListener { //Sube a la base de datos

		@Override
		public void actionPerformed(ActionEvent e) {
			String path = getDirectory();
			if(bd.contains(path)){
				JOptionPane.showMessageDialog(null, "Ya se ingreso ese texto");
			} 
			else {
				JOptionPane.showMessageDialog(null, "Subido " + path);
				bd.add(path);
			}
		}
	}

	class SubidaArchivo implements ActionListener { //Sube el archivo a comparar

		@Override
		public void actionPerformed(ActionEvent e) {
			String path = getDirectory();
			if (path.isEmpty() && bd.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor selecciona directorio para preocesar");
			}else {
				JOptionPane.showMessageDialog(null, "Subido " + path);
				file= path;
			}
		}
	}

	class Ejecutar implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {      

			if (file.equals("")) {
				JOptionPane.showMessageDialog(null, "Seleccione el archivo a comparar");
				return ;
			}

			if (bd.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Suba archivos a la base de datos");
				return ;
			}

			String respuesta= "";
			long startTime = System.currentTimeMillis();
			System.out.println("#Procesando");
			respuesta= respuesta+"#Procesando"+"\n" ;

			PlagiarismChecker d = new PlagiarismChecker();            	

			PlagiarismChecker p= new PlagiarismChecker();
			String[] paths= new String[bd.size()];
			for(int i= 0; i< bd.size(); i++){
				paths[i]= bd.get(i);
			}
			System.out.println(d.loadFiles(paths) + " en Load");
			ResultChecker rpta= p.verifyPlagiarism(file);
			respuesta+= rpta.imprimir();

			System.out.println("100% \n#Procesamiento: Listo!");
			respuesta= respuesta+"\n#Procesamiento: Listo!"+"\n" ;

			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("Tiempo de procesamiento en milisegundos: "+elapsedTime);
			respuesta= respuesta+"Tiempo de procesamiento en milisegundos: "+elapsedTime+"\n";
			System.out.println(respuesta);
			processArea.setText(respuesta);
		}
	}
}