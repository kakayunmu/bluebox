package pers.kakayunmu.bluebox.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.kakayunmu.bluebox.entity.Goods;
import pers.kakayunmu.bluebox.model.GoodsModel;
import pers.kakayunmu.bluebox.model.common.RetModel;
import pers.kakayunmu.bluebox.repositorys.GoodsRepository;

@RestController
@RequestMapping("/api/goods")
@Slf4j
public class GoodsController {

    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private Authorization authorization;

//    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
//    public Object get(@PathVariable(value = "id")long id){
//        return  goodsRepository.getGoodsByIdAndcAndCreateBy(id,authorization.getMember().getId());
//    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Object save(@RequestBody GoodsModel goodsModel){
        Goods goods=new Goods();
        BeanUtils.copyProperties(goodsModel,goods);
        return  new RetModel(0,"保存成功");
    }
}
