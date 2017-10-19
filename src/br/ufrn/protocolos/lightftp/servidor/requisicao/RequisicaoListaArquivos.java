package br.ufrn.protocolos.lightftp.servidor.requisicao;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import br.ufrn.protocolos.lightftp.arquivo.ManipulaArquivo;
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
		if (ValidaRequisicao.listaArquivos(mensagemRequisicao)) {
			entenderMensagemRequisicao();
			listaArquivos = ManipulaArquivo.listarArquivos(diretorioRemotoCliente);
			mensagemResposta = StatusRequisicao.SUCESSO + "\n";
			mensagemResposta += diretorioRemotoCliente + "\n";
			mensagemResposta += listaArquivos;
		} else {
			mensagemResposta += StatusRequisicao.MAL_FORMATADO + "\n";
		}
		enviaResposta(mensagemResposta);
	}

}