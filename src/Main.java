import java.io.IOException;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			
			public void run() {
				GUI gui = new GUI();
				gui.Display();
			}
		});	
	}

}