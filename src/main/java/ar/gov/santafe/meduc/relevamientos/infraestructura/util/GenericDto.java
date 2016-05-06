package ar.gov.santafe.meduc.relevamientos.infraestructura.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GenericDto {

	private Map<String, Object> atributos = new HashMap<String, Object>();
	
	public GenericDto(){
		 
	}
	public GenericDto(Object[] args){
		for (int i=0; i< args.length ;i++){
			this.atributos.put("arg"+i, args[i]);
		}
	}
	public GenericDto(String[] names,Object[] args){
		for (int i=0; i< args.length ;i++){
			if (names==null || names.length<i+1){
				this.atributos.put(i+"", args[i]);	
			} else {
				this.atributos.put(names[i], args[i]);
			}
		}
	}
	public GenericDto(Map<String, Object> args){
			atributos = args;
	}
	
	public static List<GenericDto> asGenericDtoList(String[] names,List<Object[]> objectArrayList){
		List<GenericDto> genericDtoList = new ArrayList<GenericDto>();
		for (Object[] unObjectArray:objectArrayList){
			genericDtoList.add(new GenericDto(names,unObjectArray));
		}
		return genericDtoList;
		
	}

	public void add(String name, Object atributo) {
		this.atributos.put(name, atributo);
	}

	public Object get(String atributo) {
		return this.atributos.get(atributo);
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		final String QUOT = "\"";
		sb.append("{");
		if( this.atributos != null) {
			int count=0;
			for(String clave : (Set<String>) this.atributos.keySet()){
				Object valor = atributos.get(clave); 
				sb.append(QUOT);
				sb.append(clave);
				sb.append(QUOT);
				sb.append(":");
				if (!isString(valor)){
					sb.append(valor);
				} else {
					sb.append(QUOT);
					sb.append(valor);
					sb.append(QUOT);
				}

				if (++count<atributos.keySet().size()){
					sb.append(",");
				}
			}
			sb.append("}");
		}
		return sb.toString();
	}
	private boolean isString(Object o){
		return o instanceof String;
		
	}
	
	public Map<String, Object> getA(){
		return atributos;
	}
	public void setA(Map<String, Object> a){
		atributos = a;
	}
	
}
