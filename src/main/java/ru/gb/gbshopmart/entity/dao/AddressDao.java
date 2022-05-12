package ru.gb.gbshopmart.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbshopmart.entity.Address;

public interface AddressDao extends JpaRepository<Address, Long> {
}