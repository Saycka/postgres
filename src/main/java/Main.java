public class Main {

    public static void main(String[] args) {
        MyConnection myConnection = MyConnection.INSTANCE;
        myConnection.printAllFIO();
        myConnection.printAllFIOAndPhone();
    }
}
