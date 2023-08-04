import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Esta clase tiene 3 atributos y contiene una lista
 * de metodos para ayudarnos a leer y verificar cada
 * documento que se carguen.
 */

public class Document{
	//Atributos
	private String fileName;
	private Phrase phrase;
	final static int max_number_word=10;

	//Constructores
	public Document() {

	}

	public Document(String fileName) {
		this.fileName = fileName;
	}

	public Document(String fileName, int frequency) {
		this.fileName = fileName;
	}

	//Getters y setters
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Phrase getPhrase() {
		return phrase;
	}

	public void setPhrase(Phrase phrase) {
		this.phrase = phrase;
	}

	/**
	 * Este metodo nos ayuda a dividir un archivo en frases e
	 * insertarlos en el arbol AVL
	 */
	public AVLTree<String> createAVL(){
		Scanner input;
		try{
			input = new Scanner(new FileReader(this.getFileName()));
		}
		catch(FileNotFoundException e){
			System.out.println("Error en Document/maching_count");
			return null;
		}

		Phrase p = new Phrase();
		AVLTree<String> tree = new AVLTree<String>();
		System.out.println("Creando AVL de " + fileName);
		while (input.hasNext()) {
			String word = input.next().toLowerCase();
			System.out.print(word + " ");
			p.addword(word);
			//Inserta cada 10 palabras en el nuevo nodo
			if (p.getNumbersWord() == max_number_word) {
				tree.setRoot(tree.insert(tree.getRoot(), new String(p.getData())));
			}
		}
		input.close();
		System.out.println("  +++ Terminado +++");
		return tree;
	}


	// Este metodo lee un arbol AVL y verifica si hay coincidencias
	public boolean match(AVLTree<String> tree){
		Scanner input;
		try{
			input = new Scanner(new FileReader(this.getFileName()));
		}
		catch(FileNotFoundException e){
			System.out.println("Error en Document/maching_count");
			return false;
		}

		Phrase p = new Phrase();
		while (input.hasNext()) {
			String word = input.next().toLowerCase();
			p.addword(word);
			// Compruebe si el arbol avl contiene la frase de 10 palabras
			if (p.getNumbersWord() == max_number_word) {
				if (tree.find(tree, p.getData().toString().trim()) != null) {
					input.close();
					return true;
				}
			}
		}
		input.close();
		return false;
	}
}
