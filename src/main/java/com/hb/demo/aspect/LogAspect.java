package com.hb.demo.aspect;

import com.hb.demo.common.annotation.RequestLog;
import com.hb.demo.entity.Log;
import com.hb.demo.mapper.LogMapper;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
@AllArgsConstructor
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    LogMapper logMapper;

    // 配置织入点 (可以根据注解配置，也可以根据具体方法)

    /**
     * 配置织入点
     * 1.根据注解配置切入点：@annotation(com.hb.demo.common.annotation.RequestLog)
     * 2.根据具体方法配置切入点：execution(public * com.hb.demo.controller.TestController.add*(..))
     * 3.配合使用：@Pointcut("execution(* com.hb..controller..*.*(..))&&@annotation(com.hb.demo.common.annotation.RequestLog)")
     */
    @Pointcut("@annotation(com.hb.demo.common.annotation.RequestLog)")
    public void logPointCut() { }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()")
    public void doAfterReturning(JoinPoint joinPoint) {
        System.out.println("处理完请求后执行");
        handleLog(joinPoint, null);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        System.out.println("出现异常");
        handleLog(joinPoint, e);
    }


    /**
     * 记录日志
     * @param joinPoint
     * @param e
     */
    protected void handleLog(final JoinPoint joinPoint, final Exception e) {
        try {
            // 获得注解
            RequestLog requestLog = getAnnotationLog(joinPoint);
            if (requestLog == null) {
                return;
            }
            if (e != null) {
                System.out.println("expection:"+e.getMessage());
            }else {
                log.info("==后置通知增强==");
            }
            // 设置类名称
            String className = joinPoint.getTarget().getClass().getName();
            // 设置方法名称
            String methodName = joinPoint.getSignature().getName();
           log.info("");
            // 处理设置注解上的参数
            String interfaceName = requestLog.interfaceName();
            String requestUrl = requestLog.requestUrl();
            boolean saveRequestData = requestLog.isSaveRequestData();
            if(saveRequestData) {

            }
            String model = requestLog.model();
            Log log = Log.builder().createBy("测试").createDate(LocalDateTime.now()).requestUrl(interfaceName).build();
            logMapper.insert(log);

        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }
    /**
     * 是否存在注解，如果存在就获取
     */
    private RequestLog getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(RequestLog.class);
        }
        return null;
    }
}