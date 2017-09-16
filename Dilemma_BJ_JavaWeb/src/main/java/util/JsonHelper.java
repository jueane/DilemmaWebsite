package util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {

	public static byte[] toJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		// 字段值为null时不序列化
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		// 忽略未知字段
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			return objectMapper.writeValueAsString(object).getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T parseJson(String json, Class<T> clz)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		// 字段值为null时不序列化
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		// 忽略未知字段
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return objectMapper.readValue(json, clz);
	}

}
