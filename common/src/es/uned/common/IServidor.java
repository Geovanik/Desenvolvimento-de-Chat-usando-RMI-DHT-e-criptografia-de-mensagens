package es.uned.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import es.uned.common.Mensagem;

public interface IServidor extends Remote {//extends para fazer a comunicação entre as classes
	
	public int autenticar(String nome) throws RemoteException;//servidor devolve um int autenticando a entrada do usuário no chat

	public int agregar(String nome,int sessao) throws RemoteException;
	
	public void enviar(String nome, int sessaoDe, int sessaoA) throws RemoteException;
	
	public List<Mensagem> receber(int sessao) throws RemoteException;//verifica se tem mensagem recebidas// import da classe criada Mensagem criada
}
