package com.doshr.xmen.backend.common.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.SimpleType;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * copy from other project
 */
public class JsonUtil {
	
	public static final String PATH_SIGN = "/";
    private static final String JSON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    public static ObjectMapper getMapper() {
        return getMapper(true);
    }

    private static String fixIllegalChar(String jsonData) {
        return StringUtils.replaceChars(jsonData, "\t\n\r", " ");
    }

    
    private static ObjectMapper getMapper(boolean autoCloseStream) {
        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat(JSON_DATE_FORMAT);

//        mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);
        mapper.setDateFormat(df);
 //        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        
        mapper.getJsonFactory().configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, autoCloseStream);

        return mapper;
    }

    public static JsonNode parseString(String jsonData) {
        try {
            return getMapper().readTree(jsonData);
        } catch (Exception e) {
            LOG.error("JsonUtil.parseString error. " + e.getMessage());
            return null;
        }
    }

    /**
     * Convert string to object.
     */
    public static <T> T toBean(String jsonData, Class<? extends T> objClass) {
    	if(StringUtils.isEmpty(jsonData)){
    		return null;
    	}
        try {
            return getMapper().readValue(fixIllegalChar(jsonData), objClass);
        } catch (Exception e) {
            LOG.error("JsonUtil.toBean error.jsonData: " + jsonData, e);
            return null;
        }
    }

    public static <T> T toBean(JsonNode jn, Class<? extends T> objClass) {
        if (jn == null) {
            return null;
        }

        try {
            return getMapper().readValue(jn.traverse(), objClass);
        } catch (Exception e) {
            LOG.error("JsonUtil.toBean error. " + e.getMessage());
            return null;
        }
    }

    /**
     * Convert string to object.
     */
    public static <T> T toBean(String jsonData, String key,
            Class<? extends T> objClass) {
        try {
            JsonNode jn = getMapper().readTree(fixIllegalChar(jsonData));
            return toBean(jn.get(key), objClass);
        } catch (Exception e) {
            LOG.error("JsonUtil.toBean error. Key: " + key + "\n" + e.getMessage());
            return null;
        }
    }

    public static Map<String, Object> toJsonMap(Object obj) {
        Map<String, Object> result = new HashMap<String, Object>();

        for (Method m : obj.getClass().getMethods()) {
            if (Modifier.isStatic(m.getModifiers()) || !Modifier.isPublic(m.getModifiers())) {
                continue;
            }

            String methodName = m.getName();
            Class<?> clazz = m.getReturnType();

            if (clazz.getName().equalsIgnoreCase(Class.class.getName())) {
                continue;
            }

            if (clazz.getName().toLowerCase().startsWith(Method.class.getPackage().getName() + ".")) {
                continue;
            }

            if (methodName.startsWith("get") && m.getParameterTypes().length == 0) {
                String key = methodName.substring(3);

                if (StringUtils.isNotEmpty(key)) {
                    Object value = null;

                    try {
                        value = m.invoke(obj);
                    } catch (Exception e) {
                        LOG.error("method call failed.", e);
                    }

                    if (value != null) {
                        result.put(key.substring(0, 1).toLowerCase() + key.substring(1), value);
                    }
                }
            }
        }

        return result;
    }

    public static void toJsonString(Object obj, Writer writer) {
        try {
            getMapper(false).writeValue(writer, obj);
        } catch (Exception e) {
            LOG.error("JsonUtil.toJsonString error:" + e.getMessage());
        }
    }
    
    /**
     * Convert object to string.
     */
    public static String toJsonString(Object obj) {
        StringWriter sw = new StringWriter();
        try {
            getMapper().writeValue(sw, obj);
            return sw.toString();
        } catch (Exception e) {
            LOG.error("JsonUtil.toJsonString error:" + e.getMessage());
            return null;
        }
    }

    /**
     * Convert the first letter to upper case for specific key.
     */
    public static String getRealKey(String key) {
        if (StringUtils.isEmpty(key)) {
            return key;
        }
        return key.substring(0, 1).toLowerCase() + key.substring(1);
    }

    public static String getJsonString(String json, String key) {
        try {
            JsonNode node = getMapper().readTree(fixIllegalChar(json)).get(key);

            if (node == null) {
                return null;
            } else {
                return node.toString();
            }
        } catch (Exception e) {
            LOG.error("JsonUtil.getJsonString error. Data: Key: " + key + "\n" + e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("deprecation")
    public static String getJsonValue(String json, String key) {
        try {
            return getMapper().readTree(fixIllegalChar(json)).get(key).getValueAsText();
        } catch (Exception e) {
            LOG.error("JsonUtil.getJsonValue error. Key: " + key + "\n" + e.getMessage());
            return null;
        }
    }

    public static boolean containsKey(String json, String key) {
        try {
            return getMapper().readTree(fixIllegalChar(json)).get(key).toString() != null;
        } catch (Exception e) {
            LOG.error("JsonUtil.containsKey error. Key: " + key + "\n" + e.getMessage());
            return false;
        }
    }

    public static <T> List<T> toList(String jsonData, String key, Class<? extends T> objClass) {
        try {
            JsonNode jn = getMapper().readTree(fixIllegalChar(jsonData));
            String str = jn.get(key).toString();
            return toList(str, objClass);
        } catch (Exception e) {
            LOG.error("JsonUtil.toList error. Key: " + key + "\n"
                    + e.getMessage());
            return null;
        }
    }

    public static <T> List<T> toList(String jsonData, Class<? extends T> objClass) {
        if (StringUtils.isEmpty(jsonData)) {
            return null;
        }

        try {
            try {
                return getMapper().readValue(jsonData, CollectionType.construct(List.class, SimpleType.construct(objClass)));
            } catch (IllegalArgumentException e) {
                return getMapper().readValue(jsonData, new TypeReference<ArrayList<T>>() {});
            }
        } catch (Exception e) {
            LOG.error("JsonUtil.toList error." + e.getMessage());
            return null;
        }
    }

    public static <T> List<T> toList(JsonNode jn, String key, Class<? extends T> objClass) {
        if (jn == null) {
            return null;
        } else {
            return toList(jn.get(key), objClass);
        }
    }

    public static <T> List<T> toList(JsonNode jn, Class<? extends T> objClass) {
        if (jn == null || !jn.isArray()) {
            return null;
        }

        try {
            JsonParser jp = jn.traverse();

            try {
                return getMapper().readValue(jp, CollectionType.construct(List.class, SimpleType.construct(objClass)));
            } catch (IllegalArgumentException e) {
                return getMapper().readValue(jp, new TypeReference<ArrayList<T>>() {});
            }
        } catch (Exception e) {
            LOG.error("JsonUtil.toList error." + e.getMessage());
            return null;
        }
    }

    public static List<String> toJsonStringList(String jsonData) {
        try {
            JsonNode jn = getMapper().readTree(fixIllegalChar(jsonData));

            if (jn.isArray()) {
                List<String> retList = new ArrayList<String>();

                for (int i = 0; i < jn.size(); i++) {
                    retList.add(toJsonString(jn.get(i)));
                }

                return retList;
            } else {
                return Arrays.asList(jsonData);
            }
        } catch (Exception e) {
            LOG.error("JsonUtil.toList error. " + e.getMessage());
            return null;
        }
    }

    public static Map<String, String> extractJsonStringList(String str, String...fields) {
        Map<String, String> resMap = new HashMap<String, String>();

        if (fields != null && fields.length > 0) {
            try {
                JsonNode jn = getMapper().readTree(fixIllegalChar(str));

                for (String field : fields) {
                    resMap.put(field, jn.get(field).toString());
                }
            } catch (Exception e) {
                LOG.error("JsonUtil.extractJsonSring error. " + e.getMessage());
            }
        }

        return resMap;
    }
    
    public static String extractJsonSring(String str, String... fields) {
        if (fields == null || fields.length == 0) {
            return "";
        }

        StringWriter sw = new StringWriter();
        try {
            JsonNode jn = getMapper().readTree(fixIllegalChar(str));
            HashMap<String, Object> resMap = new HashMap<String, Object>();
            for (String field : fields) {
                resMap.put(field, jn.get(field));
            }

            getMapper().writeValue(sw, resMap);
            return sw.toString();
        } catch (Exception e) {
            LOG.error("JsonUtil.extractJsonSring error. " + e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toObjectMap(String str) {
        try {
            return getMapper().readValue(str, Map.class);
        } catch (Exception e) {
            LOG.error("JsonUtil.toObjectMap error. " + e.getMessage());
            return null;
        }
    }

    public static Map<String, String> toStringMap(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        Map<String, String> map = new HashMap<String, String>();
        JsonFactory f = new JsonFactory();
        JsonParser jp = null;

        try {
            jp = f.createJsonParser(str);
            jp.nextToken(); // will return JsonToken.START_OBJECT (verify?)
            while (jp.nextToken() != JsonToken.END_OBJECT) {
                String fieldname = jp.getCurrentName();
                jp.nextToken(); // move to value, or START_OBJECT/START_ARRAY
                if (map.containsKey(fieldname)) {
                    LOG.error("the json string contain dupicate field name[" + fieldname + "]");
                    return null;
                } else {
                    map.put(fieldname, jp.getText());
                }
            }
        } catch (Exception e) {
            LOG.error("JsonUtil.toMap error. "
                    + e.getMessage());
            // throw e;
        } finally {
            if (jp != null) {
                try {
                    jp.close();
                } catch (IOException e) {
                    LOG.error("JsonUtil.toMap error:" + e.getMessage());
                }
            }
        }
        return map;
    }

    public static String removeKeys(String str, List<String> fields) {
        if (fields == null || fields.size() == 0 || StringUtils.isEmpty(str)) {
            return str;
        }

        try {
            JsonNode jn = getMapper().readTree(fixIllegalChar(str));
            Iterator<String> iter = jn.getFieldNames();
            HashMap<String, Object> resMap = new HashMap<String, Object>();
            while (iter.hasNext()) {
                String fn = iter.next();
                if (!fields.contains(fn)) {
                    resMap.put(fn, jn.get(fn));
                }
            }

            return toJsonString(resMap);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(String.format(
                    "failed to removeKeys,data=[%s]", str));
        }
    }

    private static Map<String, Object> removeJsonNodeFieldByPath(JsonNode jn, List<String> fieldPaths) throws Exception {
        Set<String> fieldsToProcess = new HashSet<String>();
        Map<String, List<String>> nextFieldPathMap = new HashMap<String, List<String>>();

        for (String fieldPath : fieldPaths) {
            int idx = fieldPath.indexOf(PATH_SIGN);

            if (idx >= 0) {
                String fieldToProcess = fieldPath.substring(0, idx);
                List<String> nextFieldPaths = nextFieldPathMap.get(fieldToProcess);

                if (nextFieldPaths == null) {
                    nextFieldPathMap.put(fieldToProcess, nextFieldPaths = new ArrayList<String>());
                }

                nextFieldPaths.add(fieldPath.substring(idx + 1));
            } else {
                fieldsToProcess.add(fieldPath);
            }
        }

        Iterator<String> iter = jn.getFieldNames();
        Map<String, Object> resMap = new HashMap<String, Object>();

        while (iter.hasNext()) {
            String fn = iter.next();

            if (fieldsToProcess.contains(fn)) {
                continue;
            }

            JsonNode fieldJN = jn.get(fn);

            if (nextFieldPathMap.containsKey(fn)) {
                if (fieldJN.isArray()) {
                    List<Object> resList = new ArrayList<Object>();

                    for (int i = 0; i < fieldJN.size(); i++) {
                        resList.add(removeJsonNodeFieldByPath(fieldJN.get(i), nextFieldPathMap.get(fn)));
                    }

                    resMap.put(fn, resList);
                } else if (fieldJN.isObject()) {
                    resMap.put(fn, removeJsonNodeFieldByPath(fieldJN, nextFieldPathMap.get(fn)));
                } else {
                    resMap.put(fn, fieldJN);
                }
            } else {
                resMap.put(fn, fieldJN);
            }
        }

        return resMap;
    }

    public static List<JsonNode> getNodeByPath(JsonNode jn, String path) {
        if (jn == null || StringUtils.isEmpty(path)) {
            return null;
        }

        List<JsonNode> result = new ArrayList<JsonNode>();

        if (jn.isArray()) {
            for (int i = 0; i < jn.size(); i++) {
                result.add(jn.get(i));
            }
        } else {
            result.add(jn);
        }

        for (String p : StringUtils.split(path, PATH_SIGN)) {
            List<JsonNode> newResult = new ArrayList<JsonNode>();

            for (JsonNode jn1 : result) {
                jn1 = jn1.get(p);

                if (jn1 != null) {
                    if (jn1.isArray()) {
                        for (int i = 0; i < jn1.size(); i++) {
                            newResult.add(jn1.get(i));
                        }
                    } else {
                        newResult.add(jn1);
                    }
                }
            }

            result = newResult;

            if (result == null) {
                break;
            }
        }

        return result;
    }

    public static boolean getBooleanValue(JsonNode jn, String fieldName, boolean defaultValue) {
        jn = jn != null ? jn.get(fieldName) : null;

        return jn != null ? jn.getBooleanValue() : defaultValue;
    }

    public static int getIntegerValue(JsonNode jn, String fieldName, int defaultValue) {
        jn = jn != null ? jn.get(fieldName) : null;

        return jn != null ? jn.getIntValue() : defaultValue;
    }

    public static long getLongValue(JsonNode jn, String fieldName, long defaultValue) {
        jn = jn != null ? jn.get(fieldName) : null;

        return jn != null ? jn.getLongValue() : defaultValue;
    }

    public static List<JsonNode> getArrayValue(JsonNode jn, String fieldName) {
        List<JsonNode> items = new ArrayList<JsonNode>();

        jn = jn != null ? jn.get(fieldName) : null;

        if (jn != null && jn.isArray()) {
            for (int i = 0; i < jn.size(); i++) {
                items.add(jn.get(i));
            }
        }

        return items;
    }
}
