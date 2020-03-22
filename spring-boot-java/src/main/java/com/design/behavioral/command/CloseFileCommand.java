package com.design.behavioral.command;

public class CloseFileCommand implements Command{
	
	private FileSystemReceiver fileSystem;
	
	public CloseFileCommand(FileSystemReceiver fs) {
		this.fileSystem = fs;
	}

	@Override
	public void execute() {
		// close command is forwarding request to closeFile method
		this.fileSystem.closeFile();
	}
}
