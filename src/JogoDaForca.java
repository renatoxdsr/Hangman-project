/**
 * Projeto1 de POO
 * Grupo de alunos:  Renato Xavier da Silva Rodrigues
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JogoDaForca {
	private int N; // quantidade de palavras do arquivo (lido do arquivo)
	private String[] palavras; // um array com as N palavras (lidas do arquivo)
	private String[] dicas; // um array com as N dicas (lidas do arquivo)
	private String palavraAtual;
	private int indice = -1; // indice da palavra sorteadado jogo
	private int acertos; // total de acertos do jogo
	private int erros = 6; // total de erros do jogo
	private String[] penalidades = {"perna","perna","braço","braço","tronco","cabeça"};
	private boolean palavraAdivinhada = true;

	
	public JogoDaForca(String nomearquivo) throws Exception {
		Scanner arq = null;	
		
			try {
				arq = new Scanner(new File(nomearquivo));
				String linha;
				N = Integer.parseInt(arq.nextLine()); //quantidade de palavras/dicas
				palavras = new String[N]; //define o tamanho dos arrays que surgirão
				dicas = new String[N];
				
				for(int i=0; arq.hasNextLine(); i++) {
					linha = arq.nextLine(); //pega a linha em questão
					palavras[i] = linha.split(";")[0]; //pega a palavra
					dicas[i] = linha.split(";")[1]; //pega as dicas
				}
				arq.close();
				
			}catch(FileNotFoundException e) {
				throw new Exception("Arquivo Inexistente.");
				
			}
		}

	public void iniciar() {
		if(palavraAdivinhada) {
			palavraAdivinhada = false;
		}else {
			palavras[indice] = palavraAtual;
		}
		
		indice = (int) (Math.random() *N);
		palavraAtual = (palavras[indice]);
		acertos = erros = 0;
		}

	public boolean adivinhou(String letra) {
		String palavra = palavras[indice]; 

		//pegando a palavra sorteada
		//A variável palavra NÃO está recebendo uma cópia da palavras[indice].
		//Ela está apenas redirecionando para mesmo lugar que palavras[indice].
		//Dessa maneira, se o conteúdo de palavra for alterado, o conteúdo de palavras[indice] também será.
		//Todavia, isso só acontece com objetos.
		String guess;
		
		if(palavra.contains(letra)) {
			int i, j = 0, happen = 0; //Happen é quantas vezes acontece a letra na palavra
			
			for(i = 0; i < palavra.length(); i++) {
				guess = palavra.substring(i, i+1); //pegando letra por letra
				if(guess.equals(letra)) //se o guess atual é igual a Letra
					happen++;	
					
			}
			
			int[] indices = new int[happen]; 
			//array para retornar os indices da palavra em que
			//ocorre um guess 

			for(i=0; i<palavra.length(); i++) {
				guess = palavra.substring(j, i+1); //pegando letra por letra
				if(guess.equals(letra))
					indices[i++] = j;
				System.out.println(palavra);
				
			
			}
			
		String palavrita = palavra.replaceAll(letra, "-"); //substituição da letra por -
			String palavrao = palavrita;
			palavras[indice] = palavrao;
			acertos += happen;
			System.out.print(indices);
			return true;
			
		
		}else {
			erros++;
			return false;
		}
		
		//verifica  se  esta  letra  ocorre  dentro  da  palavra  advinhada.  
		//Se for verdadeiro, retornará  os índices  dessas presentes  happen's.  
		//Outrossim,  retornará  null.  
		//Além  disso,  o  método contabilizará  um  acerto  no  jogo  
		//para  cada  vez que aparecer  a  letra  encontrada  na palavra.  
		//Como também  contabilizará  um  erro  para  a  inexistência  da  letra.

		}
	
	public boolean terminou() {
		if(acertos == palavraAtual.length() || erros >= 6) {
			return true;
		} 
		return false;
		
	}

	public String getPalavra() {
		return this.palavras[indice];

	}


	public String getDica() {
		return this.dicas[indice];
	}


	public String getPenalidade() {
		return penalidades[erros-1];
	}

	public int getAcertos() {
		return this.acertos;
	}

	public int getErros() {
		return this.erros;
	}

	public String getResultado() {
		String result = ("você perdeu");
		if( acertos > erros || erros == 5 ) {
			result = ("você ganhou");
		}else {
			result = ("você perdeu");
		}

		return result;
		
	}
}