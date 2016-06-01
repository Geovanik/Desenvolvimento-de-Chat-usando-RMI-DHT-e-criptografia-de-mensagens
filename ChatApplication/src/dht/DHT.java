package dht;

public class DHT<ItemInfo> {

	private static final int K = 12; //Identificar valor dessa constante
	
	public class ItemNode {
		private Integer id;
		private ItemNode next;
		private String data;
		
		public ItemNode(Integer id, String data){
			this.id = id;
			this.data = data;
		}		
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getId(){
			return id;
		}
		
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		public ItemNode getNext() {
			return next;
		}
		public void setNext(ItemNode next) {
			this.next = next;
		}
		
		/*Dado um nó inicial, busca o nó rsponsável
		 * para a key destino 
		 * */
		public ItemNode findNode(ItemNode start, Integer key){
			ItemNode current = start;
			while (current.getId() <= key && key < current.getNext().getId()) {
				if (key > current.getId() && current.getId() > current.getNext().getId()) {
					break;
				} else {
					current = current.getNext();
				}
			}
			return closestNode(current, current.getNext(), key);
		}
		
		/*Esta é uma função de distância do anel utilizando sentido horário
		 * Depende de uma variavel K definida globalmente, ou seja o tamanho da chave
		 * O maior node possivel será 2^K
		 * Retorna o nó mais próximo da chave
		 * */
		public ItemNode closestNode(ItemNode node, ItemNode successor, Integer key) {
			if (node.getId() > successor.getId()) {
				if (((2^DHT.K) - successor.getId() - key) > (key - node.getId())) {
					return node;
				} else {
					return successor;
				}
			} else {
				if ((key - node.getId()) > (successor.getId() - key)) {
					return successor;
				} else {
					return node;
				}
			}	
		}
		
		/*Procura o no responsavel e armazena o valor
		 * com a chave
		 * */
		public void store(ItemNode start, Integer key, String value){
			ItemNode node = findNode(start, key);
			node.setId(key);
			node.setData(value);
		}
		
		/*Procura o no responsavel e retorna o valor
		 * para a chave
		 * */
		public String lookup(ItemNode start, Integer key){
			ItemNode node = findNode(start, key);
			return node.getData();
		}

	}

}
