package br.com.seta.processo.parse.factory;

import br.com.seta.processo.parse.Parse;
import br.com.seta.processo.parse.ParseODS;

public class ParseODSFactory implements ParseFactory {

	@Override
	public Parse createParse() {
		return new ParseODS();
	}
      
}
