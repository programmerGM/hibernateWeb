package com.project.controls;

import java.util.List;

import com.project.dao.GroupDao;
import com.project.entities.Group;

/**
 * Class group control.
 * 
 * @author Maurício Generoso
 * @since 15/03/2018
 * @version 0.1
 */
public class GroupControls extends Controls<GroupDao, Group> {

    /**
     * Construct.
     */
    public GroupControls() {
	super(new GroupDao());
    }

    /**
     * GetById.
     */
    @Override
    public Group getById(Long id) {
	return getDao().getById(Group.class, id);
    }

    /**
     * GetAll.
     */
    @Override
    public List<Group> getAll() {
	return getDao().getAll(Group.class.getSimpleName());
    }

    /**
     * Find group by name group.
     * 
     * @param nameGroup
     *            - Name group.
     * @return Group.
     */
    public Group getByName(String nameGroup) {
	return null;
    }

}
