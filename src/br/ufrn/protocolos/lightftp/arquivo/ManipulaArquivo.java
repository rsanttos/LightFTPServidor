package br.ufrn.protocolos.lightftp.arquivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ManipulaArquivo {

	public static String DIRETORIO_REMOTO_PRINCIPAL = "D:/Users/f043684/Desktop/Pessoal/engSoftware/lightftp/remoto";
	public static String DIRETORIO_REMOTO_CLIENTE = "/cliente01";
	public static String DIRETORIO_LOCAL_CLIENTE = "D:/Users/f043684/Desktop/Pessoal/engSoftware/lightftp/local";
	
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
	
	public static String listarArquivos(String caminhoDiretorioCliente) {
		String caminhoCompleto = DIRETORIO_REMOTO_PRINCIPAL + caminhoDiretorioCliente;
		File diretorio = new File(caminhoCompleto);
		String listaArquivos = "";
		
		for(File arquivo : diretorio.listFiles()) {
			listaArquivos += arquivo.getName() + "\n";			
		}
		
		return listaArquivos;
	}
	
	public static byte[] transformaArrayDinamicoEmEstatico(List<Byte> arrayDinamico) {
		byte[] arrayEstatico = new byte[arrayDinamico.size()];
		int i = 0;
		for(Byte b : arrayDinamico) {
			arrayEstatico[i] = b;
			i++;
		}
		
		return arrayEstatico;
	}
}
