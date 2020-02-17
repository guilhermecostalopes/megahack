package br.com.megahack;

import java.io.IOException;
import java.io.InputStream;

public class Converter {

	public static byte[] imageToByte(String image) throws IOException {
		InputStream is = Converter.class.getResourceAsStream(image);
		byte[] buffer = new byte[is.available()];
		is.read(buffer);
		is.close();
		return buffer;
	}
}
