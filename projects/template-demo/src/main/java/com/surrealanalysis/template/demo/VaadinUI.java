package com.surrealanalysis.template.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

    MeetupMemberRepository repository;
    Grid grid;

    @Autowired
    public VaadinUI(MeetupMemberRepository repo) {
        this.repository = repo;
        this.grid = new Grid();
    }

    @Override
    protected void init(VaadinRequest request) {
        setContent(grid);
        listMeetupMembers();
    }

    private void listMeetupMembers() {
        grid.setContainerDataSource(
            new BeanItemContainer(MeetupMember.class, repository.findAll()));
    }
}
e2