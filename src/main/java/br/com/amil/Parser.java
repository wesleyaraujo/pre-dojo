package br.com.amil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * Classe que realizado a leitura de um arquivo de log e monta o ranking das
 * partidas.
 * 
 * @author wesleyaraujo
 *
 */
public class Parser {

	public static void main( String[] args ) throws FileNotFoundException {
		try {
			File file = Parser.readFile( "FileLog.log" );
			List<MatchLog> matchLogs = new ArrayList<>();
			Parser.doParser( file, matchLogs );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	/**
	 * Método responsável pela leitura do conteudo do arquivo de log e extrair
	 * as informações das partidas.
	 * 
	 * @param file
	 *            arquivo de log
	 * @param matchs
	 *            lista com as partidas
	 * @return lista com as partidas
	 * @throws Exception
	 */
	@SuppressWarnings( "resource" )
	public static List<MatchLog> doParser( File file, List<MatchLog> matchs ) throws Exception {
		if ( file != null ) {
			BufferedReader br = new BufferedReader( new FileReader( file ) );
			String line;
			int currentLine = 1;
			int totalKills = 0;
			MatchLog match = null;
			while ( ( line = br.readLine() ) != null ) {
				currentLine++;
				String[] splitLine = line.split( "\\s+" );
				if ( line.contains( "New match" ) ) {
					match = new MatchLog();
					match.setMatchId( Integer.valueOf( splitLine[5] ) );
					match.setLineStart( currentLine );
					if ( matchs == null )
						matchs = new ArrayList<>();
					matchs.add( match );
					totalKills = 0;
				} else if ( line.contains( "killed" ) ) {
					String killer = splitLine[3];
					String killed = splitLine[5];
					Integer pointsBeforeKilled = match.getPlayers().get( killed );
					if ( pointsBeforeKilled == null )
						match.getPlayers().put( killed, 1 );
					else
						match.getPlayers().put( killed, ++pointsBeforeKilled );

					if ( !killer.equals( "<WORLD>" ) ) {
						match.setTotalKills( ++totalKills );
						if ( !match.getPlayers().containsKey( killer ) )
							match.getPlayers().put( killer, 0 );
					}
				} else if ( !line.contains( "ended" ) ) {
					throw new RuntimeException( "Arquivo Inválido." );
				}
			}
			if ( !matchs.isEmpty() ) {
				for ( MatchLog m : matchs ) {
					System.out.println( "Partida:" + m.getMatchId() );
					System.out.println( "Total de Kills: " + m.getTotalKills() );
					System.out.println( "---Jogadores---" );
					for ( Entry<String, Integer> p : m.getPlayers().entrySet() ) {
						System.out.println( "Nome: " + p.getKey() );
						System.out.println( "Mortes: " + p.getValue() );
					}
					System.out.println( "--------------------" );
				}
			} else {
				throw new RuntimeException( "Arquivo de Log vazio." );
			}
		} else {
			throw new FileNotFoundException( "Arquivo não Encontrado." );
		}

		return matchs;

	}

	/**
	 * Método que obtém o arquivo a partir do nome.
	 * 
	 * @param fileName
	 *            nome do arquivo
	 * @return um arquivo
	 * @throws FileNotFoundException
	 */
	public static File readFile( String fileName ) throws FileNotFoundException {
		URL url = Parser.class.getClassLoader().getResource( fileName );
		if ( url == null )
			throw new FileNotFoundException( "Arquivo não Encontrado." );
		return new File( url.getPath() );
	}
}
