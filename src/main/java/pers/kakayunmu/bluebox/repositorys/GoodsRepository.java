package pers.kakayunmu.bluebox.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.kakayunmu.bluebox.entity.Goods;

interface GoodsRepository extends JpaRepository<Goods, Long> {
}
