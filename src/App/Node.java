package App;

public class Node {
	public Word word;
	public Node left;
	public Node right;

    public Node(Word word) {
        this.word = word;
        this.left = null;
        this.right = null;
    }
    
    public void printData(){
        word.printInfo();
    }

//  true thi di ben trai | false di ben phai
    public boolean rightOf(String word) {
            return this.word.getWord().compareToIgnoreCase(word) < 0; 
    }
    public boolean leftOf(String word) {
            return this.word.getWord().compareToIgnoreCase(word) > 0; 
    }
    
    public boolean hasWord(String word) {
            return this.word.getWord().compareToIgnoreCase(word) == 0;
    }
    public boolean nearestWord(String word) {
            return this.word.getWord().contains(word);
    }
    
    @Override
    public String toString() {
            return word.toString();
    }
}
