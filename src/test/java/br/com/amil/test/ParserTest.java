package br.com.amil.test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.amil.MatchLog;
import br.com.amil.Parser;

/**
 * Classe de teste da class {@linkplain Parser}
 * 
 * @author wesleyaraujo
 *
 */
public class ParserTest {

	private List<MatchLog> matchs;

	@Before
	public void beforeTest() {
		matchs = new ArrayList<>();
	}

	/**
	 * Método que deve lançar um {@linkplain RuntimeException} pois o arquivo de
	 * log está vazio.
	 * <hr>
	 * 
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	@Test( expected = RuntimeException.class )
	public void shouldThrowErrorWhenReadFileLogIsEmpty() throws FileNotFoundException, Exception {
		Parser.doParser( Parser.readFile( "FileEmpty.log" ), matchs );
	}

	/**
	 * Método que deve lancar um {@link FileNotFoundException} pois o arquivo
	 * FileLog1.log não existe.
	 * 
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	@Test( expected = FileNotFoundException.class )
	public void shouldThrowExceptionWhenReadFileLogIsNull() throws FileNotFoundException, Exception {
		// Arquivo FileLog1.log não existe
		Parser.doParser( Parser.readFile( "FileLog1.log" ), matchs );
	}

	/**
	 * Método que deve executar sem erros. <br>
	 * O arquivo de log está correto.
	 * 
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	@Test
	public void shouldRunWithoutErrors() throws FileNotFoundException, Exception {
		// Arquivo válido
		Parser.doParser( Parser.readFile( "FileLogTest.log" ), matchs );
		Assert.assertTrue( matchs != null && !matchs.isEmpty() );
	}

	/**
	 * Método que testa a chamada do método doParser passando uma lista nula de
	 * {@linkplain MatchLog}.
	 * <hr>
	 * Resultado esperado: é execuado com sucesso, pois internamente é criada
	 * uma lista caso seja passado nulo no parâmetro matchs.
	 * 
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	@Test
	public void testWithListOfMatchsIsNull() throws FileNotFoundException, Exception {
		matchs = Parser.doParser( Parser.readFile( "FileLogTest.log" ), null );
		Assert.assertTrue( matchs != null && !matchs.isEmpty() );
	}

	/**
	 * Método para testar a chamado do método doParser com um arquivo inválido.
	 * (Arquivo inválido é aquele que não contem em alguma linha uma das
	 * palavras chaves: New Match, killed ou ended)
	 * <hr>
	 * Resultado esperado: Deve ser lançada uma {@linkplain RuntimeException}
	 * 
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	@Test( expected = RuntimeException.class )
	public void testWithFileLogInvalid() throws FileNotFoundException, Exception {
		Parser.doParser( Parser.readFile( "FileLogInvalid.log" ), matchs );
	}

	/**
	 * Método que testa se o jogador Neto morreu apenas uma vez na partida.
	 * <hr>
	 * Resultado esperado: executado com sucesso, pois no aquivo de log
	 * informado, o jogador neto morre apenas uma vez.
	 * 
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	@Test
	public void testIfNetoDiedOnlyOnce() throws FileNotFoundException, Exception {
		Parser.doParser( Parser.readFile( "FileLogNetoDiedOnce.log" ), matchs );
		MatchLog match = matchs.get( 0 );
		Integer netoKills = match.getPlayers().get( "Neto" );
		Assert.assertTrue( netoKills == 1 );
	}

}
