package com.example.buildbaseframe.api.common.respond;

import com.example.buildbaseframe.api.user.controller.UserController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * <b>通用返回结果包装器</b>
 * <p>
 *     自动根据controller返回的内容自动包装为指定格式
 *     注意：如果在Controller的方法参数列表里有HttpServletResponse，且方法返回为void或null，会导致这个包装器失效
 * </p>
 *
 * @author lq
 * @version 1.0
 */
@Slf4j
//@RestControllerAdvice(basePackages = {"club.devhub.bbq.api.user.rest.controller", "club.devhub.bbq.api.wall.rest.controller"})
@RestControllerAdvice(assignableTypes = {UserController.class})
public class CommonResultWrapper implements ResponseBodyAdvice<Object> {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 设置这个“包装器”是否启用。return true表示启用。
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    /**
     * 自定义包装方法，根据Controller层返回的内容不同，有不同的包装方法。
     * 不过最终要给前端返回的是标准返回模板————CommonResult
     */
    @Override
    @SneakyThrows(JsonProcessingException.class)
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null) {
            return CommonResult.success();
        }
        if (body instanceof String) {
            return objectMapper.writeValueAsString(CommonResult.success(body));
        }
        if (body instanceof CommonResult) {
            return body;
        }
        return CommonResult.success(body);
    }


}
