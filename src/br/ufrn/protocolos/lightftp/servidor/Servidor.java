package br.ufrn.protocolos.lightftp.servidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import br.ufrn.protocolos.lightftp.servidor.requisicao.RequisicaoDownloadArquivo;
import br.ufrn.protocolos.lightftp.servidor.requisicao.RequisicaoListaArquivos;
import br.ufrn.protocolos.lightftp.servidor.requisicao.TipoRequisicao;
import br.ufrn.protocolos.lightftp.validacao.ValidaRequisicao;

public class Servidor {

	private RequisicaoListaArquivos requisicaoListaArquivos;
	private RequisicaoDownloadArquivo requisicaoDownloadArquivo;

	public Servidor() {
	}

	public void iniciaServidor() throws IOException {
		System.out.println("Servidor em execução.");
		while (true) {
			ServerSocket connectionSocket = new ServerSocket(7777);
			Socket socket = connectionSocket.accept();

			DataInputStream inBytes = new DataInputStream(socket.getInputStream());
			byte[] dadosRecedidos = new byte[128];
			inBytes.read(dadosRecedidos);
			String mensagemRecebida = new String(dadosRecedidos);

			String tipoRequisicao = ValidaRequisicao.tipo(mensagemRecebida);

			if (tipoRequisicao.equals(TipoRequisicao.SOLICITACAO_LISTA_ARQUIVOS)) {
				requisicaoListaArquivos = new RequisicaoListaArquivos(socket, mensagemRecebida);
				requisicaoListaArquivos.processaRequisicao();
			} else if (tipoRequisicao.equals(TipoRequisicao.SOLICITACAO_DOWNLOAD_ARQUIVO)) {
				requisicaoDownloadArquivo = new RequisicaoDownloadArquivo(socket, mensagemRecebida);
				requisicaoDownloadArquivo.processaRequisicao();
			} else if (tipoRequisicao.equals(TipoRequisicao.SOLICITACAO_UPLOAD_ARQUIVO)) {

			} else if (tipoRequisicao.equals(TipoRequisicao.FINALIZAR_CONEXAO)) {

			} else if (tipoRequisicao.equals(TipoRequisicao.REQUISICAO_INVALIDA)){

			}

			socket.close();
			connectionSocket.close();
		}
	}
}
