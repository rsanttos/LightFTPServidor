package br.ufrn.protocolos.lightftp.arquivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ManipulaArquivo {

	public static byte[] transformaArquivoEmBytes(File arquivo) {
		int len = (int) arquivo.length();
		byte[] sendBuf = new byte[len];
		FileInputStream inFile = null;
		try {
			inFile = new FileInputStream(arquivo);
			inFile.read(sendBuf, 0, len);
			inFile.close();
		} catch (FileNotFoundException fnfex) {
			fnfex.printStackTrace();
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
		return sendBuf;
	}

	public static void transformaBytesEmArquivo(byte[] dadosRecebidos, String nomeArquivo) throws IOException {
		File arquivoRecebido = new File(nomeArquivo);
		OutputStream os = new FileOutputStream(arquivoRecebido);
		os.write(dadosRecebidos);
		os.flush();
		System.out.println("Arquivo " + nomeArquivo + " criado com sucesso.");
		os.close();
	}
}
