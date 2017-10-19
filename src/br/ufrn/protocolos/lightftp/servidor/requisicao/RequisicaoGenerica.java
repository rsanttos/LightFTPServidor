package br.ufrn.protocolos.lightftp.servidor.requisicao;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RequisicaoGenerica {
	
	protected String diretorioRemotoCliente;	
	protected String tipoRequisicao;
	protected String mensagemResposta;
	protected String mensagemRequisicao;
	protected Socket socket;
	protected String[] dadosMensagem;

	public RequisicaoGenerica() {
	}

	
	
	public RequisicaoGenerica(Socket socket, String mensagemRequisicao) {
		super();
		this.mensagemRequisicao = mensagemRequisicao;
		this.socket = socket;
		this.tipoRequisicao = "";
		this.mensagemResposta = "";
		this.diretorioRemotoCliente = "";
	}



	public String getDiretorioRemotoCliente() {
		return diretorioRemotoCliente;
	}

	public void setDiretorioRemotoCliente(String diretorioRemotoCliente) {
		this.diretorioRemotoCliente = diretorioRemotoCliente;
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
		diretorioRemotoCliente = dadosMensagem[1];		
	}


	public void enviaResposta(String mensagem) throws IOException {
		DataOutputStream outBytes = new DataOutputStream(socket.getOutputStream());
		outBytes.write(mensagem.getBytes());
	}
	
}
