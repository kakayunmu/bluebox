package pers.kakayunmu.bluebox.controller;

import pers.kakayunmu.bluebox.entity.Lable;
import pers.kakayunmu.bluebox.repositorys.LableRepository;

import java.util.ArrayList;

/**
 * 标签管理类
 */
public class LableController {


    /**
     * 新建用户时初始化标签
     * @param memberId 用户Id
     * @param lableRepository 标签Repository
     */
    public static void InitLable(long memberId, LableRepository lableRepository) {
        long count = lableRepository.countLableByCreateBy(memberId);
        ArrayList<Lable> lables = new ArrayList();
        if (count == 0l) {
            lables.add(new Lable("衣服", "备注", "default", 1));
            lables.add(new Lable("玩具", "备注", "default", 1));
        }
        lableRepository.save(lables);
        System.out.println(count);
    }
}
