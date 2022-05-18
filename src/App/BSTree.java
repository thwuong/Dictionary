package App;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;




public class BSTree {
	 public int size;
	 public Node root;
	 
	 public BSTree() {
                this.size = 0;
                this.root = null;
	 }
	 
	 public int getSize() {
                return this.size;
	 }
	 
	// tim node bang tu
	 public Node search(String word) {
                return search(this.root,word);
	 }
	 
	 private Node search(Node node, String word) {
                if(node == null)
                    return null;
                
                if(node.hasWord(word)){
                    return node;
                }
                else if(node.leftOf(word)) {
                    return search(node.left,word);
                }
                else
                    return search(node.right,word);         
	 }
	 // Tim gan dung
	 public List<Word> searchNearest(String word) {
		 List<Word> words = new ArrayList<>();
		 searchNearest(this.root,word,words);
		 return words;
	 }
	 private void searchNearest(Node node, String word,List<Word> words ) {
                if(node != null) {
                	if(node.nearestWord(word))
             	   		words.add(node.word);
                
                	searchNearest(node.left,word,words);
            
                	searchNearest(node.right,word,words);
                }
                
	 }
	 // them node moi cho cay
	 public void insert(Node node) {
                this.root = insert(this.root,node);
		  
	 }
	 private Node insert(Node node, Node newNode) {
                if(node == null) {
                    this.size++;
                    return newNode;
                        // neu root rong tra ve cho root nut goc
                }
                if(node.leftOf(newNode.word.getWord())) {
                    node.left = insert(node.left, newNode);
                }
                else if(node.rightOf(newNode.word.getWord())){
                    node.right = insert(node.right, newNode);
                }
                return node;
	 }
         
         public void update(Node node){
                Scanner input = new Scanner(System.in);
                Word wordexist = node.word;
               // thêm example
                System.out.println("\nNhập số lượng Example(Ví dụ) tối đa 3 Example");
                int sl = input.nextInt();
                input.nextLine();
                String[] exampleAdd = new String[sl];
                for(int i = 0 ; i<sl ; i++){
                    System.out.println("\nNhập ví dụ thứ : " + (i+1));
                    exampleAdd[i] = input.nextLine();
                }
                // lấy độ dài mảng
                String[] exampleUpdate = new String[exampleAdd.length + wordexist.getExample().length];
    
                System.arraycopy( wordexist.getExample(), 0, exampleUpdate, 0,  wordexist.getExample().length);
                System.arraycopy(exampleAdd, 0, exampleUpdate, wordexist.getExample().length, exampleAdd.length); 
                // set examples
                
                node.word.setExample(exampleUpdate);
                
         }
	 // xoa 1 node bang tu
	 public void delete(String word) {
		this.root = delete(this.root,word);
                
	 }
	 private Node delete(Node root,String word) {
                if(root == null)
                    return null;
                if(root.hasWord(word)) {
                        this.size--;
                        if(root.left == null) {
                                return root.right;
                        }
                        Node maxNode = root.left;
                        Node preNode = maxNode;
                        while(maxNode.right != null) {
                                preNode = maxNode;
                                maxNode = maxNode.right;
                        }
                        maxNode.right = root.right;
                        if(maxNode != root.left) {
                                preNode.right = maxNode.left;
                                maxNode.left = root.left;
                        }
                        return maxNode;
                }
                if(root.leftOf(word)) {
                        root.left = delete(root.left,word);
                }
                if(root.rightOf(word)){
                        root.right = delete(root.right,word);
                }
                return root;
	 }
	 
//	 Node -> Left -> Right
	 public void printPreOrder() {
		 printPreOrder(this.root);
	 }
	 private void printPreOrder(Node node) {
		 if(node == null) return ;
		 node.printData();
		 printPreOrder(node.left);
		 printPreOrder(node.right);
	 }
//	 Left -> Node -> Right
	 public void printInOrder() {
		 printInOrder(this.root);
	 }
	 private void printInOrder(Node node) {
		 if(node == null) return ;
		 printInOrder(node.left);
		 node.printData();
		 printInOrder(node.right);
	 }
//	Left -> Right -> Node
	 public void printPostOrder() {
		 printPostOrder(this.root);
	 }
	 
	 private void printPostOrder(Node node) {
		 if(node == null) return;
		 printPostOrder(node.left);
		 printPostOrder(node.right);
		 node.printData();
	 }
        //    Write file    
        
        public void writeToFile(String url){
           try{
               BufferedWriter  bw = new BufferedWriter(new FileWriter(url));
               write(this.root,bw,url);
               bw.close();
           }   
           catch(Exception ex) {
               System.out.println("An error occured try again ");
           }
        }
        private void write(Node focusNode, BufferedWriter bw, String url) throws IOException{
            if (focusNode != null) {
                   bw.write(focusNode.toString() +"\n");//add the current node
                   
                   write(focusNode.left,bw,url); //adds the  left node

                   write(focusNode.right,bw,url); // add the right node
               }
           
        }


        public BSTree readFormFile(String url){
		BSTree tree = new BSTree();
            try {
                FileReader fr = new FileReader(url);
                BufferedReader br = new BufferedReader(fr);
                String line = "";
                while(true){
                    line = br.readLine();
                    if(line == null){
                        break;
                    }
                    String txt[] = line.split(";");
                    String word = txt[0];
                    String mean = txt[1];
                    String pronounce = txt[2];
                    String example[] = txt[3].split(",");
                    tree.insert(new Node(new Word(word,mean,pronounce,example)));
                }
            }catch(Exception e){
                System.out.println("Lỗi chưa có dữ liệu");
            }
            return tree;
        }
}
