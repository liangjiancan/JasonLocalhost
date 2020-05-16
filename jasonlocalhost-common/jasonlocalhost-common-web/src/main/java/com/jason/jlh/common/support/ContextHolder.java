package com.jason.jlh.common.support;

import com.jason.jlh.common.pojo.IGlobalParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @title: ContextHolder
 * @package: com.jason.jlh.common.support
 * @description: 上下文信息载体
 * @author:
 * @date: 2020/5/14
 * @version: v1.0
 */
public class ContextHolder implements IGlobalParam {

    private static final ThreadLocal<ContextHolder> LOCAL = ThreadLocal.withInitial(() -> new ContextHolder());

    private static final int MAX_IP_LENGTH = 30;
    private static final String HEADER_X_FORWAREDED_FOR = "x-forwarded-for";

    /**
     * 请求是否来自gateway
     */
    private boolean gateway = false;

    /**
     * 获取当前实例
     *
     * @param: []
     * @return: com.jason.jlh.common.support.ContextHolder
     * @author: huyongjun
     * @date: 2020/5/14
     */
    public static ContextHolder getContext() {
        return LOCAL.get();
    }

    /**
     * 设置请求是否来自于gateway
     *
     * @param: [gateway]
     * @return: void
     * @author: huyongjun
     * @date: 2020/5/14
     */
    public void setGateway(boolean gateway) {
        this.gateway = gateway;
    }

    /**
     * 清理缓存数据
     *
     * @param: []
     * @return: void
     * @author: huyongjun
     * @date: 2020/5/14
     */
    public void clear() {
        this.gateway = false;
    }

    /**
     * 获取用户名
     *
     * @param: []
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/3
     */
    @Override
    public String getUserName() {
        return null;
    }

    /**
     * 获取用户ID
     *
     * @param: []
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/3
     */
    @Override
    public String getUserId() {
        return null;
    }

    /**
     * 获取用户会话标识，即userCode+":"+sessionId
     *
     * @param: []
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/3
     */
    @Override
    public String getUserSession() {
        return null;
    }

    /**
     * 获取用户会话ID
     *
     * @param: []
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/3
     */
    @Override
    public String getSessionId() {
        return null;
    }

    /**
     * 获取客户端IP
     *
     * @param: []
     * @return: java.lang.String
     * @author:
     * @date: 2020/5/14
     */
    @Override
    public String getClientIp() {
        try {
            //如果是Cache自动刷新调用获取上下文时，这里可能会有异常；异常则返回空的IP，
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            String xff = request.getHeader(HEADER_X_FORWAREDED_FOR);
            if (xff == null) {
                return request.getRemoteAddr();
            }
            //如果攻击者伪造x-forwarded-for头，slb会在原有xff头后面加逗号，再加真实ip
            //X-Forwarded-For: aaa.bbb.ccc, 10.95.18.1
            String[] arr = xff.split(",");
            int len = arr.length;
            String realIp;
            if (gateway) {
                //如果请求来自gateway，则IP会显示为 X-Forwarded-For: 10.113.40.232, 10.82.47.199
                realIp = arr[len - 2].trim();
            } else {
                //直接走SLB，则以最后一个座位IP
                realIp = arr[len - 1].trim();
            }
            if (realIp.length() > MAX_IP_LENGTH) {
                //控制一下大小，理论上不能超过255.255.255.255
                return realIp.substring(0, MAX_IP_LENGTH);
            } else {
                return realIp;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 是否管理员
     *
     * @param: []
     * @return: boolean
     * @author:
     * @date: 2020/5/3
     */
    @Override
    public boolean isAdmin() {
        return false;
    }

    /**
     * 获取额外数据
     *
     * @param: []
     * @return: java.util.Map<java.lang.String , java.lang.Object>
     * @author: huyongjun
     * @date: 2020/5/3
     */
    @Override
    public Map<String, Object> getData() {
        return null;
    }
}
