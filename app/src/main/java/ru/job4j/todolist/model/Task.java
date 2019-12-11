package ru.job4j.todolist.model;

import java.util.Date;

public class Task {
    private String name;
    private String desc;
    private Date created;
    private boolean closed = false;
    private int id;

    public Task(String name, String desc, Date created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public Task(int id, String name, String desc, Date created) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        if (isClosed() != task.isClosed()) return false;
        if (getId() != task.getId()) return false;
        if (getName() != null ? !getName().equals(task.getName()) : task.getName() != null)
            return false;
        if (getDesc() != null ? !getDesc().equals(task.getDesc()) : task.getDesc() != null)
            return false;
        return getCreated() != null ? getCreated().equals(task.getCreated()) : task.getCreated() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getDesc() != null ? getDesc().hashCode() : 0);
        result = 31 * result + (getCreated() != null ? getCreated().hashCode() : 0);
        result = 31 * result + (isClosed() ? 1 : 0);
        result = 31 * result + getId();
        return result;
    }
}
