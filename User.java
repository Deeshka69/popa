public class User {
    public String name;
    public String surname;
    protected String classNumber;
    protected String classLiter;

    protected int points;

    protected String description;

    private String chatID;

    protected User(String surname, String name, String classNumber, String classLiter, int points, String description){
        this.name = name;
        this.surname = surname;
        this.classNumber = classNumber;
        this.classLiter = classLiter;
        this.points = points;
        this.description = description;
    }

    public void showInfo(){
        System.out.println(name + " " + surname + " Из класса " + classNumber + classLiter + " Очков: " + points);
    }
    protected int getPoints(){
        return points;
    }



}
