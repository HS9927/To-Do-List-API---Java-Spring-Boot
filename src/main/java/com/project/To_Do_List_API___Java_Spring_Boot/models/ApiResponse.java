package com.project.To_Do_List_API___Java_Spring_Boot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private String message;
    private String detail;
    private T data;
    
    /// Status, Message
    public ApiResponse (int status, String message ) {
    	this.status = status;
    	this.message = message;
    	this.detail = null;
    	this.data = null;
    }
    
    /// Status, Message, Detail
    public ApiResponse (int status, String message, String detail) {
    	this.status = status;
    	this.message = message;
    	this.detail = detail;
    	this.data = null;
    }
    
    /// Status, Message, Data
    public ApiResponse (int status, String message, T data) {
    	this.status = status;
    	this.message = message;
    	this.detail = null;
    	this.data = data;
    }
}
