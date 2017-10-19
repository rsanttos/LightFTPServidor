package br.ufrn.protocolos.lightftp.servidor.main;

import java.io.IOException;

import br.ufrn.protocolos.lightftp.servidor.Servidor;

public class ServidorMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Servidor servidor = new Servidor();
		servidor.iniciaServidor();
	}

}
