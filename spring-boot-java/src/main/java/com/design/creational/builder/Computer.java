package com.design.creational.builder;

/**
 * 
 * @author zhangzhigang
 */
public class Computer {
	
	// required parameters
	private String HDD;
	
	private String RAM;
	
	// optional parameters
	private boolean isGraphicsGardEnabled;
	
	private boolean isBluetoothEnabled;

	public String getHDD() {
		return HDD;
	}

	public String getRAM() {
		return RAM;
	}

	public boolean isGraphicsGardEnabled() {
		return isGraphicsGardEnabled;
	}

	public boolean isBluetoothEnabled() {
		return isBluetoothEnabled;
	}
	
	private Computer(ComputerBuilder builder) {
		this.HDD = builder.HDD;
		this.RAM = builder.RAM;
		this.isBluetoothEnabled = builder.isBluetoothEnabled;
		this.isGraphicsGardEnabled = builder.isGraphicsGardEnabled;
	}
	
	@Override
	public String toString(){
		return "RAM= "+this.getRAM()+", HDD="+this.getHDD() + ", isBluetoothEnabled=" + this.isBluetoothEnabled() + ", isGraphicsGardEnabled=" + this.isGraphicsGardEnabled();
	}
	
	// Builder Class
	public static class ComputerBuilder {
		
		// required parameters
		private String HDD;
		
		private String RAM;
		
		// optional parameters
		private boolean isGraphicsGardEnabled;

		private boolean isBluetoothEnabled;
		
		public ComputerBuilder(String hdd, String ram) {
			this.HDD = hdd;
			this.RAM = ram;
		}
		
		public ComputerBuilder setGraphicsGardEnabled(boolean isGraphicsGardEnabled) {
			this.isGraphicsGardEnabled = isGraphicsGardEnabled;
			return this;
		}

		public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) {
			this.isBluetoothEnabled = isBluetoothEnabled;
			return this;
		}
		
		public Computer build() {
			return new Computer(this);
		}
		
	}
}
