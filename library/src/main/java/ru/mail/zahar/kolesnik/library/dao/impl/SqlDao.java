package ru.mail.zahar.kolesnik.library.dao.impl;


import ru.mail.zahar.kolesnik.library.dao.Dao;
import ru.mail.zahar.kolesnik.library.models.entity.impl.Book;
import ru.mail.zahar.kolesnik.library.models.entity.impl.Client;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class SqlDao implements Dao {


    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    /**
     * value used to get all books from database
     */
    private static final java.lang.String SQL_REQUEST_GET_ALL_BOOKS = "SELECT * FROM books;";

    /**
     * value used to founding books from database
     */
    private final static String SQL_REQUEST_SEARCH_BOOKS = "SELECT * FROM books WHERE ";
    /**
     * value used to founding clients from database
     */
    private final static String SQL_REQUEST_SEARCH_CLIENTS = "SELECT * FROM persons WHERE ";

    /**
     * value used to getting the name and role of the user by his login and password
     */
    private static final String SQL_REQUEST_AUTHORIZATION = "SELECT role, namePerson FROM persons WHERE ";

    /**
     * value used to add book to dataBase
     */
    private final static String SQL_REQUEST_INSERT_BOOK = "INSERT INTO books (isbn, bookName, category, description, authorName, authorPatronymic, authorSurname) VALUES ";

    /**
     * value used to find issued books
     */
    final static String SQL_REQUEST_GET_ISSUED_BOOKS = "SELECT * FROM (SELECT * FROM shelf NATURAL JOIN books) AS issued_books NATURAL JOIN persons AS issue ORDER BY givenDate ASC ";

    /**
     * value used to add client to the database
     */
    private final static String SQL_REQUEST_INSERT_CLIENT = "INSERT INTO persons (namePerson, surname, patronymic, tel, login, password) VALUES ";

    /**
     * линк к домашней базе
     */
    private final static String DATA_BASE_URL = "jdbc:mysql://127.0.0.1:3306/library?user=root&password=root1111";

    /**
     * интерфейс для внутренних методов делающих запросы в Базу
     */
    interface RequestDataBase {
        void request(Connection conn) throws SQLException;
    }

    /**
     * коннектится к базе принимая внутренние классы метода
     */
    void connectToBase(RequestDataBase requestDataBase) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            connection = DriverManager.getConnection(DATA_BASE_URL);
            connection.setAutoCommit(false);
            requestDataBase.request(connection);
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
                connection = null;
            }
            if (resultSet != null) {
                resultSet.close();
                resultSet = null;
            }
            if (preparedStatement != null) {
                preparedStatement.close();
                preparedStatement = null;
            }
        }
    }

    /**
     * Выданные книги
     */
    @Override
    public List<Book> getIssuedBooksFromBase() throws SQLException, ClassNotFoundException {

        List<Book> issuedBooks = new LinkedList<>();

        //inner
        class RequestDB implements RequestDataBase {
            public void request(Connection conn) throws SQLException {
                preparedStatement = conn.prepareStatement(SQL_REQUEST_GET_ISSUED_BOOKS);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    issuedBooks.add(new Book(resultSet.getInt("idBook"), resultSet.getInt("isbn"), resultSet.getString("bookName"), resultSet.getString("category"),
                            resultSet.getString("description"), resultSet.getString("authorName"), resultSet.getString("authorPatronymic"),
                            resultSet.getString("authorSurname"), resultSet.getTimestamp("givenDate"), resultSet.getTimestamp("returnedDate"), new Client(resultSet.getInt("idPerson"), resultSet.getString("role"),
                            resultSet.getString("namePerson"), resultSet.getString("patronymic"), resultSet.getString("surname"),
                            resultSet.getString("tel"), resultSet.getString("login"), resultSet.getString("password"))
                    ));
                }
            }
        }

        connectToBase(new RequestDB());
        return issuedBooks;
    }

    /**
     * adds book to database
     */
    @Override
    public void addBookToBase(int id, String bookName, String category, String description, String authorName, String authorPatronymic,
                              String authorSurname) throws SQLException, ClassNotFoundException {
        String book = "('" + id + "', '" + bookName + "', '" + category + "', '" + description + "', '" + authorName + "', '" + authorPatronymic + "', '" + authorSurname + "');";
        //inner
        class RequestDB implements RequestDataBase {
            public void request(Connection conn) throws SQLException {
                preparedStatement = conn.prepareStatement(SQL_REQUEST_INSERT_BOOK + book);
                preparedStatement.executeUpdate();
            }
        }

        connectToBase(new RequestDB());
    }

    /**
     * add the client to the database
     */
    @Override
    public void addClientToBase(String name, String surname, String patronymic, String tel, String login,
                                String password) throws SQLException, ClassNotFoundException {
        String client = "( '" + name + "', '" + surname + "', '" + patronymic + "', '" + tel + "', '" + login + "', '" + password + "')";
        //inner
        class RequestDB implements RequestDataBase {
            public void request(Connection conn) throws SQLException {
                preparedStatement = conn.prepareStatement(SQL_REQUEST_INSERT_CLIENT + client);
                preparedStatement.executeUpdate();
            }
        }

        connectToBase(new RequestDB());
    }

    /**
     * a list of books that customers owe
     */
    @Override
    public List<Book> getDebtorsFromBase() throws SQLException, ClassNotFoundException {
        return getIssuedBooksFromBase().stream().filter(book -> LocalDateTime.now().isAfter(book.returnedDate.toLocalDateTime()))
                .collect(Collectors.toList());
    }

    /**
     * returns the name and role of the user by his login and password
     */
    @Override
    public String[] getPersonFromBase(String login, String password) throws SQLException, ClassNotFoundException {
        String loginPassword = "login = '" + login.trim() + "' AND password = '" + password.trim() + "';";
        final String[] role = {"", ""};
        class RequestDB implements RequestDataBase {
            public void request(Connection conn) throws SQLException {
                preparedStatement = conn.prepareStatement(SQL_REQUEST_AUTHORIZATION + loginPassword);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {

                    role[0] = resultSet.getString("role");
                    role[1] = resultSet.getString("namePerson");
                } else {
                    role[0] = "";
                }

            }
        }
        connectToBase(new RequestDB());
        return role;
    }

    /**
     * return founded books from database
     */
    @Override
    public List<Book> searchBookInBase(String authorName, String authorPatronymic, String authorSurname, String bookName,
                                       String category) throws SQLException, ClassNotFoundException {
        String searchBook = "authorName LIKE '%" + authorName + "%' AND authorPatronymic LIKE '%" +
                authorPatronymic + "%' AND authorSurname LIKE '%" + authorSurname + "%' AND bookName LIKE '%" +
                bookName + "%' AND category LIKE '%" + category + "%';";
        List<Book> foundedBooks = new LinkedList<>();

        //inner
        class RequestDB implements RequestDataBase {
            public void request(Connection conn) throws SQLException {
                preparedStatement = conn.prepareStatement(SQL_REQUEST_SEARCH_BOOKS + searchBook);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    foundedBooks.add(new Book(resultSet.getInt("idBook"), resultSet.getInt("isbn"), resultSet.getString("bookName"),
                            resultSet.getString("category"), resultSet.getString("description"),
                            resultSet.getString("authorName"), resultSet.getString("authorPatronymic"),
                            resultSet.getString("authorSurname"), resultSet.getTimestamp("givenDate"),
                            resultSet.getTimestamp("returnedDate"), null));
                }
            }
        }

        connectToBase(new RequestDB());
        return foundedBooks;
    }

    /**
     * return founded clients from database
     */
    @Override
    public List<Client> searchClientInBase(String namePerson, String surname, String patronymic) throws SQLException, ClassNotFoundException {
        String searchClient = "namePerson LIKE '%" + namePerson + "%' AND patronymic LIKE '%" +
                patronymic + "%' AND surname LIKE '%" + surname + "%';";
        List<Client> foundedClients = new LinkedList<>();

        //inner
        class RequestDB implements RequestDataBase {
            public void request(Connection conn) throws SQLException {
                preparedStatement = conn.prepareStatement(SQL_REQUEST_SEARCH_CLIENTS + searchClient);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    foundedClients.add(new Client(resultSet.getInt("idPerson"), "client",
                            resultSet.getString("namePerson"), resultSet.getString("patronymic"),
                            resultSet.getString("surname"), resultSet.getString("tel"),
                            resultSet.getString("login"), resultSet.getString("password")));
                }
            }
        }

        connectToBase(new RequestDB());
        return foundedClients;
    }

    /**
     * returned list of all books from database
     */
    @Override
    public List<Book> getAllBooksFromBase() throws SQLException, ClassNotFoundException {
        List<Book> foundedBooks = new LinkedList<>();

        //inner
        class RequestDB implements RequestDataBase {
            public void request(Connection conn) throws SQLException {
                preparedStatement = conn.prepareStatement(SQL_REQUEST_GET_ALL_BOOKS);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    foundedBooks.add(new Book(resultSet.getInt("idBook"), resultSet.getInt("isbn"), resultSet.getString("bookName"), resultSet.getString("category"),
                            resultSet.getString("description"), resultSet.getString("authorName"), resultSet.getString("authorPatronymic"),
                            resultSet.getString("authorSurname"), resultSet.getTimestamp("givenDate"), resultSet.getTimestamp("returnedDate"), null));
                }
            }
        }

        connectToBase(new RequestDB());
        return foundedBooks;
    }


    private static final String SQL_REQUEST_GET_ALL_CLIENTS = "SELECT * FROM persons;";

    @Override
    public List<Client> getAllClientsFromDatabase() throws SQLException, ClassNotFoundException {
        List<Client> foundedClients = new LinkedList<>();

        //inner
        class RequestDB implements RequestDataBase {
            public void request(Connection conn) throws SQLException {
                preparedStatement = conn.prepareStatement(SQL_REQUEST_GET_ALL_CLIENTS);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    foundedClients.add(new Client(resultSet.getInt("idPerson"), "client",
                            resultSet.getString("namePerson"), resultSet.getString("patronymic"),
                            resultSet.getString("surname"), resultSet.getString("tel"),
                            resultSet.getString("login"), resultSet.getString("password")));
                }
            }
        }

        connectToBase(new RequestDB());
        return foundedClients;
    }

    private static String SQL_REQUEST_GET_CLIENT_BY_PHONE = "SELECT * FROM persons WHERE tel = ?;";

    @Override
    public Client getClientFromDatabaseByPhone(String clientTel) throws SQLException, ClassNotFoundException {
        final Client[] client = {null};
        //inner
        class RequestDB implements RequestDataBase {
            public void request(Connection conn) throws SQLException {
                preparedStatement = conn.prepareStatement(SQL_REQUEST_GET_CLIENT_BY_PHONE);
                preparedStatement.setString(1, clientTel);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    client[0] = new Client(resultSet.getInt("idPerson"), "client",
                            resultSet.getString("namePerson"), resultSet.getString("patronymic"),
                            resultSet.getString("surname"), resultSet.getString("tel"),
                            resultSet.getString("login"), resultSet.getString("password"));

                }
            }
        }

        connectToBase(new RequestDB());
        return client[0];
    }

    @Override
    public void editClientInDatabaseByID(int id, String namePerson, String surname, String patronymic,
                                         String tel, String loginClient, String passwordClient) throws SQLException, ClassNotFoundException {
        class RequestDB implements RequestDataBase {
            public void request(Connection conn) throws SQLException {
                preparedStatement = conn.prepareStatement("UPDATE persons SET namePerson = '" + namePerson + "', " +
                        "surname = '" + surname + "', patronymic = '" + patronymic + "', tel = '" + tel + "', login = '" + loginClient + "', " +
                        "password = '" + passwordClient + "' WHERE idPerson = '" + id + "';");
                preparedStatement.executeUpdate();

            }
        }

        connectToBase(new RequestDB());
    }

    @Override
    public void removeClientByIDFromDatabase(int id) throws SQLException, ClassNotFoundException {
        class RequestDB implements RequestDataBase {
            public void request(Connection conn) throws SQLException {
                preparedStatement = conn.prepareStatement("DELETE FROM persons WHERE idPerson = '" + id + "';");
                preparedStatement.executeUpdate();
            }
        }

        connectToBase(new RequestDB());
    }

    private final static String SQL_REQUEST_GET_BOOK_BY_ISBN = "SELECT * FROM books WHERE isbn = ?;";

    @Override
    public Book getBookByIdFromBase(int isbn) throws SQLException, ClassNotFoundException {
        final Book[] book = {null};
        //inner
        class RequestDB implements RequestDataBase {
            public void request(Connection conn) throws SQLException {
                preparedStatement = conn.prepareStatement(SQL_REQUEST_GET_BOOK_BY_ISBN);
                preparedStatement.setInt(1, isbn);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    book[0] = new Book(resultSet.getInt("idBook"), resultSet.getInt("isbn"), resultSet.getString("bookName"), resultSet.getString("category"),
                            resultSet.getString("description"), resultSet.getString("authorName"), resultSet.getString("authorPatronymic"),
                            resultSet.getString("authorSurname"), resultSet.getTimestamp("givenDate"), resultSet.getTimestamp("returnedDate"), null);

                }
            }
        }

        connectToBase(new RequestDB());
        return book[0];
    }

    private final static String SQL_REQUEST_IS_ISSUED_BOOK_BY_ISBN = "SELECT * FROM shelf WHERE idBook= (SELECT idBook FROM books WHERE isbn = ?);";

    @Override
    public boolean isNotIssuedByIsbnFormBase(String isbn) throws SQLException, ClassNotFoundException {
        boolean[] boo = {true};
        //inner
        class RequestDB implements RequestDataBase {
            public void request(Connection conn) throws SQLException {
                preparedStatement = conn.prepareStatement(SQL_REQUEST_IS_ISSUED_BOOK_BY_ISBN);
                preparedStatement.setInt(1, Integer.parseInt(isbn));
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    boo[0] = false;
                }
            }
        }

        connectToBase(new RequestDB());
        return boo[0];
    }

    private final static String SQL_REQUEST_ISSUE_BOOK_TO_CLIENT_INSERT = "INSERT INTO shelf (idBook, idPerson) VALUES (" +
            "(SELECT idBook FROM books WHERE isbn = ?)," +
            "(SELECT idPerson FROM persons WHERE tel = ?));";

    private final static String SQL_REQUEST_ISSUE_BOOK_TO_CLIENT_UPDATE = "UPDATE books SET givenDate = ?, returnedDate = ? WHERE isbn = ?;";

    @Override
    public void issueBookToClientFromDatabase(String isbn, String tel, Timestamp returnDate) throws SQLException, ClassNotFoundException {
        class Request implements RequestDataBase {
            public void request(Connection conn) throws SQLException {
                preparedStatement = conn.prepareStatement(SQL_REQUEST_ISSUE_BOOK_TO_CLIENT_INSERT);
                preparedStatement.setInt(1, Integer.parseInt(isbn));
                preparedStatement.setString(2, tel);
                preparedStatement.executeUpdate();
                System.out.println("ISSUE1");
                preparedStatement = conn.prepareStatement(SQL_REQUEST_ISSUE_BOOK_TO_CLIENT_UPDATE);
                preparedStatement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                preparedStatement.setTimestamp(2, Timestamp.valueOf(returnDate.toLocalDateTime()));
                preparedStatement.setInt(3, Integer.parseInt(isbn));
                preparedStatement.executeUpdate();
                System.out.println("ISSUE");
            }
        }

        connectToBase(new Request());
    }

    private static final String SQL_REQUEST_RETURN_BOOK_TO_CLIENT_REMOVE = "DELETE FROM shelf WHERE idBook = ?;";
    private static final String SQL_REQUEST_RETURN_BOOK_TO_CLIENT_UPDATE = "UPDATE books SET returnedDate = ? WHERE idBook = ?;";
