package com.jason.jlh.common.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * @title: PhoneNumberSerializer
 * @package: com.jason.jlh.common.serialization
 * @description: 电话号码序列化器
 * @author: huyongjun
 * @date: 2020/5/16
 * @version: v1.0
 */
public class PhoneNumberSerializer extends StdScalarSerializer<String> {

    public PhoneNumberSerializer() {
        super(String.class);
    }

    /**
     * 序列化方法
     *
     * @param: [str, jsonGenerator, serializerProvider]
     * @return: void
     * @author: huyongjun
     * @date: 2020/5/16
     */
    @Override
    public void serialize(String str, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        //进来时一定不为null
        String result;
        int len = str.length();
        if (len == 11) {
            // 159****7689
            result = str.substring(0, 3) + "****" + str.substring(8);
        } else if (len > 4) {
            // 8190****
            result = StringUtils.leftPad(str.substring(len - 4), len, "*");
        } else {
            //原样输出
            result = str;
        }
        jsonGenerator.writeString(result);
    }
}
