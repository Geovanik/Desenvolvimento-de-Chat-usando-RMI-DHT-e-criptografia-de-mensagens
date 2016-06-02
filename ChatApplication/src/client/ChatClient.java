package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import server.ChatServerIF;

public class ChatClient extends UnicastRemoteObject implements ChatClientIF, Runnable {

	private static final long serialVersionUID = 1L;
	private ChatServerIF chatServer;
	private String name = null;
	
	protected ChatClient(String name, ChatServerIF chatServer) throws RemoteException {
		this.name = name;
		this.chatServer = chatServer;
		chatServer.registerChatClient(this);
	}

	public void retrieveMessage(String message) throws RemoteException {
		System.out.println(message);
	}

	public void run() {
	
		Scanner scanner = new Scanner(System.in);//estancia um objeto scanner
		String message;
		
		//mostra menu com usuários conectados vindo da dht
		while (true){
			//System.out.println("\n-- > Menu < -- \n");
			System.out.println("\nUsuários disponíveis: (DHT)\n");//mostra os usuários vindos da dht e escolhe um pelo nick ou nome, por enquanto digite um nome qualquer
			
			message = scanner.nextLine();//lendo qual usuário este cliente quer se conectar
			
			if(message.equals("sairdef")){//se ele quer sair definitivamente do sistema
				//matar a thread do terminal
				System.out.println("\nVoce saiu!\n");
				break;
			}
			
			while (true) {//enviando mensagens
				message = scanner.nextLine();
				if(message.equals("end")){
					break;
				}
				try {
					//se mensagem for = end desconecta o usuário do pvt, saindo dai ele pode ainda falar em grupo ou sair 
					chatServer.broadcastMessage(name + " : " + message);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
	}	
}
