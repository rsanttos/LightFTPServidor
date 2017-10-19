package br.ufrn.protocolos.lightftp.validacao;

import br.ufrn.protocolos.lightftp.servidor.requisicao.TipoRequisicao;

public class ValidaRequisicao {

	public static boolean listaArquivos(String mensagem) {
		boolean valido = true;
		return valido;
	}

	public static boolean downloadArquivo(String mensagem) {
		boolean valido = true;
		return valido;
	}

	public static boolean uploadArquivo(String mensagem) {
		boolean valido = true;
		return valido;
	}

	public static boolean finalizaConexao(String mensagem) {
		boolean valido = true;
		return valido;
	}

	public static String tipo(String mensagem) {
		String tipoRequisicao = "";
		String[] dadosMensagem = mensagem.split("\n");
		tipoRequisicao = dadosMensagem[0];
		if (tipoRequisicao.equals(TipoRequisicao.SOLICITACAO_LISTA_ARQUIVOS)
				|| tipoRequisicao.equals(TipoRequisicao.FINALIZAR_CONEXAO)
				|| tipoRequisicao.equals(TipoRequisicao.REQUISICAO_INVALIDA)
				|| tipoRequisicao.equals(TipoRequisicao.SOLICITACAO_DOWNLOAD_ARQUIVO)
				|| tipoRequisicao.equals(TipoRequisicao.SOLICITACAO_UPLOAD_ARQUIVO)) {
			return tipoRequisicao;
		} else {
			tipoRequisicao = TipoRequisicao.REQUISICAO_INVALIDA;
			return tipoRequisicao;
		}
	}
}
