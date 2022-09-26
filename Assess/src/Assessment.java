import java.util.*;

public class Assessment {
	//Store the encoded text into variable
		static String encodedtext = "";
		//Character table
		static char[] table = {'A','B','C','D','E','F','G',
				'H','I','J','K','L','M','N','O','P','Q','R','S',
				'T','U','V','W','X','Y','Z','0','1','2','3','4','5',
				'6','7','8','9','(',')','*','+',',','-','.','/'};
		
		//make a copy of given table
		static char[] ori = table.clone();
		//Character of offset
		static char offChar;
		
		//Variable to store offset index
		static int offNew = 0;
		public static void main(String [] args)
		{
			//Get string input
			Scanner sc = new Scanner(System.in);
			//String example
			//String str = "HELLO WORLD";
			String txt = "ok";
			//Enter offset character
			System.out.println("Enter Offset: ");
			//Read the input offset character
			char in = sc.nextLine().charAt(0);
			char charUpper = Character.toUpperCase(in);
			
			System.out.println("Enter Message: ");
			String mess = sc.nextLine();
			String stringUpper = mess.toUpperCase();
			
			int offset = 0;
			for(int i = 0; i < 44; i++)
			{
				if(charUpper == table[i])
				{
					offset = i;
				}
			}
			
			//Store offset character into variable
			offChar = table[offset];
			offNew = offset;
			
			Assessment obj = new Assessment();
			//Call both methods
			System.out.println("Encoded Message: " + in + obj.encode(stringUpper));
			System.out.println("Decoded Message: " + obj.decode(encodedtext));
		}
		
		public String encode(String plainText)
		{
			//Convert plaintext to character array
			char [] res = plainText.toCharArray();
			//variable to store number of spaces in between text
			int space = 0;
			
			//loop to count the number of space in the text
			for(int i = 0; i < res.length; i++)
			{
				if(res[i] == ' ')
				{
					space++;
				}
			}
			
			//Get the offset table based on offset value
			for(int j = 0; j < offNew; j++)
			{
				//Stores the last element of given array in variable
				char tem = table[table.length - 1];
				//store length of array into variable
				int len = table.length;
				
				int last = table.length - 1;
				
				//loop to shift character from beginning to end by one time
				for(int i = len - 2; i >= 0; i--)
				{
					//store table[i]th element in last location of array
					table[last] = table[i];
					//decrement last so all element until the first will be shifted
					last --;
				}
				//Store last element of array at the first location
				table[0] = tem;
			}
			
			//Create integer array to store index of res array
			//res array is to convert plaintext to charater array
			int [] position = new int[res.length];
			//index of position array
			int pos = 0;
		
			//flag to check if element of String is present in the table
			int f = 0;
			for(int k = 0; k < res.length; k++)
			{
				for(int i = 0; i < ori.length; i++)
				{
					//if element from string is in the table, add its position in array
					if(res[k] == ori[i])
					{
						//indicate element of string is present in the table
						f = 1;
						//store the element
						position[pos] = i;
						//increment pos index of array
						pos++;
						break;
					}
				}
				//if element from input string is not in the table
				//then type cast that character to integer and store in array
				if(f == 0 && res[k] != ' ')
				{
					position[pos] = (int)res[k];
					pos++;
				}
				
				f = 0;
				//if space is present in input string then add 0 to position array
				if(res[k] == ' ')
				{
					position[pos] = 0;
					pos++;
				}
			}
			
			String tr = "";
			//if 0 is in position array, indicate presence of space and add space to string
			for(int i = 0; i < position.length; i++)
			{
				if(position[i] == 0)
				{
					tr = tr + " ";
				}
				//Encode element from table
				if(position[i] != 0 && position[i] >= 0 && position[i] <= 43)
				{
					tr = tr + table[position[i]];
				}
				
			}
			encodedtext = encodedtext + tr;
			return tr;
		}
		
		//method to decode character
		public String decode(String encodedText)
		{
			//Convert encoded string to array
			char [] encodedArray = encodedText.toCharArray();
			//create array to store positions of encoded string
			//int [] position = new int[encodedText.length()];
			
			String decodedtext = "";
			//flag to check if element of String is present in the table
			int f = 0;
			
			//Decoding of String
			for(int i = 0; i < encodedArray.length; i++)
			{
				for(int j = 0; j < table.length; j++)
				{
					//if the encoded characters matches with offset table then
					//add offset character to the decoded string
					if(encodedArray[i] == table[j])
					{
						f = 1;
						decodedtext = decodedtext + table[j + offNew];
						break;
					}
					//if there is spaces in array add the space
					if(encodedArray[i] == ' ')
					{
						decodedtext = decodedtext + " ";
						break;
					}
				}
				//no character present in the table, reprint the text
				if(f == 0)
				{
					decodedtext = decodedtext + encodedArray[i];
				}
				
				f = 0;
			}
			return decodedtext;
		}
}




