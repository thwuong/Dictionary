package App;

import java.io.Serializable;
import java.util.Scanner;
import java.util.Arrays;  

public class Word implements Serializable{
	private String word;
        private String mean;
	private String pronounce;
        private String[] example;

	
	public Word(String wordNew,String meanNew, String pronounceNew ,String[] exampleNew) {
            word = wordNew;
            mean = meanNew;
            pronounce = pronounceNew;
            example= exampleNew;
	}

        private Word() {
           //
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getPronounce() {
            return pronounce;
        }

        public void setPronounce(String pronounce) {
            this.pronounce = pronounce;
        }

        public String getMean() {
            return mean;
        }

        public void setMean(String mean) {
            this.mean = mean;
        }

        public String[] getExample() {
            return example;
        }
       

        public void setExample(String[] exampleNew) {
            this.example = exampleNew;
        }
	   
	public void printInfo() {
		System.out.print("\nTừ: "+ this.word);
		System.out.print("\nPhát âm: "+ this.pronounce);
		System.out.print("\nNghĩa tiếng việt: "+ this.mean);
                System.out.print("\nCác ví dụ: "+Arrays.toString(this.example));
                System.out.print("\n");
	}
        @Override
        public String toString() {
            String examples = Arrays.toString(example).substring(1,Arrays.toString(example).length()-1);
            return word + ";" + pronounce + ";" + mean + ";" + examples;
        }
}
