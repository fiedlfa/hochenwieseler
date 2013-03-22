package github.hochenwieseler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Eine einfacher Filewriter Kapselung
 * 
 * @author FF-2012
 * 
 */
public class DateiSchreiber {
	private FileWriter writer;
	int i = 0;

	public DateiSchreiber(String Name) {
		File file = new File("IntegerMinValue " + Name + ".txt");
		try {
			// new FileWriter(file ,true) - falls die Datei bereits existiert
			// werden die Bytes an das Ende der Datei geschrieben

			// new FileWriter(file) - falls die Datei bereits existiert
			// wird diese überschrieben
			writer = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void schreibe(String string) {
		try {
			writer.write(string + ";");
			i++;
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (i >= 1000) {
			i = 0;
			try {
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void bye() {
		try {

			// Schreibt den Stream in die Datei
			// Sollte immer am Ende ausgeführt werden, sodass der Stream
			// leer ist und alles in der Datei steht.
			writer.flush();

			// Schließt den Stream
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
