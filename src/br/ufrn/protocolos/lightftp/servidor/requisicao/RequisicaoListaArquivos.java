package br.ufrn.protocolos.lightftp.servidor.requisicao;

import java.io.IOException;
import java.net.Socket;

import br.com.servico.manipulaarquivo.Arquivo;
import br.ufrn.protocolos.lightftp.validacao.ValidaRequisicao;

public class RequisicaoListaArquivos extends RequisicaoGenerica {
	
	protected String listaArquivos;

	public RequisicaoListaArquivos(Socket socket, String mensagemRequisicao) {
		super();
		this.socket = socket;
		this.mensagemRequisicao = mensagemRequisicao;
		this.mensagemResposta = "";
	}

	public void processaRequisicao() throws IOException {
		status = ValidaRequisicao.listaArquivos();
		
		if (status.equals(StatusRequisicao.SUCESSO)) {
			entenderMensagemRequisicao();
			listaArquivos = Arquivo.listarArquivosDoDiretorio();
			mensagemResposta = status + "\n";
			mensagemResposta += listaArquivos;
			bytesMensagemResposta = mensagemResposta.getBytes();
		} else {
			mensagemResposta += status + "\n";
			bytesMensagemResposta = mensagemResposta.getBytes();
		}
		enviaResposta();
	}

}
