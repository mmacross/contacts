package org.mmss.contacts.repository;

import org.mmss.contacs.dto.ContactDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContactRepository extends PagingAndSortingRepository<ContactDto, Integer>{
	Page<ContactDto> findByNameLike(Pageable pageable, String name);
}
