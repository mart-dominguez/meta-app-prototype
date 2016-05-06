package ar.gov.santafe.meduc.relevamientos.infraestructura.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module.Feature;
import java.util.HashMap;
import java.util.Iterator;

public class JsonMapper {

    private static ObjectMapper mapper;
    
    public static String toJson(Object toMap) {
        try {
            return getObjectMapper().writeValueAsString(toMap);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static <T> List<T> getList(String params, String field, Class<T> c) {
        List<T> lista = new ArrayList<T>();
        ObjectMapper mapper = getObjectMapper();
        List responseList = getObject(params, field, List.class);
        for (Object aMap : responseList) {
            lista.add(convert(aMap, c));
        }
        return lista;

    }

    public static <T> T convert(Object obj, Class<T> c) {
        try {
            ObjectMapper mapper = getObjectMapper();
            return mapper.convertValue(obj, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> convertList(List aList, Class<T> c) {
        List<T> lista = new ArrayList<T>();
        try {
            ObjectMapper mapper = getObjectMapper();
            for (Object aMap : aList) {
                lista.add(convert(aMap, c));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static <T> T getObject(String params, String field, Class<T> c) {
        try {
            ObjectMapper mapper = getObjectMapper();
            GenericDto requestDto = mapper.readValue(params, GenericDto.class);
            Object obj = requestDto.get(field);
            //return (T) obj;
            return mapper.convertValue(obj, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    public static <T> T readAs(String json, Class<T> c) {
        try { 
            ObjectMapper mapper = getObjectMapper();
             return mapper.readValue(json, c); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ObjectMapper getObjectMapper() {
        if (mapper!=null){
            return mapper;
        }
        mapper = new ObjectMapper();
        Hibernate4Module hm = new Hibernate4Module();
        hm.disable(Feature.USE_TRANSIENT_ANNOTATION);
        mapper.registerModule(hm);

        final String format = "yyyy-MM-dd";
        final DateFormat df = new SimpleDateFormat(format);
        df.setTimeZone(TimeZone.getDefault());
        mapper.setDateFormat(df);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
        //mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE, JsonTypeInfo.As.WRAPPER_OBJECT); 
        return mapper;

    }

    public static Map getMap(String params, String field, Class<?> keyClass, Class<?> valueClass) {
        try {
            ObjectMapper mapper = getObjectMapper();
            GenericDto requestDto = mapper.readValue(params, GenericDto.class);
            Object obj = requestDto.get(field);
            return (Map) obj;
            //Map genericMap = mapper.convertValue(obj, Map.class);
            //return convertMap(genericMap, keyClass, valueClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map convertMap(Map genericMap, Class<?> keyClass, Class<?> valueClass) {
        if (valueClass.equals(Long.class)) {
            for (Object aKey : genericMap.keySet()) {
                Long newVal = Long.parseLong(genericMap.get(aKey).toString());
                genericMap.put(aKey, newVal);
            }

        }
        return genericMap;
    }

    public static JsonNode getJsonNode(String json) {
        try {
            ObjectMapper mapper = getObjectMapper();
            JsonNode rootNode = mapper.readTree(json);
            return rootNode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> getJsonStringMap(String json, String path) {
        Map<String, String> retMap = new HashMap<String, String>();
        JsonNode rootNode = getJsonNode(json);
        JsonNode myNode = rootNode.path("a").path(path);
        Iterator<Map.Entry<String, JsonNode>> ite = myNode.fields();
        Map.Entry<String, JsonNode> blas;
        while (ite.hasNext()){
            blas = ite.next();
            retMap.put(blas.getKey(), blas.getValue().toString());
        }
        return retMap;
    }
}
