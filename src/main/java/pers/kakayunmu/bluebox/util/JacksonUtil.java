package pers.kakayunmu.bluebox.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * Jackson 工具类
 */
@Slf4j
public final class JacksonUtil {
    private static ObjectMapper objectMapper;

    static {
        objectMapper=new ObjectMapper();
    }

    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     * (1)转换为普通JavaBean：readValue(json,Student.class)
     * (2)转换为List,如List<Student>,将第二个参数传递为Student[].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
     * @param jsonStr
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T readValue(String jsonStr,Class<T> valueType){
        try{
            return  objectMapper.readValue(jsonStr,valueType);
        }catch (Exception ex) {
            log.warn("json 字符串转对象发生异常,详细：{}", ex.getMessage());
        }finally {
            return  null;
        }
    }

    /**
     * json数组转List
     * @param jsonStr
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T readValue(String jsonStr, TypeReference<T> valueType){
        try{
            return objectMapper.readValue(jsonStr,valueType);
        }catch (Exception ex){
            log.warn("json 字符串转对象发生异常,详细：{}", ex.getMessage());
        }finally {
            return  null;
        }
    }

    /**
     * 对象转字符串
     * @param object
     * @return
     */
    public static String toJson(Object object){
        try{
            return objectMapper.writeValueAsString(object);
        }catch (Exception ex){
            log.warn("对象转字符串发生异常,详细：{}", ex.getMessage());
        }finally {
            return  null;
        }
    }
}
