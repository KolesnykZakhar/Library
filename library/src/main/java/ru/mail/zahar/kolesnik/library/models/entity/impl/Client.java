package ru.mail.zahar.kolesnik.library.models.entity.impl;


import ru.mail.zahar.kolesnik.library.models.entity.Person;

public class Client extends Person {

    public Client(String name, String patronymic, String surname, String tel) {
        super(name, patronymic, surname, tel);
        role = "client";
    }

    public Client(int id, String role, String name, String patronymic, String surname, String tel, String login, String password) {
        super(id, role, name, patronymic, surname, tel, password, login);
    }

    public String info(){
        return surname+" "+name+" "+patronymic+", "+tel;
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
    public Client(){}
}
