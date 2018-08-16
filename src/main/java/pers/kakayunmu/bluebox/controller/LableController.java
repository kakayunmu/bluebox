package pers.kakayunmu.bluebox.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.kakayunmu.bluebox.entity.Lable;
import pers.kakayunmu.bluebox.model.LableModel;
import pers.kakayunmu.bluebox.model.common.RetDataModel;
import pers.kakayunmu.bluebox.model.common.RetModel;
import pers.kakayunmu.bluebox.repositorys.LableRepository;
import pers.kakayunmu.bluebox.util.JacksonUtil;

import java.util.ArrayList;
import java.util.UUID;

/**
 * 标签管理类
 */
@RestController
@RequestMapping(value = "/api/lable")
@Slf4j
public class LableController {

    @Autowired
    private LableRepository lableRepository;

    /**
     * 新建用户时初始化标签
     * @param memberId 用户Id
     * @param lableRepository 标签Repository
     */
    public static void InitLable(String memberId, LableRepository lableRepository) {
        long count = lableRepository.countLableByCreateBy(memberId);
        ArrayList<Lable> lables = new ArrayList();
        if (count == 0l) {
            lables.add(new Lable("衣服", "备注", "default", 1));
            lables.add(new Lable("玩具", "备注", "default", 1));
        }
        lableRepository.save(lables);
        System.out.println(count);
    }

    /**
     * 通过用户ID 获取标签列表
     * @param memberId
     * @return
     */
    @RequestMapping(value ="/getByMember/{memberId}",method = RequestMethod.GET)
    public Object getByMember(@PathVariable(value = "memberId")String memberId){
        return new RetDataModel(0,"获取数据成功", lableRepository.findLablesByCreateBy(memberId));
    }

    /**
     * 保存标签
     * @param lableModel
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Object save(@RequestBody LableModel lableModel){
        Lable lable=lableRepository.findOne(lableModel.getId());
        if(lable==null) {
            lable = new Lable();
        }
        BeanUtils.copyProperties(lableModel,lable);
        System.out.println("======>"+JacksonUtil.toJson(lable));
        lableRepository.save(lable);
        return  new RetModel(0,"保存标签成功");
    }


}
