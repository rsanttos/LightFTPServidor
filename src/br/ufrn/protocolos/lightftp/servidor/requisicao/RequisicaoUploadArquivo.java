package br.ufrn.protocolos.lightftp.servidor.requisicao;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.protocolos.lightftp.arquivo.ManipulaArquivo;

public class RequisicaoUploadArquivo extends RequisicaoGenerica {
	
	private List<Byte> tipoRequisicao;
	private List<Byte> nomeArquivo;
	private List<Byte> arquivo;

	public RequisicaoUploadArquivo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RequisicaoUploadArquivo(Socket socket, byte[] bytesMensagemRequisicao) {
		super(socket, bytesMensagemRequisicao);
		tipoRequisicao = new ArrayList<Byte>();
		nomeArquivo = new ArrayList<Byte>();
		arquivo = new ArrayList<Byte>();
		// TODO Auto-generated constructor stub
	}


	public void processaRequisicao() throws IOException {
		entenderMensagemRequisicao();
		byte[] arquivoBytes = ManipulaArquivo.transformaArrayDinamicoEmEstatico(arquivo);
		byte[] nomeBytes = ManipulaArquivo.transformaArrayDinamicoEmEstatico(nomeArquivo);
		String nome = new String(nomeBytes).trim();
		ManipulaArquivo.transformaBytesEmArquivo(arquivoBytes, new String(nomeBytes));
	}

	@Override
	public void entenderMensagemRequisicao() {
		// TODO Auto-generated method stub
		
		int i = 0;
		while(bytesMensagemRequisicao[i] != '\n') {
			tipoRequisicao.add(bytesMensagemRequisicao[i]);
			i++;
		}		

		i++;
		while(bytesMensagemRequisicao[i] != '\n') {
			nomeArquivo.add(bytesMensagemRequisicao[i]);
			i++;
		}
		
		i++;
		while(i < bytesMensagemRequisicao.length) {
			arquivo.add(bytesMensagemRequisicao[i]);
			i++;
		}
		
		System.out.println("teste");
	}
	
	
}
