package br.com.amil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Parser {
	public static void main( String[] args ) throws FileNotFoundException {
		try {
			File file = readFile( "FileLog.log" );

			BufferedReader br = new BufferedReader( new FileReader( file ) );
			String line;
			while ( ( line = br.readLine() ) != null ) {
				System.out.println( line );
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		}

	}

	public static File readFile( String fileName ) throws FileNotFoundException {
		URL url = Parser.class.getClassLoader().getResource( fileName );
		if ( url == null )
			throw new FileNotFoundException();
		return new File( url.getPath() );
	}
}
