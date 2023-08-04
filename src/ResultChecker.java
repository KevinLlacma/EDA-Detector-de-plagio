public class ResultChecker {
	//Atributos
	private boolean [] result;
	private String [] titulos;
	private int i;

	//Constructor
	public ResultChecker(int n){
		titulos= new String[n];
		result= new boolean[n];
		i= 0;
	}

	//Setter y Getters
	public boolean [] getResults() {
		return this.result;
	}

	public boolean getResult(int i) {
		return this.result[i];
	}

	public void setResult(boolean[] result) {
		this.result = result;
	}

	public void setResult(boolean b, String t) {
		titulos[i]= t;
		result[i]= b;
		i++;
	}

	public String [] getTitulos() {
		return this.titulos;
	}

	public void setTitulo(String t, int i) {
		System.out.println("Se agrego " + t + " " + i);
		this.titulos[i]= t;
	}

	public void setPalabras(String[] palabras_) {
		this.titulos = palabras_;
	}

	public String imprimir(){
		//Entrando a Result Checker
		StringBuilder rpta= new StringBuilder();
		for (int i = 0; i < getResults().length; i++) {			
			rpta.append(titulos[i] + " Plagio :"+result[i] +"\n");
		}
		System.out.println(rpta);
		return rpta.toString();
	}
}
