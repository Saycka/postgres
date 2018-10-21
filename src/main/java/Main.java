public class Main {

    public static void main(String[] args) {
        MyConnection myConnection = MyConnection.INSTANCE;
        myConnection.startConnection();
        myConnection.printAllFIO();
        myConnection.printAllFIOAndAddress();
        myConnection.printPhones();
        myConnection.insertPerson();
        myConnection.printAllFIO();
        myConnection.deletePerson();
        myConnection.printAllFIO();
        myConnection.updatePhone();
        myConnection.printPhones();
        myConnection.closeConnection();
    }
}
