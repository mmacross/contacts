package org.mmss.contacs.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mmss.contacs.dto.ContactDto;
import org.mmss.contacs.vo.ContactListVo;
import org.mmss.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContactService {
	private final static Log _log = LogFactory.getLog(ContactService.class);
	@Autowired
    private ContactRepository contactRepository;
	
	@Transactional(readOnly = true)
    public ContactListVo findAll(int page, int maxResults) {
		_log.debug("inicio");
		_log.debug("page:"+page+", maxResults:"+maxResults);
        Page<ContactDto> result = executeQueryFindAll(page, maxResults);

        if(shouldExecuteSameQueryInLastPage(page, result)){
            int lastPage = result.getTotalPages() - 1;
            result = executeQueryFindAll(lastPage, maxResults);
        }
        _log.info("result:"+result);
        _log.debug("fin");
        return buildResult(result);
    }
	
	public void save(ContactDto contact) {
        contactRepository.save(contact);
    }

    @Secured("ROLE_ADMIN")
    public void delete(int contactId) {
        contactRepository.delete(contactId);
    }

    @Transactional(readOnly = true)
    public ContactListVo findByNameLike(int page, int maxResults, String name) {
        Page<ContactDto> result = executeQueryFindByName(page, maxResults, name);

        if(shouldExecuteSameQueryInLastPage(page, result)){
            int lastPage = result.getTotalPages() - 1;
            result = executeQueryFindByName(lastPage, maxResults, name);
        }

        return buildResult(result);
    }

    private boolean shouldExecuteSameQueryInLastPage(int page, Page<ContactDto> result) {
        return isUserAfterOrOnLastPage(page, result) && hasDataInDataBase(result);
    }

    private Page<ContactDto> executeQueryFindAll(int page, int maxResults) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByNameASC());

        return contactRepository.findAll(pageRequest);
    }

    private Sort sortByNameASC() {
        return new Sort(Sort.Direction.ASC, "name");
    }

    private ContactListVo buildResult(Page<ContactDto> result) {
        return new ContactListVo(result.getTotalPages(), result.getTotalElements(), result.getContent());
    }

    private Page<ContactDto> executeQueryFindByName(int page, int maxResults, String name) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByNameASC());

        return contactRepository.findByNameLike(pageRequest, "%" + name + "%");
    }

    private boolean isUserAfterOrOnLastPage(int page, Page<ContactDto> result) {
        return page >= result.getTotalPages() - 1;
    }

    private boolean hasDataInDataBase(Page<ContactDto> result) {
        return result.getTotalElements() > 0;
    }
}
