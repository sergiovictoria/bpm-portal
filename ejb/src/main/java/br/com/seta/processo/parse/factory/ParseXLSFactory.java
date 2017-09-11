package br.com.seta.processo.parse.factory;

import br.com.seta.processo.parse.Parse;
import br.com.seta.processo.parse.ParseXLS;

public class ParseXLSFactory implements ParseFactory {

	@Override
	public Parse createParse() {
		return new ParseXLS();
	}
    
}
