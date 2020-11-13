package Code;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CustomerInterface extends Remote {
		
		
    public String login(String password) throws RemoteException; 
    
    public String logout(String cookie) throws RemoteException; 


}