package com.hotent.mobile.model.form;

public enum PlatformType {
	undefine("undefine"), 
	PC("PC"), 
	mobile("mobile");

	private final String value;

	private PlatformType(String value) {
		this.value = value;
	}

	public String getValue() {
		if(value.equals("mobile")) return "1";
		else return "0";
	}

	@Override
	public String toString() {
		return getValue();
	}
	
	public static PlatformType getEnumFromString(String string) {
		if (string != null) {
			if(string.trim().equals("1")){
				return mobile;
			}else if(string.trim().equals("0")){
				return PC;
			}else{
				return Enum.valueOf(PlatformType.class, string.trim());
			}
		}
		return undefine;
	}
}