package ru.mail.zahar.kolesnik.library.models.entity;


public class Client {

    public Client(int id, String role, String name, String patronymic, String surname, String tel, String login, String password) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.tel = tel;
        this.password = password;
        this.login = login;
    }

    public String info() {
        return surname + " " + name + " " + patronymic + ", " + tel;
    }

    @Override
    public String toString() {
        String buffPatronymic = "empty";
        if (patronymic != null) {
            buffPatronymic = patronymic;
        }
        return
                "\nName: " + name +
                        "\nPatronymic: " + buffPatronymic +
                        "\nSurname: " + surname +
                        "\nPhone Number: " + tel;
    }

    public Client() {
    }

    public int id;
    public String role;
    public String name;
    public String patronymic;
    public String surname;
    public String tel;
    public String password;
    public String login;

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Client && this.id == ((Client) obj).id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
