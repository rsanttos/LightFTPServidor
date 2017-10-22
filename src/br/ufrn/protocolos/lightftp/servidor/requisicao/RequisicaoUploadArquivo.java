package br.ufrn.protocolos.lightftp.servidor.requisicao;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import br.com.servico.lista.Lista;
import br.com.servico.manipulaarquivo.Arquivo;
import br.ufrn.protocolos.lightftp.validacao.ValidaRequisicao;

public class RequisicaoUploadArquivo extends RequisicaoGenerica {

	private List<Byte> tipoRequisicao;
	private List<Byte> nomeArquivo;
	private List<Byte> tamanhoArquivo;
	private List<Byte> arquivo;

	public RequisicaoUploadArquivo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequisicaoUploadArquivo(Socket socket, byte[] bytesMensagemRequisicao) {
		super(socket, bytesMensagemRequisicao);
		tipoRequisicao = new ArrayList<Byte>();
		nomeArquivo = new ArrayList<Byte>();
		tamanhoArquivo = new ArrayList<Byte>();
		arquivo = new ArrayList<Byte>();
		// TODO Auto-generated constructor stub
	}

	public void processaRequisicao() throws IOException {
		if(ValidaRequisicao.uploadArquivo(mensagemRequisicao)) {			
			entenderMensagemRequisicao();
			byte[] nomeBytes = Lista.dinamicoParaEstatico(nomeArquivo);
			byte[] arquivoBytes = Lista.dinamicoParaEstatico(arquivo);
			String nome = new String(nomeBytes).trim();
			String caminhoArquivo = caminhoDiretorioPrincipal + nome;
			Arquivo.byteToFile(arquivoBytes, caminhoArquivo);
			mensagemResposta += StatusRequisicao.SUCESSO + "\n";
		} else {
			mensagemResposta += StatusRequisicao.ARQUIVO_NAO_ENCONTRADO + "\n";
		}
		bytesMensagemResposta = mensagemResposta.getBytes();
		enviaResposta();
	}

	@Override
	public void entenderMensagemRequisicao() {
		// TODO Auto-generated method stub

		int i = 0;
		while (bytesMensagemRequisicao[i] != '\n') {
			tipoRequisicao.add(bytesMensagemRequisicao[i]);
			i++;
		}

		i++;
		while (bytesMensagemRequisicao[i] != '\n') {
			nomeArquivo.add(bytesMensagemRequisicao[i]);
			i++;
		}

		i++;
		while (bytesMensagemRequisicao[i] != '\n') {
			tamanhoArquivo.add(bytesMensagemRequisicao[i]);
			i++;
		}
		
		byte[] tamanhoBytes = Lista.dinamicoParaEstatico(tamanhoArquivo);
		String tamanhoStr = new String(tamanhoBytes);
		int tamanhoArquivo = Integer.parseInt(tamanhoStr);
		
		i++;
		int j = 0;
		while (j < tamanhoArquivo) {
			arquivo.add(bytesMensagemRequisicao[i]);
			i++;
			j++;
		}
	}
}
