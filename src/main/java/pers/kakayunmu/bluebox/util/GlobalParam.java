package pers.kakayunmu.bluebox.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import pers.kakayunmu.bluebox.model.common.GlobalParamValue;

import javax.annotation.PreDestroy;
import java.io.*;
import java.util.*;

@Slf4j
@Component
public class GlobalParam {
    private static final Map<String, GlobalParamValue> MAP;
    private static final int EXPIRYMIN = 30;

    static {
        MAP = new HashMap();
        Map<String, GlobalParamValue> stringGlobalParamValueMap = replyMap();
        if(stringGlobalParamValueMap!=null){
            log.info("从缓存文件中加载到{}条数据",stringGlobalParamValueMap.size());
            MAP.putAll(stringGlobalParamValueMap);
        }
    }

    static Map<String, GlobalParamValue> replyMap() {
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        log.info("缓存文件位置" + path);
        String filePath = String.format("%s/.bluebox-cache", path);
        File file = new File(filePath);
        try {
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                return (Map<String, GlobalParamValue>) objectInputStream.readObject();
            } else {
                log.info("缓存文件不存在");
                return null;
            }
        }catch (ClassNotFoundException ex){
            log.error("恢复缓存文件发生异常", ex);
            return  null;
        }
        catch (IOException ex) {
            log.error("恢复缓存文件发生异常", ex);
            return  null;
        }
    }

    @PreDestroy
    public void destory() {
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        log.info("缓存文件位置" + path);
        String filePath = String.format("%s/.bluebox-cache", path);
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(MAP);
            fileOutputStream.close();
        } catch (IOException ex) {
            log.error("boot 关闭序列化缓存到文件失败", ex);
        }
    }

    /**
     * 添加对象
     *
     * @param key
     * @param object
     * @param <T>
     */
    public <T> void push(String key, T object) {
        push(key, object, EXPIRYMIN);
    }

    /**
     * 添加对象
     *
     * @param key
     * @param object
     * @param expiryMin
     * @param <T>
     */
    public <T> void push(String key, T object, int expiryMin) {
        removeExpiryObj();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, expiryMin);
        log.info("pull 对象到全局容器中；key：{} 对象类型：{} 过期时间：{}", key, object.getClass(), nowTime.getTime());
        MAP.put(key, new GlobalParamValue(object, nowTime.getTime(), expiryMin));
    }

    /**
     * 获取对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T get(String key) {
        if (key != null && MAP.containsKey(key)) {
            Calendar nowTime = Calendar.getInstance();
            GlobalParamValue<T> ret = MAP.get(key);
            if (ret.getExpiryTime().before(nowTime.getTime())) {
                removeExpiryObj();
                return null;
            }
            nowTime.add(Calendar.MINUTE, ret.getExpiryMin());
            ret.setExpiryTime(nowTime.getTime());
            return ret.getParame();
        } else {
            return null;
        }
    }

    /**
     * 删除对象
     *
     * @param key
     */
    public void remove(String key) {
        if (MAP.containsKey(key)) {
            MAP.remove(key);
        }
    }

    /**
     * 移除过期的对象
     */
    private void removeExpiryObj() {
        List<String> keys = new ArrayList();
        Date now = new Date();
        for (Map.Entry<String, GlobalParamValue> gp : MAP.entrySet()) {
            if (gp.getValue().getExpiryTime().before(now)) {
                keys.add(gp.getKey());
            }
        }
        for (String key : keys) {
            log.info("从全局容器里移除过期对象；key：{}", key);
            MAP.remove(key);
        }
    }


}
