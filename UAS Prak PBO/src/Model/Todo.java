package Model;

public class Todo {
    private String title;
    private String note;
    private int userId;

    public Todo(String title, String note, int userId) {
        this.title = title;
        this.note = note;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
}