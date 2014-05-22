package org.mmss.contacts.repository;

import org.mmss.contacs.dto.UserDto;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDto, Integer>{
    UserDto findByEmail(String email);
}
