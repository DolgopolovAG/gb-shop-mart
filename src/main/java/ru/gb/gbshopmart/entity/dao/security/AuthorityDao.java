package ru.gb.gbshopmart.entity.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbshopmart.entity.security.Authority;

public interface AuthorityDao extends JpaRepository<Authority, Long> {
}