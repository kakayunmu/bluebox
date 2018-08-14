package pers.kakayunmu.bluebox.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.kakayunmu.bluebox.entity.Lable;

public interface LableRepository extends JpaRepository<Lable, Long> {
    long countLableByCreateBy(long memberId);
    Iterable<Lable> findLablesByCreateBy(long memberId);
}
