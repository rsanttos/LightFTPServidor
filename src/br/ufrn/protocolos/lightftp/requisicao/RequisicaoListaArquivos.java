package br.ufrn.protocolos.lightftp.requisicao;

public class RequisicaoListaArquivos {
	
	private String diretorioPrincipal;

	public RequisicaoListaArquivos() {
		super();
		diretorioPrincipal = "";
	}
	
	public String processaRequisicao(String mensagem) {
		String mensagemResposta = "";
		// valida requisicao
			// se v�lida
				// pesquisa nome dos arquivos
				// seta status como SUCESSO
			// se nao
				// seta status como REQUISICAO_INVALIDA
			// monta mensagem com resposta			
			// retorna mensagem
		return mensagemResposta;
	}
	
	public void enviaResposta(String mensagem) {
		
	}

}
