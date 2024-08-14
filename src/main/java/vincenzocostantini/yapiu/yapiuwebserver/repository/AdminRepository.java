package vincenzocostantini.yapiu.yapiuwebserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vincenzocostantini.yapiu.yapiuwebserver.entities.users.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {
}