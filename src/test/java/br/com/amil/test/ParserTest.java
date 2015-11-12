package br.com.amil.test;

import java.io.FileNotFoundException;

import org.junit.Test;

public class ParserTest {

	@Test
	public void shouldThrowErrorWhenReadFileLogIsEmpty() {

	}

	@Test( expected = FileNotFoundException.class )
	public void shouldThrowExceptionWhenReadFileLogIsNull() {

	}
}
