package com.design.behavioral.command;

public class WriteFileCommand implements Command{
	
	private FileSystemReceiver fileSystem;
	
	public WriteFileCommand(FileSystemReceiver fs) {
		this.fileSystem = fs;
	}

	@Override
	public void execute() {
		// write command is forwarding request to writeFile method
		this.fileSystem.writeFile();
	}
}
