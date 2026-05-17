package Medium.GroupBy;

class Employee {
    String name, department, city;
    String gender;
    int age;
    double salary;
    boolean active;

    Employee(String name, String department, String city,
             String gender, int age, double salary, boolean active) {
        this.name = name;
        this.department = department;
        this.city = city;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getCity() {
        return city;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public boolean isActive() {
        return active;
    }

    public String toString() {
        return name;
    }
}