//    private static final String SQL_REQUEST_RETURN_BOOK_TO_CLIENT_UPDATE = "UPDATE books SET returnedDate = ? WHERE (SELECT idBook FROM books WHERE isbn = ?) = idBook;";

    @Override
    public void returnBookInDatabase(String idBook, Timestamp returnDate) throws SQLException, ClassNotFoundException {
        class Request implements RequestDataBase {
            public void request(Connection conn) throws SQLException {
                preparedStatement = conn.prepareStatement(SQL_REQUEST_RETURN_BOOK_TO_CLIENT_REMOVE);
                preparedStatement.setInt(1, Integer.parseInt(idBook));
                preparedStatement.executeUpdate();
                System.out.println("RETURN1");
                preparedStatement = conn.prepareStatement(SQL_REQUEST_RETURN_BOOK_TO_CLIENT_UPDATE);
                preparedStatement.setInt(1, Integer.parseInt(idBook));
                preparedStatement.setTimestamp(2, Timestamp.valueOf(returnDate.toLocalDateTime()));
                preparedStatement.executeUpdate();
                System.out.println("RETURN");
            }
        }

        connectToBase(new Request());
    }

    private final static String SQL_REQUEST_GET_CLIENTS_WITH_RENTED_BOOKS = "SELECT * FROM (SELECT DISTINCT idPerson FROM shelf) AS ids NATURAL JOIN persons WHERE namePerson LIKE ? AND surname LIKE ? AND patronymic LIKE ?;";

    @Override
    public List<Client> searchClientWithBookInDatabase(String clientName, String clientSurname, String clientPatronymic) throws SQLException, ClassNotFoundException {
        List<Client> foundedClients = new LinkedList<>();

        //inner
        class RequestDB implements RequestDataBase {
            public void request(Connection conn) throws SQLException {
                preparedStatement = conn.prepareStatement(SQL_REQUEST_GET_CLIENTS_WITH_RENTED_BOOKS);
                preparedStatement.setString(1, "%" + clientName + "%");
                preparedStatement.setString(2, "%" + clientSurname + "%");
                preparedStatement.setString(3, "%" + clientPatronymic + "%");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    foundedClients.add(new Client(resultSet.getInt("idPerson"), "client",
                            resultSet.getString("namePerson"), resultSet.getString("patronymic"),
                            resultSet.getString("surname"), resultSet.getString("tel"),
                            resultSet.getString("login"), resultSet.getString("password")));
                }
            }
        }

        connectToBase(new RequestDB());
        return foundedClients;
    }

    /**
     * testing
     */
    public void printTest() throws SQLException, ClassNotFoundException {
        class RequestDB implements RequestDataBase {
            public void request(Connection conn) throws SQLException {
                preparedStatement = conn.prepareStatement(SQL_REQUEST_GET_ISSUED_BOOKS);
                resultSet = preparedStatement.executeQuery();
                int count = 1;
                while (resultSet.next()) {
                    System.out.println(count++);
                    System.out.println(resultSet.getTimestamp("returnedDate"));
                    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                        System.out.println("     " + i + ") " + resultSet.getMetaData().getColumnName(i) + ": " + resultSet.getString(i));
                    }
                    System.out.println("_________________________________________");
                }
            }
        }
        connectToBase(new RequestDB());
    }
}
