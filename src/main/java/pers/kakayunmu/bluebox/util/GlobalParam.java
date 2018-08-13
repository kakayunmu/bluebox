package pers.kakayunmu.bluebox.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.kakayunmu.bluebox.model.common.GlobalParamValue;

import java.util.*;

@Slf4j
@Component
public class GlobalParam {
    private static final Map<String, GlobalParamValue> MAP;
    private static final int EXPIRYMIN = 30;

    static {
        MAP = new HashMap();
    }

    /**
     * 添加对象
     * @param key
     * @param object
     * @param <T>
     */
    public <T> void push(String key,T object){
        push(key,object,EXPIRYMIN);
    }

    /**
     * 添加对象
     * @param key
     * @param object
     * @param expiryMin
     * @param <T>
     */
    public <T> void push(String key, T object, int expiryMin) {
        removeExpiryObj();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, expiryMin);
        log.info("pull 对象到全局容器中；key：{} 对象类型：{} 过期时间：{}",key,object.getClass(),nowTime.getTime());
        MAP.put(key, new GlobalParamValue(object, nowTime.getTime(),expiryMin));
    }

    /**
     * 获取对象
     * @param key
     * @param <T>
     * @return
     */
    public <T> T get(String key) {
        if (key!=null&& MAP.containsKey(key)) {
            Calendar nowTime = Calendar.getInstance();
            GlobalParamValue<T> ret = MAP.get(key);
            if(ret.getExpiryTime().before(nowTime.getTime())){
                removeExpiryObj();
                return  null;
            }
            nowTime.add(Calendar.MINUTE,ret.getExpiryMin());
            ret.setExpiryTime(nowTime.getTime());
            return ret.getParame();
        } else {
            return null;
        }
    }

    /**
     * 删除对象
     * @param key
     */
    public void remove(String key){
        if(MAP.containsKey(key)){
            MAP.remove(key);
        }
    }

    /**
     * 移除过期的对象
     */
    private void removeExpiryObj(){
        List<String > keys=new ArrayList();
        Date now=new Date();
        for (Map.Entry<String,GlobalParamValue> gp:MAP.entrySet()){
            if(gp.getValue().getExpiryTime().before(now)){
                keys.add(gp.getKey());
            }
        }
        for(String key:keys){
            log.info("从全局容器里移除过期对象；key：{}",key);
            MAP.remove(key);
        }
    }
}
