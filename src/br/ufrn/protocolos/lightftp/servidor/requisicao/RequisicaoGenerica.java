package br.ufrn.protocolos.lightftp.servidor.requisicao;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RequisicaoGenerica {
	
	protected String caminhoDiretorioPrincipal = "arquivos/";
	
	protected String tipoRequisicao;
	protected String mensagemResposta;
	protected String mensagemRequisicao;
	protected Socket socket;
	protected String[] dadosMensagem;
	
	protected byte[] bytesMensagemResposta;
	protected byte[] bytesMensagemRequisicao;
	
	
	public RequisicaoGenerica() {
	}	
	
	public RequisicaoGenerica(Socket socket, String mensagemRequisicao) {
		super();
		this.mensagemRequisicao = mensagemRequisicao;
		this.socket = socket;
		this.tipoRequisicao = "";
		this.mensagemResposta = "";
	}

	public RequisicaoGenerica(Socket socket, byte[] bytesMensagemRequisicao) {
		super();
		this.socket = socket;
		this.bytesMensagemRequisicao = bytesMensagemRequisicao;
		this.tipoRequisicao = "";
		this.mensagemResposta = "";
	}


	public String getMensagemResposta() {
		return mensagemResposta;
	}

	public void setMensagemResposta(String mensagemResposta) {
		this.mensagemResposta = mensagemResposta;
	}

	public String getMensagemRequisicao() {
		return mensagemRequisicao;
	}

	public void setMensagemRequisicao(String mensagemRequisicao) {
		this.mensagemRequisicao = mensagemRequisicao;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}	
	
	public void entenderMensagemRequisicao() {
		dadosMensagem = mensagemRequisicao.split("\n");
		tipoRequisicao = dadosMensagem[0];		
	}


	public void enviaResposta() throws IOException {
		DataOutputStream outBytes = new DataOutputStream(socket.getOutputStream());
		outBytes.write(bytesMensagemResposta);
	}

	public byte[] getBytesMensagemResposta() {
		return bytesMensagemResposta;
	}

	public void setBytesMensagemResposta(byte[] bytesMensagemResposta) {
		this.bytesMensagemResposta = bytesMensagemResposta;
	}
	
}
