package ru.mail.zahar.kolesnik.library.models.entity.impl;


import ru.mail.zahar.kolesnik.library.models.entity.Staff;

public class Librarian extends Staff {
    public Librarian(String name, String patronymic, String surname, String tel) {
        super(name, patronymic, surname, tel);
        role = "librarian";
    }
}
