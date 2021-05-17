package com.quantcast.meta;

import com.quantcast.output.ConsoleWriter;
import com.quantcast.output.OutputWriter;

public enum OutputWriterMeta {

	CONSOLE("CONSOLE",new ConsoleWriter());
	
	private String outputType;
	private OutputWriter writer;
	
	private OutputWriterMeta(String outputType, OutputWriter writer) {
		this.outputType = outputType;
		this.writer = writer;
	}
	
	public OutputWriter getOutputWriter() {
		return this.writer;
	}
}
