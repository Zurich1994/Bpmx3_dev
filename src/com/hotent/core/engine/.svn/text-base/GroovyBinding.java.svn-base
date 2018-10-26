package com.hotent.core.engine;

import groovy.lang.Binding;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GroovyBinding extends Binding{
	  private Map variables;
	  private static ThreadLocal<Map<String,Object>> localVars=new ThreadLocal<Map<String,Object>>();
	  
	  private static Map<String,Object> propertyMap=new HashMap<String, Object>();

	  public GroovyBinding()
	  {
	  }

	  public GroovyBinding(Map variables)
	  {
		  localVars.set(variables);
	    //this.localVars = variables;
	  }

	  public GroovyBinding(String[] args)
	  {
	    this();
	    setVariable("args", args);
	  }

	  public Object getVariable(String name)
	  {
		  Map<String,Object> map=localVars.get();
		  Object result=null;
		  if(map!=null && map.containsKey(name)){
			 result=map.get(name);
		  }
		  else{
			  result=propertyMap.get(name);
		  }
		
	    return result;
	  }

	  public void setVariable(String name, Object value)
	  {
		  if(localVars.get()==null){
			  Map<String,Object> vars=  new LinkedHashMap<String,Object>();
			  vars.put(name, value);
			  localVars.set(vars);
		  }
		  else{
			  localVars.get().put(name, value);
		  }
	  }

	  public Map getVariables() {
		  if(localVars.get()==null){
			  return new LinkedHashMap();
		  }
	      
	    return localVars.get();
	  }
	  
	  public void clearVariables(){
		  localVars.remove();
	  }

	  public Object getProperty(String property)
	  {
	    return propertyMap.get(property);
	  }

	  public void setProperty(String property, Object newValue)
	  {
		  propertyMap.put(property, newValue);
	  }
}
