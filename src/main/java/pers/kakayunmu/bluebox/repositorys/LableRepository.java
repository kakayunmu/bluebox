package pers.kakayunmu.bluebox.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.kakayunmu.bluebox.entity.Lable;

interface LableRepository extends JpaRepository<Lable, Long> {
}
