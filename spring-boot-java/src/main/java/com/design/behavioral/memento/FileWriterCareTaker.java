package com.design.behavioral.memento;

public class FileWriterCareTaker {
	
	private Object obj;
	
	public void save(FileWriterUtil fileWriter) {
		this.obj = fileWriter.save();
	}
	
	public void undo(FileWriterUtil fileWriter) {
		fileWriter.undoToLastSave(obj);
	}
}
