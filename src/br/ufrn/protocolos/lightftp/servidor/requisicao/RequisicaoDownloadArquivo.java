package br.ufrn.protocolos.lightftp.servidor.requisicao;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

import br.com.servico.manipulaarquivo.Arquivo;
import br.com.servico.mensagem.MensagemServico;
import br.ufrn.protocolos.lightftp.validacao.ValidaRequisicao;

public class RequisicaoDownloadArquivo extends RequisicaoGenerica {

	private String nomeArquivoDownload;
	private MensagemServico mensagemServico;

	public RequisicaoDownloadArquivo() {
		super();
		mensagemServico = new MensagemServico();
	}

	public RequisicaoDownloadArquivo(Socket socket, String mensagemRequisicao) {
		super(socket, mensagemRequisicao);
		mensagemServico = new MensagemServico();
	}

	public void processaRequisicao() throws IOException {
		if (ValidaRequisicao.downloadArquivo(mensagemRequisicao)) {
			entenderMensagemRequisicao();
			String caminhoCompleto = caminhoDiretorioPrincipal + nomeArquivoDownload.trim();
			File arquivoParaDownload = new File(caminhoCompleto);
			byte[] bytesArquivo = Arquivo.fileToByte(arquivoParaDownload);
			mensagemResposta += StatusRequisicao.SUCESSO + "\n";
			mensagemResposta += bytesArquivo.length + "\n";
			byte[] bytesCabecalho = mensagemResposta.getBytes();
			bytesMensagemResposta = mensagemServico.concatenaArraysBytes(bytesCabecalho, bytesArquivo);
			System.out.println(bytesMensagemResposta.length);
			bytesMensagemResposta = mensagemServico.insereFinal(bytesMensagemResposta);
			System.out.println(bytesMensagemResposta.length);
		} else {
			mensagemResposta += StatusRequisicao.TIPO_REQUISICAO_INEXISTENTE;
			bytesMensagemResposta = mensagemResposta.getBytes();
		}
		enviaResposta();
	}
	

	@Override
	public void entenderMensagemRequisicao() {
		// TODO Auto-generated method stub
		super.entenderMensagemRequisicao();
		nomeArquivoDownload = dadosMensagem[1];		
	}

}
