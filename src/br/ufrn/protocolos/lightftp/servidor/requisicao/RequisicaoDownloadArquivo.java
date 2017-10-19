package br.ufrn.protocolos.lightftp.servidor.requisicao;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

import br.ufrn.protocolos.lightftp.arquivo.ManipulaArquivo;
import br.ufrn.protocolos.lightftp.validacao.ValidaRequisicao;

public class RequisicaoDownloadArquivo extends RequisicaoGenerica {

	private String nomeArquivoDownload;

	public RequisicaoDownloadArquivo() {
		super();
	}

	public RequisicaoDownloadArquivo(Socket socket, String mensagemRequisicao) {
		super(socket, mensagemRequisicao);
	}

	public void processaRequisicao() throws IOException {
		if (ValidaRequisicao.downloadArquivo(mensagemRequisicao)) {
			entenderMensagemRequisicao();
			File arquivoParaDownload = new File("protocolo.txt");
			byte[] arquivoParaDownloadBytes = ManipulaArquivo.transformaArquivoEmBytes(arquivoParaDownload);
			mensagemResposta += StatusRequisicao.SUCESSO + "\n";
			mensagemResposta += montaArrayBytesArquivo(arquivoParaDownloadBytes);
			System.out.println(mensagemResposta);
		} else {
			mensagemResposta += StatusRequisicao.TIPO_REQUISICAO_INEXISTENTE;
		}
		enviaResposta(mensagemResposta);
	}

	public String montaArrayBytesArquivo(byte[] array) {
		String arrayStr = "";
		for (int i = 0; i < array.length; i++) {
			byte b = array[i];
			if(i + 1 < array.length) {
				arrayStr += b + ";";				
			} else {
				arrayStr += b;				
			}
		}
		
		return arrayStr;
	}

	@Override
	public void entenderMensagemRequisicao() {
		// TODO Auto-generated method stub
		super.entenderMensagemRequisicao();
		String arquivoDownload = dadosMensagem[2];
		nomeArquivoDownload = ManipulaArquivo.DIRETORIO_REMOTO_PRINCIPAL + diretorioRemotoCliente + "\\"
				+ arquivoDownload;
	}

}
