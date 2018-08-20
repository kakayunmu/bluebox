package pers.kakayunmu.bluebox.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pers.kakayunmu.bluebox.entity.Lable;
import pers.kakayunmu.bluebox.entity.Member;
import pers.kakayunmu.bluebox.entity.vo.LableVO;
import pers.kakayunmu.bluebox.model.LableModel;
import pers.kakayunmu.bluebox.model.common.RetDataModel;
import pers.kakayunmu.bluebox.model.common.RetModel;
import pers.kakayunmu.bluebox.repositorys.LableRepository;
import pers.kakayunmu.bluebox.util.JacksonUtil;

import java.util.ArrayList;

/**
 * 标签管理类
 */
@RestController
@RequestMapping(value = "/api/lable")
@Slf4j
public class LableController {

    @Autowired
    private Authorization authorization;
    @Autowired
    private LableRepository lableRepository;

    /**
     * 新建用户时初始化标签
     *
     * @param memberId        用户Id
     * @param lableRepository 标签Repository
     */
    public static void InitLable(String memberId, LableRepository lableRepository) {
        long count = lableRepository.countLableByCreateBy(memberId);
        ArrayList<Lable> lables = new ArrayList();
        if (count == 0l) {
            log.info("新用户初始化默认标签");
            lables.add(new Lable("衣服", "备注", "default", 1));
            lables.add(new Lable("玩具", "备注", "default", 1));
        }
        lableRepository.save(lables);
    }

    /**
     * 获取用户标签列表
     *
     * @return
     */
    @RequestMapping(value = {"/getList/{page}","/getList/{page}/{size}"}, method = RequestMethod.GET)
    public Object getList(@PathVariable(value = "page")int page,@PathVariable(value = "size",required = false)int size) {
        Member member = authorization.getMember();
        if(size==0){// 如果未指定每页条数则默认每页10条数据
            size=10;
        }
        Pageable pageable=new PageRequest(page,size);
        Page<LableVO> ret= lableRepository.findLablesByCreateByWithGoodCount(pageable,member.getId());
        return new RetDataModel(0, "获取数据成功", ret);
    }

    /**
     * 通过标签ID 获取标签信息
     *
     * @param lableId
     * @return
     */
    @RequestMapping(value = "/get/{lableId}", method = RequestMethod.GET)
    public Object get(@PathVariable(value = "lableId") String lableId) {
        Lable lable = lableRepository.findOne(lableId);
        LableModel lableModel = new LableModel();
        BeanUtils.copyProperties(lable, lableModel);
        return new RetDataModel(0, "获取数据成功", lableModel);
    }

    /**
     * 保存标签
     *
     * @param lableModel
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(@RequestBody LableModel lableModel) {
        Lable lable = lableRepository.findOne(lableModel.getId());
        if (lable == null) {
            lable = new Lable();
        }
        BeanUtils.copyProperties(lableModel, lable);
        System.out.println("======>" + JacksonUtil.toJson(lable));
        lableRepository.save(lable);
        return new RetModel(0, "保存标签成功");
    }


}
