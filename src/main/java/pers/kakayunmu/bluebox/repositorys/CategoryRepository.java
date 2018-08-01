package pers.kakayunmu.bluebox.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.kakayunmu.bluebox.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
