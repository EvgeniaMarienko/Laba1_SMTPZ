import java.util.ArrayList;

public class Main {
    public  static  void main(String[] args){
        DAO dao = new TourDAO();
        // dao.create(new Tour("odin","Africa, safari, animals", "Africa", "nice chill", 2000,"imge" ));
        ArrayList<Tour> tours =  dao.getAll();
        for (Tour tour : tours){
            System.out.println(tour.toString());
        }

        DAO clientdao = new ClientDAO();
        //clientdao.create(new Client("odinkek","Dima Matuk", "borshagovka", 21));
        //clientdao.create(new Client("unique","Dima Matuk", "borshagovka", 21));
        Client client = (Client) clientdao.getEntityByID("unique");
        client.setTourID("odin");
        System.out.println(client.toString());
    }
}
