package App;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dictionary {
        private static final String  url = "D:/UDLapTrinh/eclipse-workspace/ProjectManager/models/Binary Search Tree.txt";
        
	public static void main(String[] args){
                System.out.println("Binary Search Tree - BTS");
		System.out.println("Cay Nhi Phan - CNP");
                Scanner input = new Scanner(System.in);
                int n;
                String keyWord ;
                boolean exit = false;
                List<Node> nodesSearched = new ArrayList<>();
                BSTree tree = new BSTree();
                // Tạo word để hứng object
                tree  = tree.readFormFile(url);
		// TODO Auto-generated method stub
                showMenu();
                while(true){
                    n = input.nextInt();
                    switch ( n ) {
                        case  1:
                            System.out.println("Nhập các thông tin từ mới");
                            Word word = Nhap(tree);
                            // ton tai word
                            if(word != null){
                                tree.insert(new Node(word));
                                System.out.println("Thêm từ mới thành công");
                            }
                            else{
                                System.out.println("Từ đã tồn tại và đã được cập nhật");
                            }
                            break;
                        case  2:
                            System.out.println("Nhập từ cần xóa");
                            keyWord = input.next();
                            tree.delete(keyWord);
                            break;
                        case  3:
                            System.out.println("Nhập từ cần tìm");
                            keyWord = input.next();
                            Node nodeSearch = tree.search(keyWord);
                            // Luu danh sach tu vua tra
                            if(nodeSearch == null){
                                // tìm những từ gần đúng
                                System.out.println("Từ tìm không có. Đây là những từ có liên quan");
                                tree.searchNearest(tree.root,keyWord);
                            }
                            else
                            {
                                nodesSearched.add(nodeSearch);
                                System.out.println("Thông tin từ cần tìm");
                                nodeSearch.printData();
                            }
                            break;
                        case  4:    
                            System.out.println("4 - In cây theo Pre Order");          
                            tree.printPreOrder();
                            break;
                        case  5:
                             System.out.println("5 - In cây theo In Order");           
                            tree.printInOrder();
                            break;
                        case  6:
                            System.out.println("6 - In cây theo Post Order");
                            tree.printPostOrder();
                            break;
                        case  7:
                            System.out.print("7 - Số lượng từ trong từ điển :");
                            System.out.println((tree.size));
                        break;
                        case  8:
                            if(nodesSearched.size() > 0){
                                System.out.println("8 - Danh sách từ đã tra");
                                System.out.println(nodesSearched);
                            }
                            else{
                                System.out.println("Danh sách rỗng");
                            }
                        break;
                        case 0 :
                            System.out.println("exited!");
                            exit = true;
                            break;
                        default:
                            System.out.println("Nhập số không hợp lệ! mời bạn nhập lại");
                    }
                    if(exit){
                        System.out.println("Bạn có muốn lưu dữ liệu mới hay không");
                        System.out.println("Nhập (số) tương ứng \n1 - lưu lại\n2 - Hủy bỏ");
                        int numberCheck = input.nextInt();
                        if(numberCheck == 1){
                            tree.writeToFile(url);
                            nodesSearched.clear();
                        }
                        else {
                            nodesSearched.clear();
                        }
                        // Ghi file
                        System.out.println("Cảm ơn đã sử dụng ứng dụng");
                        System.out.println(""
                      + "(¯`*•.¸,¤°´’`°¤,¸.•*´¯)\n" +
                        "    Cảm ơn rất nhiều \n" +
                        "  đã sử dụng ứng dụng\n" +
                        "\n" +
                        "(_¸.•*´’`°¤¸’¸¤°´’`*•.¸_)");
                        break;
                    }
                    showMenu();
                }
	
        }
        public static Word Nhap(BSTree tree){
            Scanner input = new Scanner(System.in);
            String wordNew, mean, pronounce; 
            System.out.println("Nhập Word(Từ)");
            wordNew = input.nextLine();
            Node nodeExist = tree.search(wordNew);
            if(nodeExist != null) {
                Word wordExist = nodeExist.word;
                int next ;
                System.out.println("\n Từ đã tồn tại muốn thêm ví dụ không cho từ " + wordNew);
                System.out.println("\n Chọn 1 đồng ý hoặc bất kì(trừ 1) để bỏ qua");
                next = input.nextInt();
                switch(next){
                    case 1 : 
                        tree.update(nodeExist);
                    break;
                    default :
                }
                return null;
            }
            else{
                System.out.println("Nhập Mean(Nghĩa của từ)");
                mean = input.nextLine();
                System.out.println("Nhập Pronounce(Phát âm)");
                pronounce = input.nextLine();
                System.out.println("Nhập số lượng Example(Ví dụ) tối đa 3 Example");
                int sl = input.nextInt();
                input.nextLine();
                String[] example = new String[sl];
                for(int i = 0 ; i<sl ; i++){
                    System.out.println("Nhập ví dụ thứ : " + i);
                    example[i] = input.nextLine();
                }
            
            return new Word(wordNew,mean,pronounce,example) ;
            }
            
        }
        public static void showMenu() {
            System.out.println("\n-----------menu------------");
            System.out.println("1 - Thêm từ mới");
            System.out.println("2 - Xóa từ");
            System.out.println("3 - Tìm từ");
            System.out.println("4 - In cây theo Pre Order");
            System.out.println("5 - In cây theo In Order");
            System.out.println("6 - In cây theo Post Order");
            System.out.println("7 - Số lượng từ trong từ điển");
            System.out.println("8 - Danh sách từ đã tra");
            System.out.println("0 - exit.");
            System.out.println("---------------------------");
            System.out.println("Người dùng hãy nhập (số) tương ứng với chức năng cần dùng");
        }
}
// thêm ví dụ ........ success
// các từ gần giống ...... success
// lưu từ đã tra ......  success
// khi thoát hỏi lưu hay không  success
