package pers.kakayunmu.bluebox.repositorys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pers.kakayunmu.bluebox.entity.Lable;
import pers.kakayunmu.bluebox.entity.vo.LableVO;

public interface LableRepository extends JpaRepository<Lable, String> {
    long countLableByCreateBy(String memberId);

    Iterable<Lable> findLablesByCreateBy(String memberId);

    @Query(value = "select new pers.kakayunmu.bluebox.entity.vo.LableVO(l.id,l.name,l.remark ,l.icon,l.isSys,count(g.id)) " +
            "from Lable l " +
            "left join l.goods g " +
            "where l.createBy=:memberId " +
            "group by l.id " +
            "order by count (g.id) desc ")
    Page<LableVO> findLablesByCreateByWithGoodCount(Pageable pageable, @Param("memberId") String memberId);
}
