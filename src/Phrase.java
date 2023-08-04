
/**
 *Esta clase es la estructura de la frase tipo, 
 *cada frase contiene una cantidad de palabras, cada frase 
 *tiene una cantidad minima de palabras
 */
public class Phrase  {
	//Atributos
	private StringBuilder data;
	private int numbersWord;
	final static int max_number_word=10;

	//Constructores
	public Phrase() {

	}

	//Getters y Setters
	public StringBuilder getData() {
		return data;
	}

	public void setData(StringBuilder data) {
		this.data = data;
	}

	public int getNumbersWord() {
		return numbersWord;
	}

	public void setNumbersWord(int numbersWord) {
		this.numbersWord = numbersWord;
	}

	/**
	 * Este metodo nos ayuda a agregar palabras a una frase dada y cada
	 * vez que la frase llegue a 5 palabras quitaremos la primera
	 * palabra y agregue la nueva palabra al final de la oracion
	 */
	public void addword(String word) {
		word = word.replaceAll("[!?,.:;)(]", "").trim();//Remueve signos de puntuacion
		if (data == null) {													
			StringBuilder sb = new StringBuilder();
			sb.append(word);
			this.setData(sb);
			this.numbersWord += 1;
		} else {
			StringBuilder temp = this.getData();
			if (this.numbersWord < max_number_word) {
				if (temp.length() > 0) {
					temp.append(" ").append(word);

				} else {
					temp.append(word);
				}
				this.setData(temp);
				this.numbersWord += 1;

			} else {
				temp.delete(0, temp.indexOf(" ") + 1);
				temp.append(" ").append(word);
				this.setData(temp);
				this.numbersWord = max_number_word;

			}
		}
	}	
}
