package br.ufrn.protocolos.lightftp.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.Date;

import br.ufrn.protocolos.lightftp.requisicao.RequisicaoListaArquivos;
import br.ufrn.protocolos.lightftp.requisicao.TipoRequisicao;
import br.ufrn.protocolos.lightftp.validacao.ValidaRequisicao;

public class Servidor {

	private RequisicaoListaArquivos requisicaoListaArquivos;

	public Servidor() {
	}

	public void iniciaServidor() throws IOException {
		System.out.println("Servidor em execução.");
		while (true) {
			ServerSocket connectionSocket = new ServerSocket(7777);
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Aguardando conexão do cliente...");
			Socket socket = connectionSocket.accept();
			System.out.println("Cliente " + socket.getInetAddress().getHostAddress() + " conectado.");

			DataInputStream inBytes = new DataInputStream(socket.getInputStream());
			byte[] dadosRecedidos = new byte[128];
			inBytes.read(dadosRecedidos);
			String mensagemRecebida = new String(dadosRecedidos).trim();
			System.out.println("Cliente diz: " + mensagemRecebida);

			String tipoRequisicao = ValidaRequisicao.tipo(mensagemRecebida);

			if (tipoRequisicao.equals(TipoRequisicao.SOLICITACAO_LISTA_ARQUIVOS)) {
				requisicaoListaArquivos = new RequisicaoListaArquivos();

			} else if (tipoRequisicao.equals(TipoRequisicao.SOLICITACAO_DOWNLOAD_ARQUIVO)) {

			} else if (tipoRequisicao.equals(TipoRequisicao.SOLICITACAO_UPLOAD_ARQUIVO)) {

			} else if (tipoRequisicao.equals(TipoRequisicao.FINALIZAR_CONEXAO)) {

			} else if (tipoRequisicao.equals(TipoRequisicao.REQUISICAO_INVALIDA)){

			}

			Instant instant = Instant.now();
			long timeStampSegundos = instant.getEpochSecond();

			Date data = new Date((long) (timeStampSegundos * 1000));

			DataOutputStream outBytes = new DataOutputStream(socket.getOutputStream());
			outBytes.writeInt((int) timeStampSegundos);

			System.out.println("Data " + data + " enviada para o cliente no formato: " + timeStampSegundos);
			System.out.println("--------------------------------------------------------------------");

			socket.close();
			connectionSocket.close();
		}
	}
}
