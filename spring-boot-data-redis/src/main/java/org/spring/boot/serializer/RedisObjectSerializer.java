package org.spring.boot.serializer;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * 自定义序列化
 * 
 * @author zhangzhigang
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {

	private Converter<Object, byte[]> serializer = new SerializingConverter();
	private Converter<byte[], Object> deserializer = new DeserializingConverter();
	
	static final byte[] EMPTY_ARRAY = new byte[0];
	
	@Override
	public byte[] serialize(Object obj) throws SerializationException {
		if (null == obj) {
			return EMPTY_ARRAY;
		}
		try {
			return serializer.convert(obj);
		} catch (Exception e) {
			return EMPTY_ARRAY;
		}
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		if (isEmpty(bytes)) {
			return null;
		}
		try {
			return deserializer.convert(bytes);
		} catch (Exception e) {
			throw new SerializationException("Can't deserialize", e);
		}
	}
	
	private boolean isEmpty(byte[] data) {
		return null == data || data.length == 0;
	}
}