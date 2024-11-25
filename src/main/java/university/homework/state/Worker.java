package university.homework.state;

public class Worker extends Student {
    private int salary;

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "{ " +
                "name: " + getName() + ", " +
                "age: " + getAge() + ", " +
                "salary: " + salary +
                " }";
    }
}
