package br.ufrn.protocolos.lightftp.validacao;

import br.com.servico.manipulaarquivo.Arquivo;
import br.ufrn.protocolos.lightftp.servidor.requisicao.StatusRequisicao;
import br.ufrn.protocolos.lightftp.servidor.requisicao.TipoRequisicao;

public class ValidaRequisicao {

	public static String listaArquivos() {
		String status = "";
		
		if(Arquivo.qtdArquivosDiretorio() > 0) {
			status = StatusRequisicao.SUCESSO;
		} else {
			status = StatusRequisicao.DIRETORIO_VAZIO ;
		}
		
		return status;
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
