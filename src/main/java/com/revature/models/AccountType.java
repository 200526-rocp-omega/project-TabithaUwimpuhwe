package com.revature.models;

import java.util.Objects;

public class AccountType {
	  private int typeId; 
	  private String type; 
	  
	public AccountType() {
		super();
	}

	public AccountType(int typeId, String type) {
		super();
		this.typeId = typeId;
		this.type = type;
	}


	public int getTypeId() {
		return typeId;
	}


	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	@Override
	public int hashCode() {
		return Objects.hash(type, typeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountType other = (AccountType) obj;
		return Objects.equals(type, other.type) && typeId == other.typeId;
	}

	@Override
	public String toString() {
		return "AccountType [typeId=" + typeId + ", type=" + type + "]";
	}
	  
	}
