package org.mmss.contacs.vo;

import java.util.List;

import org.mmss.contacs.dto.ContactDto;

public class ContactListVo {
	private int pagesCount;
    private long totalContacts;

    private String actionMessage;
    private String searchMessage;

    private List<ContactDto> contacts;

    public ContactListVo() {
    }

    public ContactListVo(int pages, long totalContacts, List<ContactDto> contacts) {
        this.pagesCount = pages;
        this.contacts = contacts;
        this.totalContacts = totalContacts;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public List<ContactDto> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactDto> contacts) {
        this.contacts = contacts;
    }

    public long getTotalContacts() {
        return totalContacts;
    }

    public void setTotalContacts(long totalContacts) {
        this.totalContacts = totalContacts;
    }

    public String getActionMessage() {
        return actionMessage;
    }

    public void setActionMessage(String actionMessage) {
        this.actionMessage = actionMessage;
    }

    public String getSearchMessage() {
        return searchMessage;
    }

    public void setSearchMessage(String searchMessage) {
        this.searchMessage = searchMessage;
    }

}
