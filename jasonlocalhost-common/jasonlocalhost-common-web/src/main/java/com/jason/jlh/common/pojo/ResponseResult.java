package com.jason.jlh.common.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

/**
 * @title: ResponseResult
 * @package: com.jason.jlh.common.pojo
 * @description: 响应结果, 统一格式封装返回到前端的数据
 * @author: huyongjun
 * @date: 2020/5/14
 * @version: v1.0
 */
@Data
public class ResponseResult<T> extends BaseValueObject {

    private static final long serialVersionUID = 4923371979122327713L;

    /**
     * 发起请求的URL
     */
    private String url;

    /**
     * 操作是否成功标志
     */
    private boolean success;

    /**
     * 成功信息或失败的状态(代码)
     */
    private String status;

    /**
     * 状态对应的可读原因消息，尤其是失败时的
     */
    private String reason;

    /**
     * 结果数据。
     * <p>
     * 由于类型是可变的泛型，序列化时将类型标识也作为属性输出，这样反序列化时才可能实现多态识别。<br>
     * 相关资料在官方说明中也有提到，参考 <a
     * href="http://www.cowtowncoder.com/blog/archives/2010/03/entry_372.html">Polymorphic Type
     * Handling</a> 和 <a
     * href="http://wiki.fasterxml.com/JacksonPolymorphicDeserialization">Polymorphic
     * Deserialization</a>。<br>
     * 注意由于类型泛型在JVM运行时是不可见的，所以像<code>List&lt;Class&gt;</code>这类结果可能无法正确输出类型，可以转成数组
     * <code>Class[]</code>后再输出。
     */
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY)
    private T result;

    public ResponseResult() {
        this.success = true;
        this.status = HttpStatus.OK.toString();
    }

    public ResponseResult(T obj) {
        this();
        this.reason = HttpStatus.OK.getReasonPhrase();
        this.result = obj;
    }

    public ResponseResult(boolean success, String status, String reason, String url, T obj) {
        this();
        this.success = success;
        this.status = status;
        this.reason = reason;
        this.url = url;
        this.result = obj;
    }

    public ResponseResult(String status, String reason, String url) {
        this();
        this.success = false;
        this.status = status;
        this.reason = reason;
        this.url = url;
    }

    @JsonIgnore
    public String getStatusLine() {
        return StringUtils.isBlank(getReason()) ? getStatus() : String.format("%s %s", getStatus(), getReason());
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        setStatus(httpStatus.toString());
        setReason(httpStatus.getReasonPhrase());
    }

}
