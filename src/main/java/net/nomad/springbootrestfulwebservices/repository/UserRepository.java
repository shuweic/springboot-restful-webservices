package net.nomad.springbootrestfulwebservices.repository;

import net.nomad.springbootrestfulwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
